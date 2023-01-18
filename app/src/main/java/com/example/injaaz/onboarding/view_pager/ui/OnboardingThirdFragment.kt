package com.example.injaaz.onboarding.view_pager.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.injaaz.R
import com.example.injaaz.authentication.AuthenticationActivity
import com.example.injaaz.onboarding.splash.ON_BOARDING_SEEN
import com.example.injaaz.onboarding.splash.ON_BOARDING_SHARED_PREF

/**
 * A simple [Fragment] subclass.
 */
class OnboardingThirdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_onboarding_third_screen, container, false)

        view.findViewById<Button>(R.id.screen_3_finish_button).setOnClickListener {
            val intent = Intent(context, AuthenticationActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
            onBoardingFinished()
        }

        return view
    }

    private fun onBoardingFinished() {
        val sharedPref =
            requireActivity().getSharedPreferences(ON_BOARDING_SHARED_PREF, Context.MODE_PRIVATE)
                ?: return
        val edit = sharedPref.edit()
        edit.putBoolean(ON_BOARDING_SEEN, true)
        edit.apply()
    }
}