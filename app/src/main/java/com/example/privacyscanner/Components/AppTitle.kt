package com.example.privacyscanner.Components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppTitle(){
    Text("Privacy Dashboard",
        fontSize = 40.sp,
        fontWeight = FontWeight.ExtraBold,
        color = Color(0xFF333333),
        modifier = Modifier
            .padding(10.dp))
}