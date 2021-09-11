package com.example.level2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.level2.databinding.ItemContactBinding
import com.example.level2.model.Contacts

class ContactsAdapter: RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {

    var contacts: List<Contacts> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    class ContactsViewHolder(
        val binding: ItemContactBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(inflater, parent, false)
        return ContactsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val contact = contacts[position]

        with(holder.binding) {
            holder.itemView.tag = contact
            moreImageViewButton.tag = contact
            }
    }

    override fun getItemCount(): Int = contacts.size

}