package com.example.testsdkvoip.presentation.contacts.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.streamwide.smartms.lib.core.api_ktx.contact.model.STWContact

@Composable
fun ContactListItem(contact: STWContact) {

    Row(
        Modifier
            .fillMaxWidth()
            .padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween


    )
    {
        Row {
            if (contact.displayName?.isNotBlank() == true)
                ContactImageText(text = contact.displayName, imageUri = "")
            Text(
                text = "${contact.displayName}",
                modifier = Modifier
                    .wrapContentSize()
                    .padding(20.dp, 8.dp, 0.dp, 0.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2,
                overflow = TextOverflow.Ellipsis,

                )
        }


        Text(
            text = "${contact.sortKey}",
            modifier = Modifier
                .wrapContentSize()
                .padding(0.dp, 8.dp, 0.dp, 0.dp),
            textAlign = TextAlign.End,
            style = TextStyle(fontStyle = FontStyle.Italic),
            overflow = TextOverflow.Ellipsis,


            )

    }

}


