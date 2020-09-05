package ru.degus.mytelegram.ui.screens.main_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.main_list_item.view.*
import ru.degus.mytelegram.R
import ru.degus.mytelegram.models.CommonModel
import ru.degus.mytelegram.ui.screens.single_chat.SingleChatFragment
import ru.degus.mytelegram.utilits.downloadAndSetImage
import ru.degus.mytelegram.utilits.replaceFragment

class MainListAdapter: RecyclerView.Adapter<MainListAdapter.MainListHolder>() {

    private var mListItems = mutableListOf<CommonModel>()

    class MainListHolder(view: View): RecyclerView.ViewHolder(view) {
        val itemName: TextView = view.main_list_item_name
        val itemLastMessage: TextView = view.main_list_last_message
        val itemPhoto: CircleImageView = view.main_list_item_photo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_list_item, parent, false)

        val holder = MainListHolder(view)
        holder.itemView.setOnClickListener {
            replaceFragment(SingleChatFragment(mListItems[holder.adapterPosition]))
        }
        return holder
    }

    override fun getItemCount(): Int = mListItems.size

    override fun onBindViewHolder(holder: MainListHolder, position: Int) {
        holder.itemName.text = mListItems[position].fullname
        holder.itemLastMessage.text = mListItems[position].lastMessage
        holder.itemPhoto.downloadAndSetImage(mListItems[position].photoUrl)
    }

    fun updateListItems(item: CommonModel) {
        mListItems.add(item)
        notifyItemInserted(mListItems.size)
    }
}
