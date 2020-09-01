package ru.degus.mytelegram.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.degus.mytelegram.R
import ru.degus.mytelegram.databinding.FragmentChatsBinding
import ru.degus.mytelegram.utilits.APP_ACTIVITY


class ChatsFragment : Fragment(R.layout.fragment_chats) {

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Chats"
        APP_ACTIVITY.mAppDrawer.enableDrawer()
    }
}