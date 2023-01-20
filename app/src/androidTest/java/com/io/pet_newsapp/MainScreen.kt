package com.io.pet_newsapp

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.io.pet_newsapp.ui.news.activity.FirstActivity
import com.io.pet_newsapp.ui.news.activity.MainActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KButton

object MainScreen: KScreen<MainScreen>() {

    override val layoutId: Int = R.layout.activity_main
    override val viewClass: Class<*> = MainActivity::class.java


//    val button: KButton = { withId(R.id.btn_server)}
}