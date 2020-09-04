package ru.degus.mytelegram.ui.screens

import androidx.fragment.app.Fragment
import ru.degus.mytelegram.R
import ru.degus.mytelegram.databinding.FragmentChatsBinding
import ru.degus.mytelegram.utilits.APP_ACTIVITY
import ru.degus.mytelegram.utilits.hideKeyboard


class ChatsFragment : Fragment(R.layout.fragment_chats) {

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Chats"
        APP_ACTIVITY.mAppDrawer.enableDrawer()
        hideKeyboard()
    }
}