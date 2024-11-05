package com.VicAndSan.vuhbird.pages.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun signup (){
    var nombre by remember { mutableStateOf(" ") }
    var celular by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        //Logo
        Image(
            painter = painterResource(id = com.VicAndSan.vuhbird.R.drawable.vuhbird_icon), // Reemplaza con el recurso de tu logo
            contentDescription = "Logo",
            modifier = Modifier
                .size(100.dp)
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
            label = { Text("Label") },
            placeholder = { Text("Nombre Completo") }
        )
        Spacer(modifier = Modifier.height(5.dp))
        //Nro Celular
        TextField(
            value = celular,
            onValueChange = { celular = it },
            label = { Text("Label") },
            placeholder = { Text("Número Celular") }
        )
        Spacer(modifier = Modifier.height(5.dp))
        //Email
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Label") },
            placeholder = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(5.dp))
        //Contraseña
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Label") },
            placeholder = { Text("Contraseña") }
        )
    }

}