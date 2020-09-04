package ru.degus.mytelegram.ui.screens

import androidx.fragment.app.Fragment
import ru.degus.mytelegram.utilits.APP_ACTIVITY

open class BaseFragment(layout: Int) : Fragment(layout) {

    override fun onStart() {
        super.onStart()
        APP_ACTIVITY.mAppDrawer.disableDrawer()
    }
}