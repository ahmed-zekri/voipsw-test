package com.call.testsdkvoip.presentation.contacts.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.call.testsdkvoip.presentation.CallStateViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons
import com.streamwide.smartms.lib.core.api_ktx.contact.model.STWContact
import com.streamwide.smartms.lib.core.api_ktx.contact.model.STWSubscriber

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ContactListItem(
    contact: STWContact,
    onClick: (STWContact) -> Unit,
    callViewModel: CallStateViewModel = hiltViewModel()
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
                    .clickable { onClick(contact) }
                    .padding(20.dp, 8.dp, 0.dp, 0.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2,
                overflow = TextOverflow.Ellipsis,

                )
        }




        Spacer(Modifier.weight(1f))
        FaIcon(
            faIcon = FaIcons.Phone,
            tint = Color.Green,
            size = 24.dp,
            modifier = Modifier.clickable {
                if (permissionsState.allPermissionsGranted)
                    callViewModel.callUserNumber(phoneNumber = (contact as STWSubscriber).phone!!.internationalNumber)
                else
                    permissionsState.launchMultiplePermissionRequest()


            })


    }

}