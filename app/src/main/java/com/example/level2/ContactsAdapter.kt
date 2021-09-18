package com.example.level2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.level2.databinding.ItemContactBinding
import com.example.level2.model.Contacts

interface ContactActionListener {

    fun onContactDelete(contact: Contacts)

}

class ContactsAdapter(
    private val actionListener: ContactActionListener) : RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>(),
    View.OnClickListener {

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

        binding.root.setOnClickListener(this)
        binding.moreImageViewButton.setOnClickListener(this)

        return ContactsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val contact = contacts[position]

        with(holder.binding) {
            holder.itemView.tag = contact
            moreImageViewButton.tag = contact

            userNameTextView.text = contact.name
            userCompanyTextView.text = contact.profession
            if (contact.photo.isNotBlank()) {
                Glide.with(photoImageView.context)
                    .load(contact.photo)
                    .circleCrop()
                    .placeholder(R.drawable.ic_user_avatar)
                    .error(R.drawable.ic_user_avatar)
                    .into(photoImageView)
            } else {
                Glide.with(photoImageView.context).clear(photoImageView)
                photoImageView.setImageResource(R.drawable.ic_user_avatar)
            }
        }
    }

    override fun getItemCount(): Int = contacts.size

    override fun onClick(v: View) {
        val contact = v.tag as Contacts
        actionListener.onContactDelete(contact)
    }

}