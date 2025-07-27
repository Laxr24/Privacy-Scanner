package com.example.privacyscanner.Components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ApplicationStats(totalApps: Int, systemApps: Int) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text("Analyzed report 📝", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(Modifier.height(5.dp))
            Text(
                "Total Application $totalApps including system application. Total system apps $systemApps and ${totalApps - systemApps} manually installed apps on this device",
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}