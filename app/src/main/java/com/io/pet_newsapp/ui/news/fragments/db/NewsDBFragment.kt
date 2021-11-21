package com.io.pet_newsapp.ui.news.fragments.db

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.io.pet_newsapp.R
import com.io.pet_newsapp.databinding.FragmentNewsDBBinding
import com.io.pet_newsapp.ui.news.fragments.server.NewsAdapter
import com.io.pet_newsapp.ui.news.fragments.server.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class NewsDBFragment : Fragment(R.layout.fragment_news_d_b) {

    private var _binding: FragmentNewsDBBinding? = null
    private val binding: FragmentNewsDBBinding
        get() = _binding!!

    private val viewModel: NewsViewModel by viewModel()
    private var adapter: NewsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsDBBinding.inflate(inflater, container, false)
        initToolbar()
        initAdapter()
        initViewModel()
        return _binding?.root
    }

    private fun initToolbar() {
        binding.inclDevelop.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initAdapter() {
//        adapter = NewsAdapter()
//        binding.rvNewsDB.adapter = adapter
    }


    private fun initViewModel() {

    }

    override fun onDestroy() {
        super.onDestroy()
        adapter = null
        _binding = null
    }
}