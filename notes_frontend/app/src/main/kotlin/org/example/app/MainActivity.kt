package org.example.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.example.app.ui.NotesApp

// PUBLIC_INTERFACE
class MainActivity : ComponentActivity() {
    /** Entry point for the Notes app. */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesApp()
        }
    }
}
