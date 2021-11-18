package com.io.pet_newsapp.ui.news.fragments.server

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.io.pet_newsapp.R
import com.io.pet_newsapp.databinding.FragmentNewsServerBinding
import com.io.pet_newsapp.domain.base.Failure
import com.io.pet_newsapp.ui.extension.collectIn
import org.koin.androidx.viewmodel.ext.android.viewModel


class NewsServerFragment : Fragment(R.layout.fragment_news_server) {

    private var _binding: FragmentNewsServerBinding? = null
    private val binding: FragmentNewsServerBinding
        get() = _binding!!

    private val viewModel: NewsViewModel by viewModel()
    private var adapter: NewsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsServerBinding.inflate(inflater, container, false)
        initToolbar()
        initAdapter()
        initViewModel()
        return _binding?.root
    }

    private fun initToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initAdapter() {
        adapter = NewsAdapter()
        binding.rvNews.adapter = adapter
        adapter!!.addLoadStateListener { adapterLoadingErrorHandling(it) }
    }

    private fun initViewModel() {

        viewModel.run {
            news.collectIn(viewLifecycleOwner) {
                adapter?.submitData(it)
            }

            failure.collectIn(viewLifecycleOwner) {
                when (it) {
                    is Failure.NoInternet, is Failure.Api, is Failure.Timeout -> {
                        setupErrorItem(it)
                    }
                    is Failure.Unknown -> {
                        setupErrorItem(it)
                    }
                    else -> {
//                        binding.inclItemError.itemErrorMessage.text = failure.message
//                        binding.inclItemError.itemErrorRetryBtn.invisible()
                    }
                }
            }
        }

//        viewModel.loadingState.onEach {
//            if (it) {
//                binding.inclProgress.clProgress.visibility = View.VISIBLE
//            } else {
//                binding.inclProgress.clProgress.visibility = View.GONE
//            }
//        }.launchWhenStarted(lifecycleScope)
    }

    private fun setupErrorItem(failure: Failure) {
//        failure.retryAction()
//        binding.inclItemError.itemErrorMessage.text = failure.msg
//        binding.inclItemError.itemErrorRetryBtn.setOnClickListener { failure.retryAction() }
    }

    private fun adapterLoadingErrorHandling(combinedLoadStates: CombinedLoadStates) {
        if (combinedLoadStates.refresh is LoadState.Loading) {
            loadingUI(true)
        } else {
            loadingUI(false)
            val error = when {
                combinedLoadStates.prepend is LoadState.Error -> combinedLoadStates.prepend as LoadState.Error
                combinedLoadStates.source.prepend is LoadState.Error -> combinedLoadStates.prepend as LoadState.Error
                combinedLoadStates.append is LoadState.Error -> combinedLoadStates.append as LoadState.Error
                combinedLoadStates.source.append is LoadState.Error -> combinedLoadStates.append as LoadState.Error
                combinedLoadStates.refresh is LoadState.Error -> combinedLoadStates.refresh as LoadState.Error
                else -> null
            }
            error?.run {
                viewModel.handleFailure(this.error) {
                    adapter!!.retry()
                }
            }
        }
    }

    private fun loadingUI(isLoading: Boolean) {
        if (isLoading) {
            binding.inclProgress.clProgress.visibility = View.VISIBLE
        } else {
            binding.inclProgress.clProgress.visibility = View.GONE
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        adapter = null
        _binding = null
    }
}