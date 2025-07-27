package com.example.privacyscanner.Components

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import com.example.privacyscanner.utilityFunctions.appInfoPage

@Composable

fun AppInfoCard(
    appName: String,
    score: Int,
    permissions: String,
    isSystemApp: Boolean,
    hasTrackers: Boolean,
    appIcon: Drawable,
    packageName: String,
    context: Context,
    modifier: Modifier? = Modifier
) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(
                color = if (isSystemApp) {
                    Color(0xFFE6F3E6)
                } else {
                    Color(0xFFFDF5EB)
                }
            ) // A light grey color similar to the image
            .padding(16.dp)
            .clickable(
                onClick = {
                    appInfoPage(context = context, packageName = packageName)
                }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            bitmap = appIcon.toBitmap().asImageBitmap(),
            contentDescription = "App Icon of $appName",
            modifier = Modifier
                .size(60.dp) // Adjust size as needed
                .clip(CircleShape)
                .background(Color.Green)
        )

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
                        text = "Score: " + when (score) {
                            0 -> {
                                100
                            }

                            in 1..5 -> {
                                95
                            }

                            in 6..10 -> {
                                75
                            }

                            in 11..15 -> {
                                25
                            }

                            in 15..50 -> {
                                10
                            }

                            else -> {
                                0
                            }
                        }.toString(),
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                    Text(
                        text = "Permissions: $permissions",
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                }
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        text = "System app: ${isSystemApp.toString().uppercase()}",
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                    Text(
                        text = "Has trackers: $hasTrackers",
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                }
            }
        }

    }
}


