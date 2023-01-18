package com.example.injaaz.authentication

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * Hide keyboard when user clicks outside edit text
 */
fun EditText.hideKeyboardWhenClickOutside(context: Context?) {
    this.setOnFocusChangeListener { view, _ ->
        val inputMethodManager =
            context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}