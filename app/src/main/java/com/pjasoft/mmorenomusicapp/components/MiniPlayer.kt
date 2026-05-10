package com.pjasoft.mmorenomusicapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.pjasoft.mmorenomusicapp.models.Album


@Composable
fun MiniPlayer(album: Album) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(70.dp),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2D0E4D)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen miniatura
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .size(50.dp)
                    .padding(8.dp)
            ) {
                AsyncImage(
                    model = album.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }

            // Título y Artista
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp)

            ) {
                Text(text = album.title, color = Color.White, fontWeight = FontWeight.Bold, maxLines = 1)
                Text(text = album.artist, color = Color.White.copy(alpha = 0.7f), style = MaterialTheme.typography.bodySmall)
            }

            // Botón Play
            Surface(
                shape = CircleShape,
                color = Color.White,
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.padding(6.dp)
                )
            }
        }
    }
}