package com.call.testsdkvoip.presentation.contacts.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.call.testsdkvoip.presentation.contacts.ContactListViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.streamwide.smartms.lib.core.api_ktx.contact.model.STWContact
import com.streamwide.smartms.lib.core.api_ktx.contact.model.STWSubscriber

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ContactListItem(
    contact: STWContact,
    onClick: (STWContact) -> Unit,
    contactListViewModel: ContactListViewModel = hiltViewModel()
) {


    val permissionsState = rememberMultiplePermissionsState(
        listOf(
            android.Manifest.permission.RECORD_AUDIO,
            android.Manifest.permission.ANSWER_PHONE_CALLS,
            android.Manifest.permission.CALL_PHONE,
            android.Manifest.permission.MODIFY_AUDIO_SETTINGS,
            android.Manifest.permission.READ_PHONE_STATE,


            )
    )




    Button(onClick = {
        if (permissionsState.allPermissionsGranted)
            contactListViewModel.callUserNumber(phoneNumber = (contact as STWSubscriber).phone!!.internationalNumber)
        else
            permissionsState.launchMultiplePermissionRequest()


    }) {

        Text(text = "Call user")
    }
    Row(
        Modifier
            .fillMaxWidth()
            .clickable { onClick(contact) }
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


