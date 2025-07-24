package com.example.privacyscanner.Components

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap

@Composable
fun AppInfoCard(appName: String, score: Int, permissions: String, isSystemApp: Boolean, hasTrackers: Boolean, appIcon: Drawable) {


    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()


    val animatedAlpha by animateFloatAsState(
        targetValue = if (isPressed) 0.2f else 1.0f, // Adjust opacity when pressed
        label = "alphaAnimation"
    )


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(color = if(isSystemApp){ Color(0xFFCDFACF)
            }else{ Color(0xFFFAD6A7)
            }) // A light grey color similar to the image
            .padding(16.dp)
            .alpha(animatedAlpha)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    Log.d("tag", "Details of: $appName")
                }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(bitmap = appIcon!!.toBitmap().asImageBitmap(),
            contentDescription = "App Icon",
            modifier = Modifier
                .size(60.dp) // Adjust size as needed
                .clip(CircleShape)
                .background(Color.Green))

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = appName,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp // Adjust font size as needed
                ),
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Score: $score",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )
                    Text(
                        text = "Permissions: $permissions",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "System app: ${isSystemApp.toString().uppercase()}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )
                    Text(
                        text = "Has trackers: $hasTrackers",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )
                }
            }
        }

    }
}


