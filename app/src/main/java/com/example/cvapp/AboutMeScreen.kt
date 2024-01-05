package com.example.cvapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun AboutMeScreen(navController: NavHostController) {
    val context = LocalContext.current

    // Load entries
    var entries by remember(context) { mutableStateOf(loadEntries(context)) }


    // Form state
    var additionalEntry by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.aboutME),
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier
                .padding(top = 10.dp, bottom = 10.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            style = MaterialTheme.typography.bodyLarge,
            text = stringResource(id = R.string.text_aboutme),
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Form for adding additional entries
        OutlinedTextField(
            value = additionalEntry,
            onValueChange = { additionalEntry = it },
            label = { Text(stringResource(id = R.string.text_add))},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Button to add entry
        Button(
            onClick = {
                // Save the additional entry
                saveEntry(context, additionalEntry)
                // Reload entries
                entries = loadEntries(context)
                // Clear the input field
                additionalEntry = ""
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(stringResource(id = R.string.text_add2),)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display existing entries
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(stringResource(id = R.string.text_add3),)
            entries.forEach { entry ->
                Text("- $entry")
            }
        }
    }
}

private const val PREFS_NAME = "cv_prefs"
private const val KEY_ENTRIES = "entries"

private fun loadEntries(context: Context): List<String> {
    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    return prefs.getStringSet(KEY_ENTRIES, emptySet())?.toList() ?: emptyList()
}
private fun saveEntry(context: Context, entry: String) {
    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    val currentEntries = prefs.getStringSet(KEY_ENTRIES, mutableSetOf()) ?: mutableSetOf()
    currentEntries.add(entry)
    prefs.edit().putStringSet(KEY_ENTRIES, currentEntries).apply()
}
