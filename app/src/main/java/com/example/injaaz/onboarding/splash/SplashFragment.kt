package com.example.injaaz.onboarding.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.injaaz.R
import com.example.injaaz.authentication.AuthenticationActivity

const val ON_BOARDING_SEEN = "seen"
const val ON_BOARDING_SHARED_PREF = "onBoarding"

/**
 * A simple [Fragment] subclass.
 */
class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash, container, false)

        if (onBoardingSeen()) {
            val intent = Intent(context, AuthenticationActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        } else {
            Handler(Looper.getMainLooper()).postDelayed({
                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
            }, 2000)
        }
        return view
    }

    private fun onBoardingSeen(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences(ON_BOARDING_SHARED_PREF, Context.MODE_PRIVATE)

        return sharedPref.getBoolean(ON_BOARDING_SEEN, false)
    }
}