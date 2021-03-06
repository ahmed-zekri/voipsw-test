package com.call.testsdkvoip.domain.use_case.call

import android.content.Context
import androidx.test.filters.SmallTest
import com.call.testsdkvoip.common.Resources
import com.google.common.truth.Truth.assertThat
import com.streamwide.smartms.lib.core.api.account.STWAccountManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@ExperimentalCoroutinesApi

@HiltAndroidTest
@SmallTest

internal class VoipTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var callUser: CallUser

    @Inject
    lateinit var hangCall: HangCall

    @Inject
    @ApplicationContext
    lateinit var context: Context

    @Before
    fun setUp() {

        hiltRule.inject()
    }

    @Test
    fun testCallUser() {

        runBlocking {
            val it =
                callUser("21625306014").filter { resource -> resource !is Resources.Loading }
                    .first()


            if (!STWAccountManager.getInstance().isUserAuthenticated(context))

                assertThat(
                    !it.message!!.contains(
                        "Exception"
                    )
                )
            else if (it !is Resources.Success)
                assert(false)
            else {
                val hangResult =
                    hangCall(it.data!!).filter { resource -> resource !is Resources.Loading }
                        .first()
                assertThat(hangResult is Resources.Success)


            }


        }
    }
}