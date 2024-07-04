plugins {
    id("com.android.application")
}

android {
    namespace = "com.vishal.ink"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.vishal.ink"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }

}

dependencies {

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")


    implementation ("androidx.navigation:navigation-fragment:2.7.7")
    implementation ("androidx.navigation:navigation-ui:2.7.7")


    //coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    //Room Database
    implementation("androidx.room:room-runtime:2.6.1")

    annotationProcessor ("androidx.room:room-compiler:2.6.1")// For Java projects

    implementation ("com.github.fornewid:neumorphism:0.3.0")

    implementation ("com.airbnb.android:lottie:5.2.0")

    annotationProcessor ("com.github.bumptech.glide:compiler:4.14.2")
    implementation ("com.github.bumptech.glide:glide:4.16.0")




}