package ru.degus.mytelegram.ui.fragments.register

import androidx.fragment.app.Fragment
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_code.*
import ru.degus.mytelegram.R
import ru.degus.mytelegram.database.*
import ru.degus.mytelegram.utilits.*

class EnterCodeFragment(val phoneNumber: String, val id: String) :
    Fragment(R.layout.fragment_enter_code) {

    override fun onStart() {
        super.onStart()
        APP_ACTIVITY.title = phoneNumber
        register_input_code.addTextChangedListener(AppTextWatcher {
            if (register_input_code.text.toString().length >= 6) eneterCode()
        })
    }

    private fun eneterCode() {
        val credential = PhoneAuthProvider.getCredential(id, register_input_code.text.toString())
        AUTH.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                val uid = AUTH.currentUser?.uid.toString()
                val dateMap = mutableMapOf<String, Any>()
                dateMap[CHILD_ID] = uid
                dateMap[CHILD_PHONE] = phoneNumber
                dateMap[CHILD_USERNAME] = uid

                REF_DATABASE_ROOT.child(
                    NODE_PHONES
                ).child(phoneNumber).setValue(uid)
                    .addOnFailureListener { showToast(it.message.toString()) }
                    .addOnSuccessListener {
                        REF_DATABASE_ROOT.child(
                            NODE_USERS
                        ).child(uid).updateChildren(dateMap)
                            .addOnSuccessListener {
                                showToast("Welcome")
                                restartActivity()
                            }
                            .addOnFailureListener { showToast(it.message.toString()) }
                    }

            } else showToast(it.exception?.message.toString())
        }
    }
}