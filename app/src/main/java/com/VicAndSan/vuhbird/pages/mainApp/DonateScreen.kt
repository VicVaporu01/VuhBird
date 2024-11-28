package com.VicAndSan.vuhbird.pages.mainApp

import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.VicAndSan.vuhbird.R
import com.VicAndSan.vuhbird.models.RemoteGetBirdByIdResult
import com.VicAndSan.vuhbird.services.RetrofitClient
import com.google.api.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun DonateScreen(paddingValues: PaddingValues, birdId: Int? = 1) {
    //Variables
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    //Variables de manejo de error
    var donationAmount by remember { mutableStateOf("") }
    var donationAmountError by remember { mutableStateOf("") }

    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    //Datos de las aves
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
    //Conexión a la API
    val context = LocalContext.current
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
    //Gestión de carga de datos
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
                //Targetas de aves
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
            //Campo de donaciones y validación de datos del campo
            OutlinedTextField(
                value = donationAmount,
                onValueChange = {
                    if (it.all { char -> char.isDigit() }) { // Solo permite números
                        donationAmount = it
                        donationAmountError = "" // Limpia errores si el input es válido
                    }
                },
                label = { Text("Monto") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            if (donationAmountError.isNotEmpty()) {
                Text(text = donationAmountError, color = androidx.compose.ui.graphics.Color.Red)
            }

            Spacer(modifier = Modifier.height(16.dp))
            //Botón donar y validación de la cantidad donada
            Button(onClick = {
                val amount = donationAmount.toIntOrNull()
                if(amount == null || amount <= 0 ){
                    donationAmountError = "Porfavor ingrese un monto válido"
                }else{
                    val userId = FirebaseAuth.getInstance().currentUser?.uid
                    if (userId!=null){
                        handleDonation(userId, amount, bird.name, context)
                    }else{
                        Log.i("Arial", "KO")
                    }

                }
            }){
                Text("Donar")
            }
        }
    }

}
//Gestión de la donación
fun handleDonation(
    userId: String,
    amount: Int,
    birdName: String,
    context: android.content.Context
) {

    val db = Firebase.firestore

    // Crear una nueva donación
    val donationRef = db.collection("donations").document()
    val donationData = mapOf(
        "amount" to amount,
        "userId" to userId,
        "birdName" to birdName,
        "timestamp" to FieldValue.serverTimestamp()
    )
    //Relación de donación con usuario
    donationRef.set(donationData)
        .addOnSuccessListener {
            val userRef = db.collection("users").document(userId)
            userRef.update("donations", FieldValue.arrayUnion(donationRef.id))
                .addOnSuccessListener {
                    Log.i("Aria", "SUCCES")
                    Toast.makeText(context, "Donación Exitosa - Muchas gracias", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Log.i("Aria", "FAILURE")
                    Toast.makeText(context, "Fallo en la donación", Toast.LENGTH_SHORT).show()
                }
        }
        .addOnFailureListener { e ->
            Log.i("Aria", "FAILURE")
            Toast.makeText(context, "Fallo en la donación", Toast.LENGTH_SHORT).show()
        }
}