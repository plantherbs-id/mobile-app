plugins {
    id("com.android.application") version "7.0.3"
    kotlin("android") version "1.5.31"
}


android {
    compileSdkVersion(30)

    defaultConfig {
        applicationId = "com.example.myapp"
        // ...
    }
}

buildFeatures {
    // Pastikan tidak ada konfigurasi Kotlin Android Extensions
    // androidExtensions {
    //     experimental = true
    // }
    viewBinding = true
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}