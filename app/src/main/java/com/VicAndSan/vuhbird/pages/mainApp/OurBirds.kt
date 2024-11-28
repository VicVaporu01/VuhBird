package com.VicAndSan.vuhbird.pages.mainApp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.VicAndSan.vuhbird.models.Entity
import com.VicAndSan.vuhbird.services.RetrofitClient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OurBirds(paddingValues: PaddingValues, navigateToDonateScreen: (Int) -> Unit) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    var birds by remember { mutableStateOf(emptyList<Entity>()) }

    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    //Conexión a la API
    LaunchedEffect(Unit) {
        isLoading = true
        try {
            val response =
                RetrofitClient.birdService.getBirds("2253cc00-3f0b-4ac1-a835-63148a2be200")
            birds = response.entities
        } catch (e: Exception) {
            error = e.message
        }
        isLoading = false
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color(0xFFD5EACE))
        )
        {
            SearchBar(
                query = text,
                onQueryChange = {
                    text = it
                },
                onSearch = {
                    active = false
                },
                active = active,
                onActiveChange = {
                    active = it
                }, modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 16.dp)
            ) { }

            // Error handling
            when {
                isLoading -> {
                    Text(
                        "Loading birds...",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                error != null -> {
                    Text(
                        "Error bringing birds...",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                birds.isEmpty() -> {
                    Text(
                        "No birds found...",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        val validBirds = birds.filter { bird ->
                            bird.images.isNotEmpty() && bird.name != null && bird.region.isNotEmpty()
                        }

                        items(validBirds) { bird ->
                            BirdCard(bird = bird, onClick = {
                                bird.id?.let { navigateToDonateScreen(it) }
                            })
                        }
                    }
                }
            }

        }
    }
}
//Composables para la gestión de las targetas de aves
@Composable
fun BirdCard(bird: Entity, onClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(bird.images.getOrNull(0) ?: ""),
                contentDescription = bird.name ?: "Unknown Bird",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = bird.name ?: "Unknown Bird",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = bird.region.getOrNull(0) ?: "Unknown Region",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun OurBirdsPreview() {
    OurBirds(
        paddingValues = PaddingValues(0.dp),
        navigateToDonateScreen = TODO()
    )
}