package com.example.injaaz.onboarding.view_pager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.injaaz.R
import com.example.injaaz.onboarding.view_pager.ui.OnboardingFirstScreenFragment
import com.example.injaaz.onboarding.view_pager.ui.OnboardingSecondFragment
import com.example.injaaz.onboarding.view_pager.ui.OnboardingThirdFragment

/**
 * A simple [Fragment] subclass.
 */
class ViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)
        val fragmentList = arrayListOf(
            OnboardingFirstScreenFragment(),
            OnboardingSecondFragment(),
            OnboardingThirdFragment()
        )

        val adapter = OnboardingViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)

        view.findViewById<ViewPager2>(R.id.onboarding_view_pager).adapter = adapter

        return view
    }
}