package com.example.sharpeningapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.sharpeningapp.ui.theme.SharpeningAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            SharpeningAppTheme {
//                Scaffold(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .windowInsetsPadding(WindowInsets.systemBars)
//                ) { innerPadding ->
//                    HomeScreen(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(innerPadding)
//                    )
//                }
//            }
        }
    }
}
