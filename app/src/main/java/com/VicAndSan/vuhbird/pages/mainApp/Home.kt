package com.VicAndSan.vuhbird.pages.mainApp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(paddingValues: PaddingValues) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color(0xFFD5EACE))
        ) {
            // Search bar
            val searchText = remember { mutableStateOf("") }
            SearchBar(
                query = searchText.value,
                onQueryChange = { searchText.value = it },
                onSearch = { /* Search action */ },
                active = false,
                onActiveChange = { },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text("Buscar", color = Color.Black)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Title
            Text(
                text = "Noticias Actuales",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp)
            )

            // Grid for news
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                items(mockNewsData()) { news ->
                    NewsCard(news)
                }
            }
        }
    }
}

@Composable
fun NewsCard(news: NewsItem) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(news.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = news.title,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = news.description,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

// Mock data
data class NewsItem(val title: String, val description: String, val imageUrl: String)

fun mockNewsData(): List<NewsItem> {
    return listOf(
        NewsItem(
            "Parrot News 2018",
            "Special Edition",
            "https://media.cnn.com/api/v1/images/stellar/prod/lincolnshire.jpg?c=16x9&q=h_653,w_1160,c_fill/f_webp"
        ),
        NewsItem(
            "Love of Conures",
            "Explre the world of conures",
            "https://www.thesprucepets.com/thmb/xpTZxnQ_6wGR_DWmpe0TSLX8M1k=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/GettyImages-545003266-58a6e9175f9b58a3c918f24c.jpg"
        ),
        NewsItem(
            "Rehabilitated Macaws in Colombia",
            "This is how the rehabilitation of macaws took place in Colombia",
            "https://images.fineartamerica.com/images/artworkimages/mediumlarge/1/parrots-colombia-victor-hugo.jpg"
        ),
        NewsItem(
            "Exotic Birds",
            "Discover the most exotic birds in the world",
            "https://th.bing.com/th/id/R.8337f540c28bc91a397ab5d8318d1560?rik=IyjT5a4mAW2RCA&riu=http%3a%2f%2f3.bp.blogspot.com%2f-i0B5H0Kcbv4%2fU86hbmU5vII%2fAAAAAAAAC_4%2fPH-eW0O1i94%2fs1600%2florikeets.jpg&ehk=RGXf%2fGdeiw2kGEdPf3IraZ4s9xXQe6LF5AXqDyUkqdc%3d&risl=&pid=ImgRaw&r=0"
        )
    )
}

@Preview(showSystemUi = true)
@Composable
fun HomePreview() {
    Home(paddingValues = PaddingValues(0.dp))
}