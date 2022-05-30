package com.call.testsdkvoip.presentation.conversation.components

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
import com.streamwide.smartms.lib.core.api_ktx.messages.model.STWBaseMessage
import com.streamwide.smartms.lib.core.api_ktx.messages.model.STWMGMMessage
import com.streamwide.smartms.lib.core.api_ktx.messages.model.STWVoIPMessage

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

                message ->
            var isLeft = false
            var caller: String? = null
            var messageBody: String? = null
            when (message) {

                is STWVoIPMessage -> {
                    isLeft = false
                    caller = message.voIPSessions?.get(0)?.caller
                }

                is STWMGMMessage -> {
                    isLeft = message.sender == null
                    messageBody = message.body


                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = if (isLeft) Arrangement.End else Arrangement.Start
            )


            {
                Box(
                    modifier = Modifier

                        .padding(10.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                listOf(
                                    Color.Gray,
                                    Color.LightGray
                                )
                            ),
                            shape = RoundedCornerShape(15.dp)
                        ),
                ) {
                    Text(
                        text = messageBody
                            ?: "You missed a call from ${caller ?: "Unknown number"}",
                        modifier = Modifier.padding(10.dp)
                    )
                }

            }

        }

    }

}