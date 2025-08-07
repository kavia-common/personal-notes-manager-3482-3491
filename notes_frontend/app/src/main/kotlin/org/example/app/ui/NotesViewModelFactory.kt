package org.example.app.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.example.app.viewmodel.NotesViewModel

// PUBLIC_INTERFACE
class NotesViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NotesViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
