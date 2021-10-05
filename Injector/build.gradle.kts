import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
    id("com.android.library")
    id("com.aliucord.gradle")
}

aliucord {
    projectType.set(com.aliucord.gradle.ProjectType.INJECTOR)
}

android {
    compileSdk = 30

    defaultConfig {
        minSdk = 21
        targetSdk = 30
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    discord("com.discord:discord:${findProperty("discord_version")}")
    implementation("androidx.appcompat:appcompat:1.3.1")

    implementation("com.swift.sandhook:hooklib:4.2.0")
    implementation("com.swift.sandhook:xposedcompat:4.2.0")
}
