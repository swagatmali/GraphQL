package com.swagatmali.graphql.presentation.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun FetchDetailsScreen(
    fetchDetailsViewModel: FetchDetailsViewModel
) {

    val uiState by fetchDetailsViewModel.uiState.collectAsStateWithLifecycle()

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

    uiState.data?.continent?.let { continent ->
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())) {
            Text(text = continent.name, style = MaterialTheme.typography.headlineLarge)

            continent.countries.let { it ->
                it.forEach {
                    Text(text = it.name, style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }

}