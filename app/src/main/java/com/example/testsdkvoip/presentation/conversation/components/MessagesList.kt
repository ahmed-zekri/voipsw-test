package com.example.testsdkvoip.presentation.conversation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.example.testsdkvoip.presentation.theme.Teal200
import com.streamwide.smartms.lib.core.api_ktx.messages.model.STWBaseMessage
import com.streamwide.smartms.lib.core.api_ktx.messages.model.STWMGMMessage

@Composable
fun MessagesList(list: LazyPagingItems<STWBaseMessage>) {

    LazyColumn(
        reverseLayout = true,
        modifier = Modifier.fillMaxWidth(),

        verticalArrangement = Arrangement.Top
    ) {


        items(
            items = list


        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(10.dp)
                    .background(
                        brush = Brush.horizontalGradient(listOf(Color.Gray, Color.LightGray)),
                        shape = RoundedCornerShape(15.dp)
                    ),
            ) { Text(text = (it as STWMGMMessage).body!!, modifier = Modifier.padding(10.dp)) }


        }

    }

}