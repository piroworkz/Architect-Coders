package com.piroworkz.architectcoders

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.android}"
    const val arrowEither = "io.arrow-kt:arrow-core:${Versions.arrowEither}"
    const val javaxInject = "javax.inject:javax.inject:${Versions.javaxInject}"
    const val googleLocationServices =
        "com.google.android.gms:play-services-location:${Versions.gmsLocationServices}"
    const val gradleVersionsPlugin =
        "com.github.ben-manes:gradle-versions-plugin:${Versions.gradleVersionsPlugin}"
    const val busyBee = "io.americanexpress.busybee:busybee-android:${Versions.busyBee}"
}

object Kotlin {
    const val gradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinGradlePlugin}"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesCore}"
}

object AndroidX {
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"
    const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.jetpack}"
    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.jetpack}"
    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.jetpack}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.jetpack}"
    const val navigationSafeArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.jetpack}"
    const val workRuntime = "androidx.work:work-runtime-ktx:${Versions.work}"

    object Views {
        const val touchImageView = "com.github.MikeOrtiz:TouchImageView:${Versions.touchImage}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
        const val materialDesign = "com.google.android.material:material:${Versions.material}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    }
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.room}"
    const val ktx = "androidx.room:room-ktx:${Versions.room}"
    const val compiler = "androidx.room:room-compiler:${Versions.room}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okHttp = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp3}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp3}"
}

object Firebase {
    const val authUi = "com.firebaseui:firebase-ui-auth:${Versions.firebaseAuthUi}"
    const val firestore = "com.google.firebase:firebase-firestore-ktx:${Versions.firestore}"
    const val messaging = "com.google.firebase:firebase-messaging-ktx:${Versions.firebaseMessaging}"
    const val gmsGoogleServices = "com.google.gms:google-services:${Versions.googleServices}"
    const val facebookSdk = "com.facebook.android:facebook-android-sdk:${Versions.facebookSdk}"
    const val firebaseCoroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.firebaseCoroutines}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object Hilt {
    const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    const val android = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val compiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val workManager = "androidx.hilt:hilt-work:${Versions.hiltWork}"
    const val workManagerKapt = "androidx.hilt:hilt-compiler:${Versions.hiltWork}"
    const val testing = "com.google.dagger:hilt-android-testing:${Versions.hilt}"
}

object Testing {
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val extJUnitKtx = "androidx.test.ext:junit-ktx:${Versions.extJUnitKtx}"
    const val espressoContrib =
        "androidx.test.espresso:espresso-contrib:${Versions.espressoContrib}"
    const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockito}"
    const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockitoInline}"
    const val turbine = "app.cash.turbine:turbine:${Versions.turbine}"
    const val truth = "com.google.truth:truth:${Versions.truth}"
    const val runner = "com.android.support.test:runner:${Versions.androidTest}"
    const val rules = "com.android.support.test:rules:${Versions.androidTest}"

}
