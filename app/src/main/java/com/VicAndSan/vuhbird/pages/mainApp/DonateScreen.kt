package com.VicAndSan.vuhbird.pages.mainApp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.VicAndSan.vuhbird.R
import com.VicAndSan.vuhbird.models.RemoteGetBirdByIdResult
import com.VicAndSan.vuhbird.services.RetrofitClient

@Composable
fun DonateScreen(paddingValues: PaddingValues, birdId: Int? = 1) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }

    var bird by remember {
        mutableStateOf(
            RemoteGetBirdByIdResult(
                family = "",
                id = 0,
                images = emptyList(),
                lengthMax = "",
                lengthMin = "",
                name = "",
                order = "",
                recordings = emptyList(),
                region = emptyList(),
                sciName = "",
                status = "",
                wingspanMax = "",
                wingspanMin = ""
            )
        )
    }

    LaunchedEffect(Unit) {
        if (birdId != null) {
            isLoading = true
            try {
                val response =
                    RetrofitClient.birdService.getBird(
                        "2253cc00-3f0b-4ac1-a835-63148a2be200",
                        birdId = birdId
                    )
                bird = response
            } catch (e: Exception) {
                error = e.message
            }
            isLoading = false
        }
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(color = Color(0xFFD5EACE))
                .padding(16.dp)
        ) {
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
                        "Error bringing bird...$error, birdId: $birdId",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                else -> {
                    Image(
                        painter = rememberAsyncImagePainter(bird.images.firstOrNull()),
                        contentDescription = bird.sciName,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(16.dp)
                            .background(Color.LightGray, shape = RoundedCornerShape(16.dp))
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "${bird.name} (${bird.sciName})",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Bird Info
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_location),
                            contentDescription = "Location Icon",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Black
                        )
                        Text(
                            text = bird.region.joinToString(", "),
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_shield),
                            contentDescription = "Protection Icon",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Black
                        )
                        Text(
                            text = "Protected for Vuh-Bird",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_warning),
                            contentDescription = "Status Icon",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Black
                        )
                        Text(
                            text = "Status of the bird: ${bird.status}",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DonateScreenPreview() {
    DonateScreen(
        paddingValues = PaddingValues(0.dp),
        birdId = TODO()
    )
}