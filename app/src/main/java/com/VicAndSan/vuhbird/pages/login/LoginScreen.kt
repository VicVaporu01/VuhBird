package com.VicAndSan.vuhbird.pages.login

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.material3.TextField
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.VicAndSan.vuhbird.R
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(navigateToSignUp: () -> Unit, navigateToMain: () -> Unit, auth: FirebaseAuth) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.vuhbird_icon), // Reemplaza con el recurso de tu logo
            contentDescription = "Logo",
            modifier = Modifier
                .size(150.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(32.dp))

        // Email input
        TextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(5.dp))
        // Password input
        TextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Contraseña") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Login button
        Button(
            onClick = {
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
                    if (it.isSuccessful){
                        //Navega a main
                        Log.i("aris", "LOGIN OK")
                        navigateToMain()
                    }else{
                        //Error
                        Log.i("aris", "LOGIN KO")
                    }
                }
            }
        ) {
            Text("Login", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Signup link
        Row {
            Text("¿No tienes cuenta?")
            Spacer(modifier = Modifier.width(4.dp))
            TextButton(onClick = { navigateToSignUp() }) {
                Text(
                    text = "Registrate",
                    style = TextStyle(color = Color(0xFF2D3D1F), textDecoration = TextDecoration.Underline)
                )
            }
        }
    }
}