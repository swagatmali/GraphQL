package com.swagatmali.graphql.presentation.continents

import androidx.compose.foundation.clickable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ContinentScreen(
    continentsViewModel: ContinentsViewModel,
    onContinentClick: (String) -> Unit
) {
    val uiState by continentsViewModel.uiState.collectAsStateWithLifecycle()
    if (uiState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    }
    if (uiState.error.isNotEmpty()) {
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center,
        ) {
            Text(text = uiState.error)
        }
    }

    uiState.data?.continents?.let { list ->

        LazyColumn(
            modifier = Modifier,
            contentPadding = PaddingValues(vertical = 8.dp) // 8.dp between each item
        ) {
            items(list) { item ->
                Card(modifier = Modifier
                    .clickable {
                        onContinentClick(item.code)
                    }
                    .padding(8.dp)
                    .fillMaxWidth()) {
                    Text(
                        text = item.name, style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }
    }
}