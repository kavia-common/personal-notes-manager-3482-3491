package org.example.app.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.example.app.model.Note
import org.example.app.viewmodel.NotesViewModel

// PUBLIC_INTERFACE
@Composable
fun NotesScreen(vm: NotesViewModel) {
    var showEditor by remember { mutableStateOf(false) }
    var editingNote by remember { mutableStateOf<Note?>(null) }
    val notes = vm.getFilteredNotes()

    Column {
        // Search bar
        OutlinedTextField(
            value = vm.searchQuery,
            onValueChange = { vm.setSearchQuery(it) },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
            placeholder = { Text("Search notes") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        // Add note button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = {
                editingNote = null
                showEditor = true
            }, content = {
                Icon(Icons.Filled.Add, contentDescription = "Add Note")
                Spacer(Modifier.width(4.dp))
                Text("New Note")
            })
        }
        // Notes list
        LazyColumn(Modifier.weight(1f, fill = true)) {
            items(notes.size) { idx ->
                val note = notes[idx]
                NoteCard(
                    note = note,
                    onTap = {
                        editingNote = note
                        showEditor = true
                    },
                    onDelete = { vm.deleteNote(note) }
                )
            }
        }
    }

    if (showEditor) {
        NoteEditorDialog(
            note = editingNote,
            onDismiss = { showEditor = false },
            onSave = { title, content, tags ->
                if (editingNote == null) {
                    vm.createNote(title, content, tags)
                } else {
                    vm.updateNote(editingNote!!.copy(title = title, content = content, tags = tags))
                }
                showEditor = false
            }
        )
    }
}

@Composable
fun NoteCard(note: Note, onTap: () -> Unit, onDelete: () -> Unit) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable { onTap() }
    ) {
        Column(Modifier.padding(12.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = note.title, style = MaterialTheme.typography.titleMedium, maxLines = 1, overflow = TextOverflow.Ellipsis)
                IconButton(onClick = onDelete) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete")
                }
            }
            Spacer(Modifier.height(4.dp))
            Text(
                text = note.content,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium
            )
            if (note.tags.isNotEmpty()) {
                Spacer(Modifier.height(2.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    note.tags.forEach { tag ->
                        AssistChip(onClick = {}, label = { Text(text = tag) })
                    }
                }
            }
        }
    }
}
