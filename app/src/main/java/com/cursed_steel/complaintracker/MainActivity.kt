package com.cursed_steel.complaintracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment

var userExist = false

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        userExist = doUserExist()
        setContent {
            MainScreen()
        }
    }
}

fun doUserExist(): Boolean {
    //Codes...
    return false
}

@Composable
fun MainScreen() {
    val userExist by remember { mutableStateOf(doUserExist()) }
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

    // Main structural container
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color("#FFFFFF".toColorInt()))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()), // Prevents keyboard overflow clipping
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Heading scales spacing based on orientation
            Spacer(modifier = Modifier.height(if (isLandscape) 16.dp else 48.dp))

            Text(
                text = "COMPLAINT TRACKER",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color("#B036DC".toColorInt())
            )

            Spacer(modifier = Modifier.height(if (isLandscape) 24.dp else 40.dp))

            // Adaptive content container
            Box(
                modifier = Modifier
                    .widthIn(max = 450.dp) // Locks standard width on large tablet screens
                    .fillMaxWidth()
            ) {
                if(isLandscape) {
                    // Two-column grid layout for landscape configurations
                    LandscapeFormLayout(userExist = userExist)
                } else {
                    // Single column scroll layout for standard portrait mobile
                    PortraitFormLayout(userExist = userExist)
                }
            }
        }
    }
}

@Composable
fun PortraitFormLayout(userExist: Boolean) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!userExist) {
            TextField(value = "", onValueChange = {}, label = { Text("Name") }, modifier = Modifier.fillMaxWidth())
            TextField(value = "", onValueChange = {}, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
            TextField(value = "", onValueChange = {}, label = { Text("Password") }, modifier = Modifier.fillMaxWidth())
            TextField(value = "", onValueChange = {}, label = { Text("Confirm Password") }, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(0.6f), // Button sizes relative to container width
                colors = ButtonDefaults.buttonColors(containerColor = Color("#B036DC".toColorInt()))
            ) {
                Text(text = "REGISTER")
            }
        } else {
            TextField(value = "", onValueChange = {}, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
            TextField(value = "", onValueChange = {}, label = { Text("Password") }, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(0.6f),
                colors = ButtonDefaults.buttonColors(containerColor = Color("#B036DC".toColorInt()))
            ) {
                Text(text = "LOGIN")
            }
        }
    }
}

@Composable
fun LandscapeFormLayout(userExist: Boolean) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (!userExist) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                TextField(value = "", onValueChange = {}, label = { Text("Name") }, modifier = Modifier.weight(1f))
                TextField(value = "", onValueChange = {}, label = { Text("Email") }, modifier = Modifier.weight(1f))
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                TextField(value = "", onValueChange = {}, label = { Text("Password") }, modifier = Modifier.weight(1f))
                TextField(value = "", onValueChange = {}, label = { Text("Confirm Password") }, modifier = Modifier.weight(1f))
            }
            Button(
                onClick = {},
                modifier = Modifier.width(200.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color("#B036DC".toColorInt()))
            ) {
                Text(text = "REGISTER")
            }
        } else {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                TextField(value = "", onValueChange = {}, label = { Text("Email") }, modifier = Modifier.weight(1f))
                TextField(value = "", onValueChange = {}, label = { Text("Password") }, modifier = Modifier.weight(1f))
            }
            Button(
                onClick = {},
                modifier = Modifier.width(200.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color("#B036DC".toColorInt()))
            ) {
                Text(text = "LOGIN")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}