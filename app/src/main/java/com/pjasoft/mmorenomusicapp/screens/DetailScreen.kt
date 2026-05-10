package com.pjasoft.mmorenomusicapp.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.pjasoft.mmorenomusicapp.components.MiniPlayer
import com.pjasoft.mmorenomusicapp.services.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

@Composable
fun DetailScreen(albumId: String, navController: NavController) {
    val service = RetrofitClient.instance
    var album by remember { mutableStateOf<com.pjasoft.mmorenomusicapp.models.Album?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(albumId) {
        try {
            val result = async(Dispatchers.IO) { service.getAlbumDetail(albumId) }
            album = result.await()
            isLoading = false
        } catch (e: Exception) {
            Log.e("DetailScreen", "Error: ${e.message}")
            isLoading = false
        }
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    val currentAlbum = album ?: return

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFF3E5F5))

    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)
        ) {

            // 1. HEADER
            item {
                Box(modifier = Modifier.fillMaxWidth()) {

                    // 1.1 CUADRO
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 55.dp)
                            .height(300.dp)
                            .clip(RoundedCornerShape(30.dp))
                    ) {
                        AsyncImage(
                            model = currentAlbum.image,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color(0x669D50FF),
                                            Color(0xCC6E22FF)
                                        )
                                    )
                                )
                        )

                        // 1.2 TEXTO/ ICONOS >
                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(24.dp)
                        ) {
                            Text(
                                currentAlbum.title,
                                color = Color.White,
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                currentAlbum.artist,
                                color = Color.White.copy(alpha = 0.8f),
                                style = MaterialTheme.typography.titleMedium
                            )

                            Spacer(
                                modifier = Modifier.height(16.dp)
                            )

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {

                                // #1
                                Surface(
                                    shape = CircleShape,
                                    color = Color(0xFF9D50FF),
                                    modifier = Modifier
                                        .size(48.dp)
                                ) {

                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .fillMaxSize()
                                    ) {
                                        Icon(
                                            Icons.Default.PlayArrow, null,
                                            tint = Color.White, modifier = Modifier.size(28.dp)
                                        )
                                    }
                                }

                                // #2
                                Surface(
                                    shape = CircleShape,
                                    color = Color.White,
                                    modifier = Modifier.size(48.dp)
                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .fillMaxSize()
                                    ) {
                                        Icon(
                                            Icons.Default.PlayArrow, null,
                                            tint = Color.Black,
                                            modifier = Modifier
                                                .size(28.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // 1.3 BOTONES FLECHA/CORAZON
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .statusBarsPadding()
                            .padding(horizontal = 28.dp, vertical = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier.background(
                                Color.Black.copy(alpha = 0.3f),
                                CircleShape
                            )
                        ) {
                            Icon(Icons.Default.ArrowBack, null, tint = Color.White)
                        }
                        IconButton(
                            onClick = {},
                            modifier = Modifier.background(
                                Color.Black.copy(alpha = 0.3f),
                                CircleShape
                            )
                        ) {
                            Icon(Icons.Outlined.FavoriteBorder, null, tint = Color.White)
                        }
                    }
                }
            }

            // 2. ABOUT THIS ALBUM
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Text(
                            "About this album", fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(currentAlbum.description, color = Color.DarkGray)
                    }
                }
            }

            // 3. ARTISTA
            item {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .wrapContentWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Text(
                        text = "Artist: ${currentAlbum.artist}",
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // 4. LISTA CANCIONES
            items(10) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            modifier = Modifier
                                .size(50.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            AsyncImage(
                                model = currentAlbum.image,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        Column(
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .weight(1f)
                        ) {
                            Text(
                                text = "${currentAlbum.title} • Track ${index + 1}",
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = currentAlbum.artist,
                                color = Color.Gray,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        Icon(Icons.Default.MoreVert, null, tint = Color.Gray)
                    }
                }
            }
        }

        // 5. MINI PLAYER
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            MiniPlayer(album = currentAlbum)
        }
    }
}