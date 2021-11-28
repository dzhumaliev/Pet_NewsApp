package com.io.pet_newsapp.ui.news.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.io.pet_newsapp.R
import com.io.pet_newsapp.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        initListeners()
        return _binding?.root
    }

    private fun initListeners() {
        binding.btnServer.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_newsServerFragment)
        }

        binding.btnDb.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_newsDBFragment)
        }

    }


}