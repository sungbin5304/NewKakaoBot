plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(Application.compileSdk)
    defaultConfig {
        minSdkVersion(Application.minSdk)
        targetSdkVersion(Application.targetSdk)
        versionCode = Application.versionCode
        versionName = Application.versionName
        multiDexEnabled = true
        setProperty("archivesBaseName", "v$versionName($versionCode)")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    sourceSets {
        getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
    }

    packagingOptions {
        exclude ("META-INF/library_release.kotlin_module")
    }

    compileOptions {
        sourceCompatibility = Application.sourceCompat
        targetCompatibility = Application.targetCompat
    }

    kotlinOptions {
        jvmTarget = Application.jvmTarget
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Dependencies.Essential.Anko)
    implementation(Dependencies.Essential.Legacy)
    implementation(Dependencies.Essential.Kotlin)
    implementation(Dependencies.Essential.CoreKtx)
    implementation(Dependencies.Essential.AppCompat)
    implementation(Dependencies.Essential.Coroutine)
    implementation(Dependencies.Essential.FragmentKtx)
    implementation(Dependencies.Essential.CoroutineCore)
    implementation(Dependencies.Essential.LifeCycleViewModel)
    implementation(Dependencies.Essential.LifeCycleExtensions)

    implementation(Dependencies.Di.Hilt)
    implementation(Dependencies.Di.HiltCommon)
    implementation(Dependencies.Di.HiltLifeCycle)

    implementation(Dependencies.Ui.Glide)
    implementation(Dependencies.Ui.CardView)
    implementation(Dependencies.Ui.SweetDialog)
    implementation(Dependencies.Ui.ConstraintLayout)

    implementation(Dependencies.Utils.Jsoup)
    implementation(Dependencies.Utils.KoreanParser)
    implementation(Dependencies.Utils.AndroidUtils)
    implementation(Dependencies.Utils.CrashReporter)

    implementation(Dependencies.Animator.Tool)
    implementation(Dependencies.Animator.Yoyo)
    implementation(Dependencies.Animator.Lottie)

    kapt(Dependencies.Di.HiltGoogleCompiler)
    kapt(Dependencies.Di.HiltAndroidXCompiler)
}
