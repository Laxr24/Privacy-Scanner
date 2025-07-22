package com.example.privacyscanner.Screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Home(){

    Text(text = "Privacy Scanner Dashboard", modifier = Modifier
        .padding(top = 50.dp, start = 10.dp),
        softWrap = true,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        color = Color(0xFF212121)
    )
}


