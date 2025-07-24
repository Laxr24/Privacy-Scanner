package com.example.privacyscanner.Components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.nio.file.WatchEvent

@Composable
@Preview(showBackground = true)
fun TableTitle(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .border(width = 2.dp, color = Color(0x00000000), shape = RectangleShape),
        horizontalArrangement = Arrangement.SpaceAround
    ){
        Box(
            contentAlignment = Alignment.Center){
            Text("App name",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold)
        }

        Box(
            contentAlignment = Alignment.Center){
            Text("Score",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold)
        }

        Box(
            contentAlignment = Alignment.Center){
            Text("Info.",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold)
        }

    }
}