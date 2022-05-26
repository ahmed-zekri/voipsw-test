package com.example.testsdkvoip.common/*
 *
 * 	StreamWIDE (Team on The Run)
 *
 * @createdBy  AndroidTeam on ven., 10 sept. 2021 10:36:28 +0100
 * @copyright  Copyright (c) 2021 StreamWIDE UK Ltd (Team on the Run)
 * @email      support@teamontherun.com
 *
 * 	© Copyright 2021 StreamWIDE UK Ltd (Team on the Run). StreamWIDE is the copyright holder
 * 	of all code contained in this file. Do not redistribute or
 *  	re-use without permission.
 *
 * @lastModifiedOn ven., 10 sept. 2021 10:36:23 +0100
 */



import com.streamwide.smartms.lib.core.api_ktx.contact.model.*

object MessageUtil {

    fun prepareParticipantArrayString(
        contact: List<STWContact>?,
    ): List<STWRecipient> {

        val users = mutableListOf<STWRecipient>()
        contact?.forEach {
            if (it is STWSubscriber) {
                it.phone?.internationalNumber?.let { number ->
                    users.add(STWRecipientSingle(number, it.phone?.fqdn))
                }
            }

            if (it is STWExternalContact) {
                it.phones?.forEach { phone ->
                    users.add(STWRecipientSingle(phone.internationalNumber, phone.fqdn))
                }
            }

            if (it is STWGroup) {
                it.groupId?.let { grpId ->
                    users.add(STWRecipientGroup(grpId, it.displayName))
                }
            }

        }
        return users
    }


}