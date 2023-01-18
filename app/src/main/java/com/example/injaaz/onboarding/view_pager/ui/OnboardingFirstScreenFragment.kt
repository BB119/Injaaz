package com.example.injaaz.onboarding.view_pager.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.example.injaaz.R


/**
 * A simple [Fragment] subclass.
 */
class OnboardingFirstScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_onboarding_first_screen, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.onboarding_view_pager)
        view.findViewById<Button>(R.id.screen_1_next_button).setOnClickListener {
            viewPager?.currentItem = 1
        }

        return view
    }
}