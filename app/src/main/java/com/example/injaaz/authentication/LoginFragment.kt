package com.example.injaaz.authentication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.injaaz.R
import com.example.injaaz.databinding.FragmentLoginBinding
import com.example.injaaz.MainActivity

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)

        binding.loginButton.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        binding.loginNoAccountTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }

        // Hide keyboard when user clicks outside the edit texts
        binding.loginEmailEditText.hideKeyboardWhenClickOutside(context)
        binding.loginPasswordEditText.hideKeyboardWhenClickOutside(context)

        return binding.root
    }
}