package ru.degus.mytelegram.ui.screens.groups

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.add_contacts_item.view.*
import kotlinx.android.synthetic.main.main_list_item.view.*
import ru.degus.mytelegram.R
import ru.degus.mytelegram.models.CommonModel
import ru.degus.mytelegram.ui.screens.single_chat.SingleChatFragment
import ru.degus.mytelegram.utilits.downloadAndSetImage
import ru.degus.mytelegram.utilits.replaceFragment
import ru.degus.mytelegram.utilits.showToast

class AddContactsAdapter: RecyclerView.Adapter<AddContactsAdapter.AddContactsHolder>() {

    private var mListItems = mutableListOf<CommonModel>()

    class AddContactsHolder(view: View): RecyclerView.ViewHolder(view) {
        val itemName: TextView = view.add_contacts_item_name
        val itemLastMessage: TextView = view.add_contacts_last_message
        val itemPhoto: CircleImageView = view.add_contacts_item_photo
        val itemChoice: CircleImageView = view.add_contacts_item_choice
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddContactsHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.add_contacts_item, parent, false)

        val holder = AddContactsHolder(view)
        holder.itemView.setOnClickListener {
            if (mListItems[holder.adapterPosition].choice) {
                holder.itemChoice.visibility =View.INVISIBLE
                mListItems[holder.adapterPosition].choice = false
                AddContactsFragment.listContacts.remove(mListItems[holder.adapterPosition])
            } else {
                holder.itemChoice.visibility =View.VISIBLE
                mListItems[holder.adapterPosition].choice = true
                AddContactsFragment.listContacts.add(mListItems[holder.adapterPosition])
            }
        }
        return holder
    }

    override fun getItemCount(): Int = mListItems.size

    override fun onBindViewHolder(holder: AddContactsHolder, position: Int) {
        holder.itemName.text = mListItems[position].fullname
        holder.itemLastMessage.text = mListItems[position].lastMessage
        holder.itemPhoto.downloadAndSetImage(mListItems[position].photoUrl)
    }

    fun updateListItems(item: CommonModel) {
        mListItems.add(item)
        notifyItemInserted(mListItems.size)
    }
}
