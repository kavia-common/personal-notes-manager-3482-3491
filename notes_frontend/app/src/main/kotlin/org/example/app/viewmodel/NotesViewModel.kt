package org.example.app.viewmodel

import android.app.Application
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.example.app.data.NotesRepository
import org.example.app.model.Note

// PUBLIC_INTERFACE
class NotesViewModel(application: Application) : AndroidViewModel(application) {
    /** ViewModel for managing notes data and business logic. */
    private val repo = NotesRepository(application)
    var allNotes by mutableStateOf<List<Note>>(listOf())
        private set
    var searchQuery by mutableStateOf("")
    var tagFilter by mutableStateOf<String?>(null)

    init {
        loadNotes()
    }

    fun loadNotes() {
        allNotes = repo.getAllNotes().sortedByDescending { it.updatedAt }
    }

    fun createNote(title: String, content: String, tags: List<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.createNote(Note(title = title, content = content, tags = tags))
            loadNotesOnMain()
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateNote(note)
            loadNotesOnMain()
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteNote(note.id)
            loadNotesOnMain()
        }
    }

    fun getFilteredNotes(): List<Note> {
        var notes = allNotes
        tagFilter?.let { tag ->
            notes = notes.filter { it.tags.contains(tag) }
        }
        if (searchQuery.isNotBlank()) {
            val q = searchQuery.trim().lowercase()
            notes = notes.filter { it.title.lowercase().contains(q) || it.content.lowercase().contains(q) }
        }
        return notes
    }

    fun getAllTags(): List<String> {
        return allNotes.flatMap { it.tags }.distinct().sorted()
    }

    fun setTagFilter(tag: String?) {
        tagFilter = tag
    }
    fun setSearchQuery(query: String) {
        searchQuery = query
    }

    private fun loadNotesOnMain() {
        // This ensures notes reloaded on UI thread
        viewModelScope.launch(Dispatchers.Main) {
            loadNotes()
        }
    }
}
