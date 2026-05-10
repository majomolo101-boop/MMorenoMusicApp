package com.pjasoft.mmorenomusicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import coil3.request.crossfade
import com.pjasoft.mmorenomusicapp.navigation.NavGraph
import com.pjasoft.mmorenomusicapp.screens.HomeScreen
import com.pjasoft.mmorenomusicapp.ui.theme.MMorenoMusicAppTheme
import okhttp3.OkHttpClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            setSingletonImageLoaderFactory { context ->
                ImageLoader.Builder(context)
                    .components {
                        add(OkHttpNetworkFetcherFactory(
                            callFactory = {
                                OkHttpClient.Builder()
                                    .addInterceptor { chain ->
                                        chain.proceed(
                                            chain.request().newBuilder()
                                                .header("User-Agent", "MMorenoMusicApp/1.0")
                                                .build()
                                        )
                                    }
                                    .build()
                            }
                        ))
                    }
                    .crossfade(true)
                    .build()
            }

            MMorenoMusicAppTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph()
                }
                }
            }
        }
    }


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MMorenoMusicAppTheme {
        Greeting("Android")
    }
}