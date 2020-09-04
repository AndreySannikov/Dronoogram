package ru.degus.mytelegram.ui.message_recycler_view.view_holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.message_item_image.view.*
import ru.degus.mytelegram.database.CURRENT_UID
import ru.degus.mytelegram.ui.message_recycler_view.views.MessageView
import ru.degus.mytelegram.utilits.asTime
import ru.degus.mytelegram.utilits.downloadAndSetImage

class HolderImageMessage(view: View): RecyclerView.ViewHolder(view), MessageHolder{
    private val blocReceivedImageMessage: ConstraintLayout = view.bloc_received_image_message
    private val chatReceivedImage: ImageView = view.chat_received_image
    private val chatReceivedImageTime: TextView = view.chat_received_image_message_time

    private val blocUserImageMessage: ConstraintLayout = view.bloc_user_image_message
    private val chatUserImage: ImageView = view.chat_user_image
    private val chatUserImageTime: TextView = view.chat_user_image_message_time

    override fun drawMessage(view: MessageView) {
        if (view.from == CURRENT_UID) {
            blocReceivedImageMessage.visibility = View.GONE
            blocUserImageMessage.visibility = View.VISIBLE
            chatUserImage.downloadAndSetImage(view.fileUrl)
            chatUserImageTime.text = view.timeStamp.asTime()
        } else {
            blocReceivedImageMessage.visibility = View.VISIBLE
            blocUserImageMessage.visibility = View.GONE
            chatReceivedImage.downloadAndSetImage(view.fileUrl)
            chatReceivedImageTime.text = view.timeStamp.asTime()
        }
    }

    override fun onAttach(view: MessageView) {

    }

    override fun onDetach() {

    }
}