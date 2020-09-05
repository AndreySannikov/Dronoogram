package ru.degus.mytelegram.ui.screens.main_list

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_main_list.*
import ru.degus.mytelegram.R
import ru.degus.mytelegram.database.*
import ru.degus.mytelegram.models.CommonModel
import ru.degus.mytelegram.utilits.APP_ACTIVITY
import ru.degus.mytelegram.utilits.AppValueEventListener
import ru.degus.mytelegram.utilits.hideKeyboard


class MainListFragment : Fragment(R.layout.fragment_main_list) {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: MainListAdapter
    private val mRefMainList = REF_DATABASE_ROOT.child(NODE_MAIN_LIST).child(CURRENT_UID)
    private val mRefUsers = REF_DATABASE_ROOT.child(NODE_USERS)
    private val mRefMessages = REF_DATABASE_ROOT.child(NODE_MESSAGES).child(CURRENT_UID)
    private var mListItems = listOf<CommonModel>()

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Chats"
        APP_ACTIVITY.mAppDrawer.enableDrawer()
        hideKeyboard()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mRecyclerView = main_list_recycler_view
        mAdapter = MainListAdapter()

        mRefMainList.addListenerForSingleValueEvent(AppValueEventListener { dataSnapshot ->
            mListItems = dataSnapshot.children.map { it.getCommonModel() }
            mListItems.forEach { model ->

                mRefUsers.child(model.id).addListenerForSingleValueEvent(AppValueEventListener { usersSnapshot ->
                    val newModel = usersSnapshot.getCommonModel()

                    mRefMessages.child(model.id).limitToLast(1)
                        .addListenerForSingleValueEvent(AppValueEventListener { messagesSnapshot ->
                            val tempList = messagesSnapshot.children.map { it.getCommonModel() }

                            if (tempList.isEmpty()) newModel.lastMessage = "Chat cleared"
                            else newModel.lastMessage = tempList[0].text

                            if (newModel.fullname.isEmpty()) {
                                newModel.fullname = newModel.phone
                            }

                            mAdapter.updateListItems(newModel)
                        })
                })
            }

        })

        mRecyclerView.adapter = mAdapter
    }
}