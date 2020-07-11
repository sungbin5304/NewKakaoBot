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
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    implementation(Libraries.ESSENTIAL.Anko)
    implementation(Libraries.ESSENTIAL.CoreKtx)
    implementation(Libraries.ESSENTIAL.Legacy)
    implementation(Libraries.ESSENTIAL.Kotlin)
    implementation(Libraries.ESSENTIAL.AppCompat)
    implementation(Libraries.ESSENTIAL.FragmentKtx)
    implementation(Libraries.ESSENTIAL.LifeCycleViewModel)
    implementation(Libraries.ESSENTIAL.LifeCycleExtensions)

    implementation(Libraries.DI.Hilt)
    implementation(Libraries.DI.HiltCommon)
    implementation(Libraries.DI.HiltLifeCycle)

    implementation(Libraries.UI.Fab)
    implementation(Libraries.UI.Glide)
    implementation(Libraries.UI.CardView)
    implementation(Libraries.UI.ConstraintLayout)

    implementation(Libraries.UTILS.Jsoup)
    implementation(Libraries.UTILS.Rhino)
    implementation(Libraries.UTILS.RhinoAndroid)
    implementation(Libraries.UTILS.AndroidUtils)
    implementation(Libraries.UTILS.AdapterHelper)
    implementation(Libraries.UTILS.CrashReporter)

    implementation(Libraries.ANIMATOR.Tool)
    implementation(Libraries.ANIMATOR.Yoyo)

    testImplementation(TestLibrary.Junit)
    androidTestImplementation(AndroidTestLibraries.Junit)
    androidTestImplementation(AndroidTestLibraries.Espresso)

    kapt(Libraries.DI.HiltCompiler)
    kapt("androidx.hilt:hilt-compiler:1.0.0-alpha01")
}
