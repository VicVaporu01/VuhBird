package com.VicAndSan.vuhbird.pages.mainApp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DonateScreen(paddingValues: PaddingValues) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(color = Color(0xFFD5EACE))
        ) {
            Text("Donate")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DonateScreenPreview() {
    DonateScreen(paddingValues = PaddingValues(0.dp))
}