package com.io.pet_newsapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.orchestrator.listeners.result.TestResult
import androidx.test.rule.ActivityTestRule
import com.io.pet_newsapp.ui.news.activity.FirstActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import kotlinx.coroutines.delay
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest : TestCase() {

//    @get:Rule
//    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
//        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//        Manifest.permission.READ_EXTERNAL_STORAGE
//    )

    @get:Rule
    val activityTestRule = ActivityTestRule(FirstActivity::class.java, true, false)


    @Before
    fun setup() {
        testLogger.i("before setup")
    }

    @Test
    fun showMainActivity() = run {

        step("Open FirstScreen") {
            activityTestRule.launchActivity(null)
            testLogger.i("I am testLogger -- log")

            FirstScreen {
                testLogger.i("FirstScreen -- log")




            }
        }


        step("Open MainScreen") {
//            testLogger.i("flakySafely -- log")
//        flakySafely(timeoutMs = 10_000L) {

            MainScreen {
                testLogger.i("MainScreen -- log")
            }
        }
    }

    suspend fun foo() {
        delay(10_000L) // when run in `runTest`, will finish immediately instead of delaying
        // ...
        FirstScreen.btnGo {
            click()
            testLogger.i("FirstScreen click -- log")

        }
    }
//    }
}