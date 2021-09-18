package com.example.level2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.level2.databinding.ActivityMainBinding
import com.example.level2.model.Contacts
import com.example.level2.model.ContactsListener
import com.example.level2.model.ContactsService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ContactsAdapter

    private val contactsService: ContactsService
        get() = (applicationContext as App).contactsService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ContactsAdapter(object : ContactActionListener{
            override fun onContactDelete(contact: Contacts) {
                contactsService.deleteUser(contact)
            }
        })

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        contactsService.addListener(contactsListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        contactsService.removeListener(contactsListener)
    }

    private val contactsListener: ContactsListener = {
        adapter.contacts = it
    }
}