plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(Apps.compileSdk)
    defaultConfig {
        minSdkVersion(Apps.minSdk)
        targetSdkVersion(Apps.targetSdk)
        versionCode = Apps.versionCode
        versionName = Apps.versionName
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
        sourceCompatibility = Versions.sourceCompat
        targetCompatibility = Versions.targetCompat
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Libraries.ESSENTIAL.Anko)
    implementation(Libraries.ESSENTIAL.Core)
    implementation(Libraries.ESSENTIAL.Legacy)
    implementation(Libraries.ESSENTIAL.Kotlin)
    implementation(Libraries.ESSENTIAL.AppCompat)
    implementation(Libraries.ESSENTIAL.LifeCycleViewModel)
    implementation(Libraries.ESSENTIAL.LifeCycleExtensions)

    implementation(Libraries.DI.Hilt)

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
}
