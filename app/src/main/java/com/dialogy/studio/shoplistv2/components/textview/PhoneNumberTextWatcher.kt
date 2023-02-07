package com.dialogy.studio.shoplistv2.components.textview

import android.text.Editable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.TextWatcher
import android.widget.EditText

class PhoneNumberTextWatcher(private val editText: EditText, private val pattern: String, val afterChanged: (phoneNumber: String) -> Unit) : TextWatcher {
    init {
        val maxLength = pattern.length
        editText.filters = arrayOf<InputFilter>(LengthFilter(maxLength))
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(phoneNumber: CharSequence, start: Int, before: Int, count: Int) {
        val phone = StringBuilder(phoneNumber)
        if (count > 0 && !isValid(phone.toString())) {
            for (i in phone.indices) {
                val c = pattern[i]
                if (c != '#' && c != phone[i]) {
                    phone.insert(i, c)
                }
            }
            editText.setText(phone)
            editText.setSelection(editText.text.length)
        }
    }

    override fun afterTextChanged(s: Editable) {
        afterChanged(s.toString())
    }

    private fun isValid(phone: String): Boolean {
        for (i in phone.indices) {
            val c = pattern[i]
            if (c == '#') continue
            if (c != phone[i]) {
                return false
            }
        }
        return true
    }
}