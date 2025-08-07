package org.example.app.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.example.app.model.Note

// PUBLIC_INTERFACE
class NotesRepository(context: Context) {
    /** Handles CRUD and storage logic for Notes using SharedPreferences as local storage */
    private val PREF_NAME = "notes_prefs"
    private val KEY_NOTES = "notes"
    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val gson = Gson()

    fun getAllNotes(): List<Note> {
        val notesJson = prefs.getString(KEY_NOTES, null) ?: return emptyList()
        val type = object : TypeToken<List<Note>>() {}.type
        return gson.fromJson(notesJson, type)
    }

    fun saveAllNotes(notes: List<Note>) {
        prefs.edit().putString(KEY_NOTES, gson.toJson(notes)).apply()
    }

    fun getNote(id: String): Note? = getAllNotes().find { it.id == id }

    fun createNote(note: Note): List<Note> {
        val notes = getAllNotes().toMutableList()
        notes.add(0, note) // Most-recent at top
        saveAllNotes(notes)
        return notes
    }

    fun updateNote(updated: Note): List<Note> {
        val notes = getAllNotes().map {
            if (it.id == updated.id) updated.copy(updatedAt = System.currentTimeMillis()) else it
        }
        saveAllNotes(notes)
        return notes
    }

    fun deleteNote(id: String): List<Note> {
        val notes = getAllNotes().filter { it.id != id }
        saveAllNotes(notes)
        return notes
    }
}
