package com.asad.composeuipractice.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asad.composeuipractice.Routes
import com.asad.composeuipractice.ui.theme.ComposeUiPracticeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onNavigate: (String) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ComposeUiPractice") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CompositionLocalProvider(LocalRippleConfiguration provides RippleConfiguration(color = Color.Red)) {
                Button(
                    onClick = { onNavigate(Routes.BASICS) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Basics Screen")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    ComposeUiPracticeTheme {
        HomeScreen(onNavigate = {})
    }
}
