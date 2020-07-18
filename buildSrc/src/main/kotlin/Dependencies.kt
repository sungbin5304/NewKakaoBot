import org.gradle.api.*

object Application {
    const val minSdk = 21
    const val targetSdk = 29
    const val compileSdk = 29
    const val versionCode = 10
    const val jvmTarget = "1.8"
    const val versionName = "1.0.0"

    val targetCompat = JavaVersion.VERSION_1_8
    val sourceCompat = JavaVersion.VERSION_1_8
}

object Versions {
    const val Anko = "0.10.8"
    const val Legacy = "1.0.0"
    const val Kotlin = "1.3.50"
    const val CoreKtx = "1.3.0"
    const val AppCompat = "1.0.2"
    const val Material = "1.1.0"
    const val Coroutine = "1.3.7"
    const val FragmentKtx = "1.2.5"
    const val LifeCycleViewModel = "2.2.0"
    const val LifeCycleExtensions = "2.2.0"

    const val Hilt = "2.28-alpha"
    const val HiltAndroidX = "1.0.0-alpha01"

    const val Glide = "4.11.0"
    const val CardView = "1.0.0"
    const val PopupMenu = "4.0.0"
    const val CodeEditor = "1.0.2"
    const val SweetDialog = "1.6.2"
    const val ConstraintLayout = "1.1.3"

    const val Jsoup = "1.13.1"
    const val Rhino = "1.7.12"
    const val RhinoAndroid = "1.5"
    const val KoreanParser = "1.0.0"
    const val AndroidUtils = "3.1.5"
    const val AdapterHelper = "2.0.2"
    const val CrashReporter = "1.1.0"

    const val AnimatorLottie = "3.4.0"
    const val AnimatorTool = "2.1@aar"
    const val AnimatorYOYO = "2.3@aar"

    const val AndroidJunit = "4.12"
    const val AndroidTestJunit = "1.1.1"
    const val AndroidTestEspresso = "3.2.0"
}

object Dependencies {
    object Essential {
        const val Anko = "org.jetbrains.anko:anko:${Versions.Anko}"
        const val CoreKtx = "androidx.core:core-ktx:${Versions.CoreKtx}"
        const val Kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Kotlin}"
        const val AppCompat = "androidx.appcompat:appcompat:${Versions.AppCompat}"
        const val Legacy = "androidx.legacy:legacy-support-core-ui:${Versions.Legacy}"
        const val Material = "com.google.android.material:material:${Versions.Material}"
        const val FragmentKtx = "androidx.fragment:fragment-ktx:${Versions.FragmentKtx}"
        const val Coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Coroutine}"
        const val CoroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Coroutine}"
        const val LifeCycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.LifeCycleExtensions}"
        const val LifeCycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LifeCycleViewModel}"
    }

    object Di {
        const val Hilt = "com.google.dagger:hilt-android:${Versions.Hilt}"
        const val HiltCommon = "androidx.hilt:hilt-common:${Versions.HiltAndroidX}"
        const val HiltAndroidXCompiler = "androidx.hilt:hilt-compiler:${Versions.HiltAndroidX}"
        const val HiltGoogleCompiler = "com.google.dagger:hilt-android-compiler:${Versions.Hilt}"
        const val HiltLifeCycle = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.HiltAndroidX}"
    }

    object Ui {
        const val Glide = "com.github.bumptech.glide:glide:${Versions.Glide}"
        const val CardView = "androidx.cardview:cardview:${Versions.CardView}"
        const val SweetDialog = "com.github.f0ris.sweetalert:library:${Versions.SweetDialog}"
        const val CodeEditor = "com.github.sungbin5304:SimpleCodeEditor:${Versions.CodeEditor}"
        const val ConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.ConstraintLayout}"
        const val PopupMenu = "com.github.zawadz88.materialpopupmenu:material-popup-menu:${Versions.PopupMenu}"
    }

    object Utils {
        const val Jsoup = "org.jsoup:jsoup:${Versions.Jsoup}"
        const val Rhino = "org.mozilla:rhino:${Versions.Rhino}"
        const val RhinoAndroid = "com.faendir.rhino:rhino-android:${Versions.RhinoAndroid}"
        const val KoreanParser = "com.github.kimkevin:hangulparser:${Versions.KoreanParser}"
        const val AndroidUtils = "com.github.sungbin5304:AndroidUtils:${Versions.AndroidUtils}"
        const val AdapterHelper = "com.github.sungbin5304:AdapterHelper:${Versions.AdapterHelper}"
        const val CrashReporter = "com.balsikandar.android:crashreporter:${Versions.CrashReporter}"
    }

    object Animator {
        const val Tool = "com.daimajia.easing:library:${Versions.AnimatorTool}"
        const val Lottie = "com.airbnb.android:lottie:${Versions.AnimatorLottie}"
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
