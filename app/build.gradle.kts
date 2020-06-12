plugins {
    id("com.android.application")
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

    implementation(Libraries.ESSENTIAL.ANKO)
    implementation(Libraries.ESSENTIAL.CORE)
    implementation(Libraries.ESSENTIAL.LEGACY)
    implementation(Libraries.ESSENTIAL.KOTLIN)
    implementation(Libraries.ESSENTIAL.AppCompat)
    implementation(Libraries.ESSENTIAL.LifeCycleViewModel)
    implementation(Libraries.ESSENTIAL.LifeCycleExtensions)

    implementation(Libraries.UI.GLIDE)
    implementation(Libraries.UI.CardView)
    implementation(Libraries.UI.ConstraintLayout)

    implementation(Libraries.UTILS.JSOUP)
    implementation(Libraries.UTILS.RHINO)
    implementation(Libraries.UTILS.RhinoAndroid)
    implementation(Libraries.UTILS.AndroidUtils)
    implementation(Libraries.UTILS.AdapterHelper)
    implementation(Libraries.UTILS.CrashReporter)

    implementation(Libraries.ANIMATOR.TOOL)
    implementation(Libraries.ANIMATOR.YOYO)

    testImplementation(TestLibrary.JUNIT)
    androidTestImplementation(AndroidTestLibraries.JUNIT)
    androidTestImplementation(AndroidTestLibraries.ESPRESSO)
}
