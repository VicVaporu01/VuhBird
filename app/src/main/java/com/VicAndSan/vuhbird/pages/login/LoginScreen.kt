package com.VicAndSan.vuhbird.pages.login

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.VicAndSan.vuhbird.R
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(navigateToSignUp: () -> Unit, navigateToMain: () -> Unit, auth: FirebaseAuth) {
    //Variables
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    //Variables de manejo de error
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

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
        //Campo de texto para email y validación de datos
        EmailTextField(email = email, onEmailChange = {
            email = it
            emailError = ""
        })
        if(emailError.isNotEmpty()){
            Text(
                text = emailError,
                color = androidx.compose.ui.graphics.Color.Red,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        //Campo de texto para contraseña y validación de datos
        PasswordTextField(password = password, onPasswordChange = {
            password = it
            passwordError = ""
        })
        if(passwordError.isNotEmpty()){
            Text(
                text = passwordError,
                color = androidx.compose.ui.graphics.Color.Red,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón Login
        Button(
            onClick = {
                val validationResult = validateLogin(email, password)
                //Validación de datos
                if(validationResult.isNullOrEmpty()){
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
                }else {
                    if (validationResult.containsKey("email")) emailError = validationResult["email"]!!
                    if (validationResult.containsKey("password")) passwordError = validationResult["password"]!!
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
//Composables para los campos del formulario
@Composable
fun EmailTextField(
    email: String,
    onEmailChange: (String) -> Unit
) {
    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        label = { Text("Correo electrónico") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Correo electrónico"
            )
        },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email
        )
    )
}
@Composable
fun PasswordTextField (password: String, onPasswordChange: (String) -> Unit){
    var isPasswordVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text("Contraseña") },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (isPasswordVisible) Icons.Default.CheckCircle else Icons.Default.Lock
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(imageVector = image, contentDescription = "Mostrar/ocultar contraseña")
            }
        },
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}
//Validaciín de datos
fun validateLogin(email: String, password: String): Map<String, String>? {
    val errors = mutableMapOf<String, String>()

    if (email.isBlank()) {
        errors["email"] = "El campo de correo no puede estar vacío."
    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        errors["email"] = "El correo electrónico no es válido."
    }

    if (password.isBlank()) {
        errors["password"] = "El campo de contraseña no puede estar vacío."
    }
    return if (errors.isEmpty()) null else errors
}