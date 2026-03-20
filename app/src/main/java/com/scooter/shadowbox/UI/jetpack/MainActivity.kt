package com.scooter.shadowbox.UI.jetpack

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingActionButtonMenu
import androidx.compose.material3.FloatingActionButtonMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleFloatingActionButton
import androidx.compose.material3.ToggleFloatingActionButtonDefaults.animateIcon
import androidx.compose.material3.animateFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.scooter.shadowbox.data.SSHActivity
import com.scooter.shadowbox.UI.ui.theme.ShadowBoxTheme
import kotlin.jvm.java

data class Project(val id: Int, val name: String)
@Composable
fun TopSection(projects: List<Project>) {
    val context = LocalContext.current
    Column(modifier = Modifier.padding(16.dp)) {
        projects.forEach { project ->
            Box(modifier = Modifier.fillMaxWidth()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(bottom = 8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = project.name,
                            modifier = Modifier.padding(16.dp)
                        )
                        IconButton(
                            onClick = {
                                val intent = Intent(context, SSHActivity::class.java)
                                context.startActivity(intent)
                            },
                            modifier = Modifier.align(alignment = Alignment.TopEnd).padding(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit Connection",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }

}

@Composable
@ExperimentalMaterial3ExpressiveApi
fun BottomBar(onAddProject: () -> Unit) {
    var fabMenuExpanded by remember { mutableStateOf(false) }
    val fabVisible = true
    val focusRequester = remember { FocusRequester() }

    FloatingActionButtonMenu(
        expanded = fabMenuExpanded,
        button = {
            ToggleFloatingActionButton(
                modifier =
                    Modifier.semantics {
                        traversalIndex = -1f
                        stateDescription =
                            if (fabMenuExpanded) "Expanded" else "Collapsed"
                        contentDescription = "Toggle menu"
                    }
                        .animateFloatingActionButton(
                            visible = fabVisible || fabMenuExpanded,
                            alignment = Alignment.BottomEnd,
                        )
                        .focusRequester(focusRequester),
                checked = fabMenuExpanded,
                onCheckedChange = { fabMenuExpanded = !fabMenuExpanded },
            ) {
                val imageVector by remember {
                    derivedStateOf {
                        if (checkedProgress > 0.5f) Icons.Filled.Close else Icons.Filled.Add
                    }
                }
                Icon(
                    painter = rememberVectorPainter(imageVector),
                    contentDescription = null,
                    modifier = Modifier.animateIcon({ checkedProgress }),
                )
            }
        }
    ) {
        FloatingActionButtonMenuItem(
            onClick = {
                onAddProject()
                fabMenuExpanded = false
            },
            icon = { Icon(Icons.Filled.Check, contentDescription = "Check") },
            text = { Text("Create a New Client") }
        )
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    val projects = remember {
        mutableStateListOf(
            Project(1, "First Project"),
        )
    }
    ShadowBoxTheme {
        Scaffold(
            bottomBar = {
                BottomBar(onAddProject = {
                    val newId = (projects.maxOfOrNull { it.id } ?: 0) + 1
                    projects.add(Project(newId, "New Project $newId"))
                })
            }
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                TopSection(projects = projects)
            }
        }
    }
}
