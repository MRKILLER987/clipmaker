package com.clipmaker.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = darkColorScheme()) {
                Surface(modifier = Modifier.fillMaxSize()) { HomeScreen() }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    var link by remember { mutableStateOf("") }
    var clips by remember { mutableFloatStateOf(3f) }
    var quality by remember { mutableStateOf("1080p") }
    var captions by remember { mutableStateOf(true) }
    var status by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Spacer(Modifier.height(24.dp))
        Text("ClipMaker", style = MaterialTheme.typography.headlineMedium)
        OutlinedTextField(
            value = link,
            onValueChange = { link = it },
            label = { Text("YouTube link") },
            modifier = Modifier.fillMaxWidth()
        )
        Text("Clips: " + clips.toInt())
        Slider(value = clips, onValueChange = { clips = it }, valueRange = 1f..10f, steps = 8)
        Text("Quality")
        listOf("720p", "1080p", "1440p", "4K").forEach { q ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = quality == q, onClick = { quality = q })
                Text(q)
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Switch(checked = captions, onCheckedChange = { captions = it })
            Spacer(Modifier.width(10.dp))
            Text("Captions")
        }
        Button(
            onClick = { status = "Stage 1 OK. Kaam agle stage me judega." },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Start") }
        if (status.isNotEmpty()) Text(status)
    }
}
