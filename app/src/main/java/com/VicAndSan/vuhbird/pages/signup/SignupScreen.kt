package com.VicAndSan.vuhbird.pages.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun signup (){
    var nombre by remember { mutableStateOf(" ") }
    var celular by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //Logo
        Image(
            painter = painterResource(id = com.VicAndSan.vuhbird.R.drawable.vuhbird_icon), // Reemplaza con el recurso de tu logo
            contentDescription = "Logo",
            modifier = Modifier
                .size(150.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Fit
        )
        //H1
        Text(
            text = "Registrate",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(32.dp))
        //Nombre
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            placeholder = { Text("Nombre Completo") }
        )
        Spacer(modifier = Modifier.height(5.dp))
        //Nro Celular
        TextField(
            value = celular,
            onValueChange = { celular = it },
            placeholder = { Text("Número Celular") }
        )
        Spacer(modifier = Modifier.height(5.dp))
        //Email
        TextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(5.dp))
        //Contraseña
        TextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Contraseña") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Login button
        Button(
            onClick = { /* Acción de inicio de sesión */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF83C180))
        ) {
            Text("Sign In", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Login link
        Row {
            Text("¿Ya tienes cuenta?")
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Login",
                style = TextStyle(color = Color(0xFF2D3D1F), textDecoration = TextDecoration.Underline),
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }

}