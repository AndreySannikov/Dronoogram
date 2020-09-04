package ru.degus.mytelegram.ui.screens

import kotlinx.android.synthetic.main.fragment_change_name.*
import ru.degus.mytelegram.R
import ru.degus.mytelegram.database.*
import ru.degus.mytelegram.utilits.*

class ChangeNameFragment : BaseChangeFragment(R.layout.fragment_change_name) {

    override fun onResume() {
        super.onResume()
        initFullnameList()
    }

    private fun initFullnameList() {
        val fullnameList = USER.fullname.split(" ")
        if (fullnameList.size > 1) {
            settings_input_name.setText(fullnameList[0])
            settings_input_surname.setText(fullnameList[1])
        } else settings_input_name.setText(fullnameList[0])
    }

    override fun change() {
        val name: String = settings_input_name.text.toString()
        val surname: String = settings_input_surname.text.toString()
        if (name.isEmpty()) {
            showToast(getString(R.string.settings_toast_name_is_empty))
        } else {
            val fullname = "$name $surname"
            setNameToDataBase(fullname)
        }
    }
}

