package org.example.app.ui

import android.app.Application
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NoteAdd
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.app.viewmodel.NotesViewModel

enum class BottomTab(val label: String, val icon: ImageVector) {
    Notes("Notes", Icons.Filled.NoteAdd),
    Tags("Tags", Icons.Filled.Tag),
    Settings("Settings", Icons.Filled.Settings)
}

// PUBLIC_INTERFACE
@Composable
fun NotesApp() {
    val vm: NotesViewModel = viewModel(factory = NotesViewModelFactory(LocalContext.current.applicationContext as Application))

    var selectedTab by remember { mutableStateOf(BottomTab.Notes) }

    NotesTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Notes") },
                    actions = {
                        if (selectedTab == BottomTab.Notes) {
                            IconButton(onClick = { vm.setSearchQuery("") }) {
                                Icon(Icons.Filled.Search, contentDescription = "Search")
                            }
                        }
                    }
                )
            },
            bottomBar = {
                NavigationBar {
                    BottomTab.values().forEach { tab ->
                        NavigationBarItem(
                            selected = selectedTab == tab,
                            onClick = { selectedTab = tab },
                            icon = { Icon(tab.icon, contentDescription = tab.label) },
                            label = { Text(tab.label) }
                        )
                    }
                }
            }
        ) { innerPadding ->
            Box(Modifier.padding(innerPadding).fillMaxSize()) {
                when (selectedTab) {
                    BottomTab.Notes -> NotesScreen(vm)
                    BottomTab.Tags -> TagsScreen(vm)
                    BottomTab.Settings -> SettingsScreen()
                }
            }
        }
    }
}
