package org.example.app.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.app.viewmodel.NotesViewModel

// PUBLIC_INTERFACE
@Composable
fun TagsScreen(vm: NotesViewModel) {
    val tags = vm.getAllTags()
    var selectedTag by remember { mutableStateOf<String?>(null) }

    Column(Modifier.fillMaxSize()) {
        Text(
            text = "Tags",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(16.dp)
        )
        LazyColumn {
            items(tags.size) { idx ->
                val tag = tags[idx]
                ListItem(
                    headlineContent = { Text(text = tag) },
                    modifier = Modifier
                        .clickable {
                            selectedTag = if (selectedTag == tag) null else tag
                            vm.setTagFilter(selectedTag)
                        }
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
        }
        if (selectedTag != null) {
            Button(
                onClick = { 
                    vm.setTagFilter(null)
                    selectedTag = null
                },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text("Clear Tag Filter")
            }
        }
    }
}
