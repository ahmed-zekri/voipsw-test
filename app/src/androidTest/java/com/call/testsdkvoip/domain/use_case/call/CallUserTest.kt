package com.call.testsdkvoip.domain.use_case.call

import androidx.test.filters.SmallTest
import com.call.testsdkvoip.common.Resources
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@ExperimentalCoroutinesApi

@HiltAndroidTest
@SmallTest

internal class CallUserTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var callUser: CallUser

    @Before
    fun setUp() {

        hiltRule.inject()
    }

    @Test
    fun testCallUser() {

        runBlocking<Unit> {
          launch {   callUser("21625306014").onEach {
              Truth.assertThat(true)

          } }

        }
    }
}