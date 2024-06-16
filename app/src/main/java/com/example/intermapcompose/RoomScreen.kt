package com.example.intermapcompose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RoomScreen(navController: NavController) {
    // Estado para almacenar la posición del círculo
    var circlePosition by remember { mutableStateOf(Offset(0f, 0f)) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0099FF))
            .padding(16.dp) // Ajusta el padding según sea necesario
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            // Canvas para dibujar el rectángulo negro y el círculo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) //
                    .padding(16.dp)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val padding = 0.dp.toPx()
                    // Dibujar el rectángulo negro
                    drawPath(
                        path = createRectanglePath(size.width, size.height, padding),
                        color = Color.Black,
                        style = Stroke(width = 5.dp.toPx())
                    )

                    // Dibuja el círculo en la posición generada
                    drawCircle(
                        color = Color.Red,
                        radius = 25.dp.toPx(),
                        center = circlePosition
                    )
                }

                // Contenido del perfil (icono de usuario)
                Icon(
                    painter = painterResource(id = R.drawable.ic_painting),
                    contentDescription = "User Icon",
                    tint = Color.White,
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color(0xFFCC3366))
                        .clickable {
                            navController.navigate("painting")
                        }
                )
            }

            // Espacio para separar elementos
            Spacer(modifier = Modifier.height(16.dp))

            // Botón para generar círculo aleatorio (fuera del rectángulo negro)
            Button(
                onClick = {
                    // Generar posición aleatoria dentro del rectángulo negro
                    val randomX = (50..700).random().toFloat()
                    val randomY = (32..1800).random().toFloat()
                    circlePosition = Offset(randomX, randomY)
                },
                modifier = Modifier.align(Alignment.CenterHorizontally) // Centrar el botón
            ) {
                Text("Generar Posición")
            }
        }
    }
}

fun createRectanglePath(width: Float, height: Float, padding: Float): Path {
    return Path().apply {
        reset()
        moveTo(padding, padding)
        lineTo(width - padding, padding)
        lineTo(width - padding, height - padding)
        lineTo(padding, height - padding)
        close()
    }
}





