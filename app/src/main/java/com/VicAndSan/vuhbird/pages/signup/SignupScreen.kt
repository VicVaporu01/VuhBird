package com.VicAndSan.vuhbird.pages.signup

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun SignUpScreen (navigateToLogin: () -> Unit, auth: FirebaseAuth, db:FirebaseFirestore){
    val context = LocalContext.current
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
        NameTextField(field = name, onFieldChange = {name = it}, "Nombre Completo")

        Spacer(modifier = Modifier.height(5.dp))
        //Nro Celular
        PhoneTextField(phone = phone, onPhoneChange = {phone = it})

        Spacer(modifier = Modifier.height(5.dp))
        //Email
        EmailTextField(email = email, {email = it})

        Spacer(modifier = Modifier.height(5.dp))
        //Contraseña
        PasswordTextField(password = password, onPasswordChange = {password = it})

        Spacer(modifier = Modifier.height(5.dp))
        // Signup button
        Button(
            onClick = {
                registerUser(email, password, name, phone){
                    if (it){

                        Log.i("Aria", "SUCCES")
                        navigateToLogin()
                        Toast.makeText(context, "Registro Exitoso", Toast.LENGTH_SHORT).show()
                    }else{
                        Log.i("Aria", "FAIL")
                        Toast.makeText(context, "Fallo de Registro", Toast.LENGTH_SHORT).show()
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
@Composable
fun NameTextField(
    field: String,
    onFieldChange: (String) -> Unit,
    fieldLabel: String
) {
    OutlinedTextField(
        value = field,
        onValueChange = onFieldChange,
        label = { Text(fieldLabel) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Nombre de Usuario"
            )
        },
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}
@Composable
fun PhoneTextField(
    phone: String,
    onPhoneChange: (String) -> Unit
) {
    OutlinedTextField(
        value = phone,
        onValueChange = onPhoneChange,
        label = { Text("Número Celular") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Phone,
                contentDescription = "Número de Telefono"
            )
        },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Phone
        )
    )
}