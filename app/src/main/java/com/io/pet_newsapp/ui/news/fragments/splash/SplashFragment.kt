package com.io.pet_newsapp.ui.news.fragments.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.io.pet_newsapp.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startDelay()
    }

    private fun startDelay() {
        lifecycleScope.launch {
            delay(2_000L)
            findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
        }
    }

}