import org.gradle.api.JavaVersion

object Apps {
    const val compileSdk = 29
    const val minSdk = 21
    const val targetSdk = 29
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Versions {
    const val KOTLIN = "1.3.50"
    const val AppCompat = "1.0.2"
    const val CORE = "1.3.0"
    const val LEGACY = "1.0.0"
    const val LifeCycleExtensions = "2.2.0"
    const val LifeCycleViewModel = "2.2.0"
    const val ANKO = "0.10.8"

    const val CardView = "1.0.0"
    const val ConstraintLayout = "1.1.3"
    const val GLIDE = "4.11.0"

    const val AndroidUtils = "3.1.4"
    const val AdapterHelper = "2.0.2"
    const val JSOUP = "1.13.1"
    const val RHINO = "1.7.12"
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
        const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN}"
        const val AppCompat = "androidx.appcompat:appcompat:${Versions.AppCompat}"
        const val CORE = "androidx.core:core-ktx:${Versions.CORE}"
        const val LEGACY = "androidx.legacy:legacy-support-core-ui:${Versions.LEGACY}"
        const val LifeCycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.LifeCycleExtensions}"
        const val LifeCycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LifeCycleViewModel}"
        const val ANKO = "org.jetbrains.anko:anko:${Versions.ANKO}"
    }

    object UI {
        const val CardView = "androidx.cardview:cardview:${Versions.CardView}"
        const val ConstraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.ConstraintLayout}"
        const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    }

    object UTILS {
        const val AndroidUtils = "com.github.sungbin5304:AndroidUtils:${Versions.AndroidUtils}"
        const val AdapterHelper = "com.github.sungbin5304:AdapterHelper:${Versions.AdapterHelper}"
        const val JSOUP = "org.jsoup:jsoup:${Versions.JSOUP}"
        const val RHINO = "org.mozilla:rhino:${Versions.RHINO}"
        const val RhinoAndroid = "com.faendir.rhino:rhino-android:${Versions.RhinoAndroid}"
        const val CrashReporter = "com.balsikandar.android:crashreporter:${Versions.CrashReporter}"
    }

    object ANIMATOR {
        const val TOOL = "com.daimajia.easing:library:${Versions.AnimatorTool}"
        const val YOYO = "com.daimajia.androidanimations:library:${Versions.AnimatorYOYO}"
    }

}

object TestLibrary {
    const val JUNIT = "junit:junit:${Versions.AndroidJunit}"
}

object AndroidTestLibraries {
    const val JUNIT = "androidx.test.ext:junit:${Versions.AndroidTestJunit}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.AndroidTestEspresso}"
}