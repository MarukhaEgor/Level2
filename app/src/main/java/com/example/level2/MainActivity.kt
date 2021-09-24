package com.example.level2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.level2.databinding.ActivityMainBinding
import com.example.level2.model.Contacts
import com.example.level2.model.ContactsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ContactsAdapter

    private val contactsViewModel by lazy { ViewModelProviders.of(this).get(ContactsViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ContactsAdapter(object : ContactActionListener{
            override fun onContactDelete(contact: Contacts) {
                contactsViewModel.deleteUser(contact)
            }
        })

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        contactsViewModel.getListContacts().observe(
            this,
            {
                it?.let {
                    adapter.refreshUsers(it)
                }
            }
        )

    }


}