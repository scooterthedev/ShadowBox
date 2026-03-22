package com.scooter.shadowbox.UI.editor

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorScreen(
    connectionID: String? = null,
    onNavigateBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var host by remember { mutableStateOf("") }
    var port by remember { mutableStateOf(""22) }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val isEditMode = connectionID != null

}