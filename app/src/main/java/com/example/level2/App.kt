package com.example.level2

import android.app.Application
import com.example.level2.model.ContactsService

class App: Application() {
    val contactsService = ContactsService()
}