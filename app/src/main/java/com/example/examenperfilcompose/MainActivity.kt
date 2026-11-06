package com.example.examenperfilcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.examenperfilcompose.ui.theme.ExamenPerfilComposeTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExamenPerfilComposeTheme {
                val showCommentDialogState = remember { mutableStateOf(false) }
                Scaffold(
                    containerColor = Color(0xFF0B1315),
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { showCommentDialogState.value = true },
                            containerColor = Color(0xFF00E676)
                        ) {
                            Text("+", color = Color.Black, fontSize = 24.sp)
                        }
                    }
                ) { innerPadding ->
                    DeveloperProfileScreen(
                        innerPadding = innerPadding,
                        showCommentDialogState = showCommentDialogState
                    )
                }
            }
        }
    }
}

data class Comentario(val autor: String, val mensaje: String)

@Composable
fun DeveloperProfileScreen(
    innerPadding: PaddingValues,
    showCommentDialogState: MutableState<Boolean> = remember { mutableStateOf(false) }
) {
    val skills = listOf("Kotlin", "C++", "Java", "Python", "TypeScript", "SQL", "HTML/CSS", "Angular", "Flask", "Laravel")
    var selectedSkill by remember { mutableStateOf("Kotlin") }

    val comentarios = remember {
        mutableStateListOf(
            Comentario("Lehi", "¡Increíble portafolio! El diseño y la arquitectura están impecables."),
            Comentario("Hassan", "Excelente trabajo, el código es muy limpio y la interfaz súper fluida.")
        )
    }

    var showCommentDialog by showCommentDialogState
    var nuevoAutor by remember { mutableStateOf("") }
    var nuevoMensaje by remember { mutableStateOf("") }

    val backgroundColor = Color(0xFF0B1315)
    val emeraldBright = Color(0xFF00E676)
    val darkEmerald = Color(0xFF004D40)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
    ) {
        // Portada
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.mr_robot_banner),
                contentDescription = "Portada",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Foto de perfil superpuesta en la esquina inferior izquierda
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 24.dp)
                    .offset(y = 48.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pfp),
                    contentDescription = "Foto de perfil",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(112.dp)
                        .clip(CircleShape)
                        .border(3.dp, emeraldBright, CircleShape)
                )
            }
        }

        // Espaciador para compensar el offset de la foto de perfil
        Spacer(modifier = Modifier.height(56.dp))

        // Datos del desarrollador
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Gael Antonio Díaz Ramírez",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Ingeniero en Software | Desarrollador Web",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = emeraldBright
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "gaelantdiaz@micorreo.upp.edu.mx",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.LightGray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Tarjeta de la sección About
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = darkEmerald
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "About",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Desarrollador de Software (Ingeniería en TI e Innovación Digital - UPP) y Creador Audiovisual, especializado en arquitecturas modernas, desarrollo móvil con Jetpack Compose, estructuras de datos y dirección creativa.",
                        fontSize = 14.sp,
                        lineHeight = 22.sp,
                        color = Color(0xFFE0F2F1)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Sección de habilidades y lenguajes
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "</> ",
                fontWeight = FontWeight.Bold,
                color = emeraldBright,
                fontSize = 16.sp
            )
            Text(
                text = "LENGUAJES DE PROGRAMACIÓN",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(skills) { skill ->
                val isSelected = selectedSkill == skill
                val iconRes = when (skill) {
                    "Kotlin" -> R.drawable.kotlin
                    "C++" -> R.drawable.cpp
                    "Java" -> R.drawable.java
                    "Python" -> R.drawable.python
                    "TypeScript" -> R.drawable.typescript
                    "SQL" -> R.drawable.sql
                    "HTML/CSS" -> R.drawable.html_css
                    "Angular" -> R.drawable.angular
                    "Flask" -> R.drawable.flask
                    "Laravel" -> R.drawable.laravel
                    else -> R.drawable.kotlin
                }

                Surface(
                    shape = RoundedCornerShape(50),
                    color = if (isSelected) Color(0xFF00E676) else Color.Transparent,
                    border = if (isSelected) null else BorderStroke(1.dp, Color(0xFF00E676)),
                    modifier = Modifier.clickable {
                        selectedSkill = skill
                        Log.i("Prowler", "Lenguaje seleccionado: $skill")
                    }
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = iconRes),
                            contentDescription = skill,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = skill,
                            color = if (isSelected) Color.Black else Color(0xFF00E676)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Ubicación",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        val uppCoordinates = LatLng(19.979903043345427, -98.6846767528567)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(uppCoordinates, 16f)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(2.dp, Color(0xFF00E676), RoundedCornerShape(16.dp))
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                Marker(
                    state = MarkerState(position = uppCoordinates),
                    title = "Universidad Politécnica de Pachuca",
                    snippet = "Zempoala, Hidalgo"
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Sección Comentarios del Perfil
        Text(
            text = "Comentarios del Perfil",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        comentarios.forEach { comentario ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF101C1F)
                ),
                border = BorderStroke(1.dp, emeraldBright)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = comentario.autor,
                        fontWeight = FontWeight.Bold,
                        color = emeraldBright,
                        fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = comentario.mensaje,
                        color = Color.White,
                        fontSize = 14.sp,
                        lineHeight = 20.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(80.dp))
    }

    // Diálogo para agregar comentarios
    if (showCommentDialog) {
        AlertDialog(
            onDismissRequest = { showCommentDialog = false },
            containerColor = Color(0xFF0E1A1D),
            title = {
                Text(
                    text = "Dejar Comentario",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = nuevoAutor,
                        onValueChange = { nuevoAutor = it },
                        label = { Text("Autor", color = emeraldBright) },
                        placeholder = { Text("Nombre del autor", color = Color.Gray) },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedBorderColor = emeraldBright,
                            unfocusedBorderColor = darkEmerald,
                            cursorColor = emeraldBright
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = nuevoMensaje,
                        onValueChange = { nuevoMensaje = it },
                        label = { Text("Mensaje", color = emeraldBright) },
                        placeholder = { Text("Escribe tu comentario", color = Color.Gray) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedBorderColor = emeraldBright,
                            unfocusedBorderColor = darkEmerald,
                            cursorColor = emeraldBright
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        comentarios.add(Comentario(nuevoAutor, nuevoMensaje))
                        Log.i("Prowler", "Nuevo comentario de: $nuevoAutor - $nuevoMensaje")
                        showCommentDialog = false
                        nuevoAutor = ""
                        nuevoMensaje = ""
                    }
                ) {
                    Text("Publicar", color = emeraldBright, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showCommentDialog = false
                    }
                ) {
                    Text("Cancelar", color = Color.LightGray)
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DeveloperProfilePreview() {
    ExamenPerfilComposeTheme {
        Scaffold(
            containerColor = Color(0xFF0B1315),
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { },
                    containerColor = Color(0xFF00E676)
                ) {
                    Text("+", color = Color.Black, fontSize = 24.sp)
                }
            }
        ) { innerPadding ->
            DeveloperProfileScreen(innerPadding = innerPadding)
        }
    }
}
