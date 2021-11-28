buildscript {
    repositories {
//        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.2.2")
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
        classpath(
            kotlin(
                "gradle-plugin",
                version = "1.5.21"
            )
        )
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.5")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.39.1")
    }
}

//tasks.register("clean", Delete::class) {
//    delete(rootProject.buildDir)
//}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.google.com/")
        maven("https://jitpack.io")
    }
}
