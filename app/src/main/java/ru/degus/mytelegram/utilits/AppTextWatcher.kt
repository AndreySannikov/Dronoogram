package ru.degus.mytelegram.utilits

import android.text.Editable
import android.text.TextWatcher

class AppTextWatcher(val onSucces: (Editable?) -> Unit): TextWatcher {
    override fun afterTextChanged(s: Editable?) {
        onSucces(s)
    }

    override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }
}