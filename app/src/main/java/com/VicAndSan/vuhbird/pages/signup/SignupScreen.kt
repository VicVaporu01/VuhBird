package com.VicAndSan.vuhbird.pages.signup

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun SignUpScreen (navigateToLogin: () -> Unit, auth: FirebaseAuth, db:FirebaseFirestore){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

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
            value = name,
            onValueChange = { name = it },
            placeholder = { Text("Nombre Completo") }
        )
        Spacer(modifier = Modifier.height(5.dp))
        //Nro Celular
        TextField(
            value = phone,
            onValueChange = { phone = it },
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

        // Signup button
        Button(
            onClick = {
                registerUser(email, password, name, phone){
                    if (it){
                        Log.i("Aria", "SUCCES")
                    }else{
                        Log.i("Aria", "FAIL")
                    }
                }
            },
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
            TextButton(onClick = { navigateToLogin() }) {
                Text(
                    text = "Login",
                    style = TextStyle(color = Color(0xFF2D3D1F), textDecoration = TextDecoration.Underline)
                )
            }
        }
    }
}

fun registerUser (email: String, password: String, name: String, phoneNumber: String, onResult: (Boolean)-> Unit){
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()

    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener{
            if (it.isSuccessful){
                val userId = it.result?.user?.uid
                val user = mapOf(
                    "email" to email,
                    "password" to password,
                    "name" to name,
                    "phoneNumber" to phoneNumber,
                    "favoriteBirds" to emptyList<String>(),
                    "donations" to emptyList<String>()
                )
                userId?.let {
                    db.collection("users").document(it)
                        .set(user)
                        .addOnSuccessListener {
                            onResult(true)
                        }
                        .addOnFailureListener{
                            onResult(false)
                        }
                }
            }else{
                onResult(false)
            }
        }
}