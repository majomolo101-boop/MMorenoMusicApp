package com.pjasoft.mmorenomusicapp.screens

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pjasoft.mmorenomusicapp.ui.theme.MMorenoMusicAppTheme

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3E5F5))
            .statusBarsPadding()

    ) {

        // 1. HEADER
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(160.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF9D50FF),
                            Color(0xFF6E22FF)
                        )
                    )
                )
                .padding(24.dp)
        ){
            // 1.1 ICONOS
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.TopEnd)
            )

            // 1.2 SALUDO
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
            ) {
                Text(
                    text = "Good Mornig!",
                    color = Color.White.copy(alpha = 0.9f),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Maria Moreno",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }










    }
}


@Preview(
    showBackground = true,
    showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MMorenoMusicAppTheme() {
        HomeScreen()
    }
}