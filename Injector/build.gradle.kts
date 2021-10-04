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
        minSdk = 24
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
    implementation("com.github.tiann:epic:0.11.2")
    implementation(files("../.assets/epic-0.11.2.aar"))
}
