package ru.degus.mytelegram.ui.fragments

import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_code.*
import ru.degus.mytelegram.MainActivity
import ru.degus.mytelegram.R
import ru.degus.mytelegram.activities.RegisterActivity
import ru.degus.mytelegram.utilits.*

class EnterCodeFragment(val phoneNumber: String, val id: String) : Fragment(R.layout.fragment_enter_code) {

    override fun onStart() {
        super.onStart()
        (activity as RegisterActivity).title = phoneNumber
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

                REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dateMap)
                    .addOnCompleteListener {task ->
                    if (task.isSuccessful) {
                        showToast("Welcome")
                        (activity as RegisterActivity).replaceActivity(MainActivity())
                    } else showToast(task.exception?.message.toString())
                }
            } else showToast(it.exception?.message.toString())
        }
    }
}