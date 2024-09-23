import com.android.build.api.variant.BuildConfigField
import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

val localPropsFile: String =
    System.getenv("LOCAL_PROPS")
        ?: (rootProject.rootDir.absolutePath + "/local.properties")
val localProperties = Properties()
    .apply {
        load(FileInputStream(localPropsFile))
    }

android {
    namespace = "com.adyen.sampletestuploadapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.adyen.sampletestuploadapp"
        minSdk = 30
        targetSdk = 34
        versionCode = 14
        versionName = "1.2.11"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        ndk {
            abiFilters.addAll(setOf("armeabi-v7a", "arm64-v8a"))
        }
    }

    signingConfigs {
        create("dummy") {
            storeFile = file("../myreleasekey.keystoreDUMMY")
            storePassword = "password"
            keyAlias = "key0"
            keyPassword = "password"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("dummy")
        }
    }

//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
//    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
//
    kotlinOptions {
        jvmTarget = "17"
//        apiVersion = "1.8"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }


    packagingOptions.resources.pickFirsts.add("META-INF/kotlinx_coroutines_core.version")
    dynamicFeatures += setOf(":t2p")
}

androidComponents {
    onVariants {
        it.apply {
            val environmentApiKey = localProperties.getProperty("environment.apiKey")
            buildConfigFields.put(
                "EnvironmentApiKey",
                BuildConfigField("String", "\"$environmentApiKey\"", "API Key"),
            )
            val environmentMerchantAccount =
                localProperties.getProperty("environment.merchantAccount")
            buildConfigFields.put(
                "EnvironmentMerchantAccount",
                BuildConfigField("String", "\"$environmentMerchantAccount\"", "Merchant Account"),
            )
        }
    }
}

dependencies {

    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")

    api("com.google.android.play:feature-delivery:2.1.0")
    implementation("com.google.android.play:feature-delivery-ktx:2.1.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")

    api("androidx.collection:collection-ktx:1.4.0")

    api("androidx.core:core-ktx:1.12.0")
    api("androidx.appcompat:appcompat:1.6.1")
    api("com.google.android.material:material:1.11.0")
    api("androidx.constraintlayout:constraintlayout:2.1.4")
    api("androidx.navigation:navigation-fragment-ktx:2.7.7")
    api("androidx.navigation:navigation-ui-ktx:2.7.7")
    api("androidx.datastore:datastore-preferences:1.0.0")
    api("com.squareup.okhttp3:okhttp:4.12.0")
    api("com.squareup.okhttp3:logging-interceptor:4.12.0")
    api("com.squareup.logcat:logcat:0.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
