package com.example.parcialmosqueraperez

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.parcialmosqueraperez.ui.theme.ParcialMosqueraPerezTheme
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ParcialMosqueraPerezTheme { ParcialScreen()  }
        }
    }
}

@Composable
fun ParcialScreen() {
    val context = LocalContext.current
    var score by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var validationResult by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondo4),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = "Imagen Login",
                modifier = Modifier.size(180.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text("Parcial #1", style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .background(Color.LightGray, shape = RoundedCornerShape(12.dp))
                    .border(width = 2.dp, color = Color.LightGray, shape = RoundedCornerShape(12.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp))

            Spacer(modifier = Modifier.height(20.dp))

            Text("Marielys Perez #1", style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(12.dp))
                    .border(width = 2.dp, color = Color(0xFF4CAF50), shape = RoundedCornerShape(12.dp))
                    .padding(horizontal = 6.dp, vertical = 4.dp))

            Spacer(modifier = Modifier.height(6.dp))

            Text("Marta Mosquera #2", style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(12.dp))
                    .border(width = 2.dp, color = Color(0xFF4CAF50), shape = RoundedCornerShape(12.dp))
                    .padding(horizontal = 16.dp, vertical = 4.dp))



            Spacer(modifier = Modifier.height(20.dp))

            Text("Ingrese su nota a validar", style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .background(Color.LightGray, shape = RoundedCornerShape(12.dp))
                    .border(width = 2.dp, color = Color.LightGray,shape = RoundedCornerShape(12.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp))

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = score,
                onValueChange = { score = it },
                placeholder = { Text("Puntaje") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedLabelColor = Color(0xFF4CAF50),
                    focusedTextColor = Color.Black,
                    focusedBorderColor = Color(0xFF4CAF50),
                    unfocusedTextColor = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            )

            Spacer(modifier = Modifier.height(16.dp))

            //si aca no ingreso el puntaje me manda un error que ingrese el que es
            Button(
                onClick = {
                    val scoreValue = score.toIntOrNull()
                    if (scoreValue != null) {
                        validationResult = getGrade(scoreValue)
                        Toast.makeText(context, "Resultado: $validationResult", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, "Por favor ingrese un puntaje válido", Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50), // Verde
                    contentColor = Color.White           // Texto blanco
                )
            ) {
                Text("Validar")
            }


            Spacer(modifier = Modifier.height(16.dp))



        }
    }
}

fun getGrade(score: Int): String {
    return when (score) {
        in 91..100 -> "A (Excelente)"
        in 81..90 -> "B (Bueno)"
        in 71..80 -> "C (Regular)"
        in 61..70 -> "D (Mas o Menos regular)"
        in 0..61 -> "F (No aprobado, gracias por participar)"
        else -> "Ingrese una nota con puntaje valido"
    }
}
