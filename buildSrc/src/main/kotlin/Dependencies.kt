import org.gradle.api.JavaVersion

object Apps {
    const val compileSdk = 29
    const val minSdk = 21
    const val targetSdk = 29
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Versions {
    const val Kotlin = "1.3.50"
    const val AppCompat = "1.0.2"
    const val Core = "1.3.0"
    const val Legacy = "1.0.0"
    const val LifeCycleExtensions = "2.2.0"
    const val LifeCycleViewModel = "2.2.0"
    const val Anko = "0.10.8"

    const val Hilt = "2.28-alpha"

    const val CardView = "1.0.0"
    const val ConstraintLayout = "1.1.3"
    const val Glide = "4.11.0"
    const val Fab = "3.0.1"

    const val AndroidUtils = "3.1.4"
    const val AdapterHelper = "2.0.2"
    const val Jsoup = "1.13.1"
    const val Rhino = "1.7.12"
    const val RhinoAndroid = "1.5"
    const val CrashReporter = "1.1.0"

    const val AnimatorTool = "2.1@aar"
    const val AnimatorYOYO = "2.3@aar"

    const val AndroidJunit = "4.12"
    const val AndroidTestJunit = "1.1.1"
    const val AndroidTestEspresso = "3.2.0"

    val sourceCompat = JavaVersion.VERSION_1_8
    val targetCompat = JavaVersion.VERSION_1_8
}

object Libraries {
    object ESSENTIAL {
        const val Kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Kotlin}"
        const val AppCompat = "androidx.appcompat:appcompat:${Versions.AppCompat}"
        const val Core = "androidx.core:core-ktx:${Versions.Core}"
        const val Legacy = "androidx.legacy:legacy-support-core-ui:${Versions.Legacy}"
        const val LifeCycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.LifeCycleExtensions}"
        const val LifeCycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LifeCycleViewModel}"
        const val Anko = "org.jetbrains.anko:anko:${Versions.Anko}"
    }

    object DI {
        const val Hilt = "com.google.dagger:hilt-android:${Versions.Hilt}"
        const val HiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.Hilt}"
    }

    object UI {
        const val CardView = "androidx.cardview:cardview:${Versions.CardView}"
        const val ConstraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.ConstraintLayout}"
        const val Glide = "com.github.bumptech.glide:glide:${Versions.Glide}"
        const val Fab = "com.robertlevonyan.view:CustomFloatingActionButton:${Versions.Fab}"
    }

    object UTILS {
        const val AndroidUtils = "com.github.sungbin5304:AndroidUtils:${Versions.AndroidUtils}"
        const val AdapterHelper = "com.github.sungbin5304:AdapterHelper:${Versions.AdapterHelper}"
        const val Jsoup = "org.jsoup:jsoup:${Versions.Jsoup}"
        const val Rhino = "org.mozilla:rhino:${Versions.Rhino}"
        const val RhinoAndroid = "com.faendir.rhino:rhino-android:${Versions.RhinoAndroid}"
        const val CrashReporter = "com.balsikandar.android:crashreporter:${Versions.CrashReporter}"
    }

    object ANIMATOR {
        const val Tool = "com.daimajia.easing:library:${Versions.AnimatorTool}"
        const val Yoyo = "com.daimajia.androidanimations:library:${Versions.AnimatorYOYO}"
    }
}

object TestLibrary {
    const val Junit = "junit:junit:${Versions.AndroidJunit}"
}

object AndroidTestLibraries {
    const val Junit = "androidx.test.ext:junit:${Versions.AndroidTestJunit}"
    const val Espresso = "androidx.test.espresso:espresso-core:${Versions.AndroidTestEspresso}"
}