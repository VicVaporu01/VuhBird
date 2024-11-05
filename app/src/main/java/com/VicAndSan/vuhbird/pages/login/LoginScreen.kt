package com.VicAndSan.vuhbird.pages.login
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextDecoration
import com.VicAndSan.vuhbird.R

@Composable
fun loginScreen() {
    var email = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.vuhbird_icon), // Reemplaza con el recurso de tu logo
            contentDescription = "Logo",
            modifier = Modifier
                .size(100.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Fit
        )

        // Nombre
        Text(
            text = "VUH BIRD",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Email input


        Spacer(modifier = Modifier.height(16.dp))

        // Login button
        Button(
            onClick = { /* Acción de inicio de sesión */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF83C180))
        ) {
            Text("Login", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Signup link
        Row {
            Text("¿No tienes cuenta?")
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Registrate",
                style = TextStyle(color = Color(0xFF2D3D1F), textDecoration = TextDecoration.Underline),
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}
