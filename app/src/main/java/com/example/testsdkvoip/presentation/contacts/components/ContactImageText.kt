package com.example.testsdkvoip.presentation.contacts.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter


@OptIn(ExperimentalCoilApi::class)
@Composable
fun ContactImageText(text: String?, imageUri: String?) =
    if (imageUri.isNullOrBlank())
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(Color.DarkGray, shape = CircleShape)
                .layout { measurable, constraints ->
                    // Measure the composable
                    val placeable = measurable.measure(constraints)

                    //get the current max dimension to assign width=height
                    val currentHeight = placeable.height
                    var heightCircle = currentHeight
                    if (placeable.width > heightCircle)
                        heightCircle = placeable.width

                    //assign the dimension and the center position
                    layout(heightCircle, heightCircle) {
                        // Where the composable gets placed
                        placeable.placeRelative(0, (heightCircle - currentHeight) / 2)
                    }
                }) {


            Text(

                text = "${
                    if (text?.split(" ")?.size!! > 1) text.split(" ")
                        .getOrNull(0)?.getOrNull(0)?.uppercase() ?: "" else "  "
                }${
                    if (text.split(" ").size > 1) " " else text.split(" ")
                        .getOrNull(0)?.getOrNull(0)?.uppercase() ?: ""
                }${
                    if (text.split(" ").size > 1) text.split(" ")
                        .getOrNull(1)?.getOrNull(0)?.uppercase() ?: "" else "  "
                }",
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier
                    .padding(4.dp)
                    .defaultMinSize(31.dp) //Use a min size for short text.

            )
        }
    else Image(
        painter = rememberImagePainter(imageUri),
        contentDescription = null,
        modifier = Modifier
            .size(35.dp)
            .clip(CircleShape),
        contentScale = ContentScale.FillBounds
    )