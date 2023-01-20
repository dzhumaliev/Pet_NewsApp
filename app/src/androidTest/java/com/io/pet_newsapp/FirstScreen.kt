package com.io.pet_newsapp

import android.widget.Button
import com.io.pet_newsapp.ui.news.activity.FirstActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KButton

object FirstScreen: KScreen<FirstScreen>() {

    override val layoutId: Int = R.layout.activity_splash
    override val viewClass: Class<*> = FirstActivity::class.java

    val btnGo = KButton { withId(R.id.btn_gogo) }

}