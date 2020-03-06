package com.shamlu.app.android.buildsrc

/**
 * @author : M.Reza.Nasirloo@gmail.com
 *         Created on: 2018-07-23
 */
object Versions {
    const val vSdkMin = 17
    const val vSdkTarget = 29
    const val vSdkCompile = 29
    const val vVersionCode = 1
    const val vVersionName = "1.1.1"
    const val vBuildTool = "29.0.3"
    const val vKotlin = "1.3.50"

    const val vSupportLib = "28.0.0"
    const val vRxJava2 = "2.2.0"
    const val vRxAndroid = "2.1.0"
    const val vRetrofit = "2.5.0"
    const val vOkHttp = "3.11.0"
    const val vStag = "2.5.1"
    const val vSlick = "1.1.5"

    const val vPlayServices = "17.0.0"
    const val vNavigation = "2.1.0"
    const val vPlayAuth = "17.3.0"
    const val palette = "1.0.0"

    const val gradle = "3.5.0"
    const val coroutines = "1.3.1"
    const val appCompat = "1.1.0-rc01"
    const val coreKtx = "1.1.0"
    const val constraintLayout = "1.1.3"
    const val lifecycle = "2.2.0"
    const val recyclerview = "1.0.0"
    const val safeArgs = "2.1.0"
    const val gson = "2.8.5"
    const val koin = "2.0.1"
    const val dagger = "2.22"
    const val room = "2.1.0-alpha06"
    const val paging = "2.1.0"
    const val retrofit = "2.5.0"
    const val retrofitCoroutines = "0.9.2"
    const val retrofitGson = "2.4.0"
    const val okHttp = "3.12.1"
    const val glide = "4.9.0"
    const val spotifyAuth = "1.1.0"
    const val browser = "1.0.0"

    const val androidTestRunner = "1.3.0-alpha02"
    const val espressoCore = "3.1.0"
    const val mockwebserver = "2.7.5"
    const val archCoreTest = "2.1.0"
    const val androidJunit = "1.1.0"
    const val mockk = "1.9.3"
    const val fragmentTest = "1.1.0"
    const val dataBinding = "3.5.0"
}

object ApplicationId {
    const val id = "com.shamlu.app"
}

object Deps {
    const val lottieAnimation = "com.airbnb.android:lottie:3.0.1"
    const val depSupport = "androidx.legacy:legacy-support-v4:1.0.0"
    const val depSupportAppCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val depSupportGridLayout = "androidx.gridlayout:gridlayout:1.0.0"
    const val depSupportFragment = "androidx.fragment:fragment:${Versions.fragmentTest}"
    const val depSupportAnnotation = "androidx.annotation:annotation:1.0.0"
    const val depSupportDesign = "com.google.android.material:material:1.0.0"
    const val depRecyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val depSupportCardView = "androidx.cardview:cardview:1.0.0"
    const val depConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val depSupportCoreUi = "androidx.legacy:legacy-support-core-ui:1.0.0"

    const val depKotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.vKotlin}"

    const val depRxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.vRxAndroid}"
    const val depRxBinding = "com.jakewharton.rxbinding2:rxbinding:2.1.1"
    const val depRxjava2 = "io.reactivex.rxjava2:rxjava:${Versions.vRxJava2}"
    const val depRxRelays = "com.jakewharton.rxrelay2:rxrelay:2.0.0"

    //Gson and Stag to generate TypeAdapter
    const val depGson = "com.google.code.gson:gson:2.8.5"
    const val depGsonStag = "com.vimeo.stag:stag-library:${Versions.vStag}"
    const val depGsonStagCompiler = "com.vimeo.stag:stag-library-compiler:${Versions.vStag}"

    //OkHttp abd Retrofit
    const val depOkHttp = "com.squareup.okhttp3:okhttp:${Versions.vOkHttp}"
    const val depOkHttpMockwebserver = "com.squareup.okhttp3:mockwebserver:${Versions.vOkHttp}"
    const val depOkHttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.vOkHttp}"
    const val depRetrofit = "com.squareup.retrofit2:retrofit:${Versions.vRetrofit}"
    const val depRetrofitMock = "com.squareup.retrofit2:retrofit-mock:${Versions.vRetrofit}"
    const val depRetrofitConvertorGson = "com.squareup.retrofit2:converter-gson:${Versions.vRetrofit}"
    const val depRetrofitConvertorScalar = "com.squareup.retrofit2:converter-scalars:${Versions.vRetrofit}"
    const val depRetrofitAdapterRxJava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.vRetrofit}"

    //SPOTIFY
    const val spotifyAuth = "com.spotify.android:auth:${Versions.spotifyAuth}"
    const val browser = "androidx.browser:browser:${Versions.browser}"

    const val depDaggerCompiler = "com.google.dagger:dagger-compiler:2.16"
    const val depDagger = "com.google.dagger:dagger:2.16"

    //Barcode scanner
    const val depZxingAndroid = "com.journeyapps:zxing-android-embedded:3.6.0"// transitive = false
    const val depZxingCore = "com.google.zxing:core:3.3.0"

    const val depJavax = "javax.inject:javax.inject:1"
    const val depTimber = "com.jakewharton.timber:timber:4.7.1"

    const val depTestJunit = "junit:junit:4.12"
    const val depTestTruth = "com.google.truth:truth:0.42"
    const val depTestMokito = "org.mockito:mockito-core:2.17.0"
    const val depTestMokitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.0.0-RC1"
    const val depTestRunner = "androidx.test:runner:1.1.0"
    const val depTestMokitoAndroid = "org.mockito:mockito-android:2.17.0"
    const val depTestEspressoCore = "androidx.test.espresso:espresso-core:3.1.0"

    // adjust
    const val adjust = "com.adjust.sdk:adjust-android:4.16.0"
    const val adjustInstallReferrer = "com.android.installreferrer:installreferrer:1.0"
    const val playAnalytics = "com.google.android.gms:play-services-analytics:${Versions.vPlayServices}"
    const val playBase = "com.google.android.gms:play-services-base:${Versions.vPlayServices}"
    const val playAuth = "com.google.android.gms:play-services-auth-api-phone:${Versions.vPlayAuth}"

    //Firebase
    const val depFirebaseCore = "com.google.firebase:firebase-core:16.0.6"
//    val depFirebaseCrashlytics = "com.crashlytics.sdk.android:crashlytics:2.9.8"

//    val navigationFragment = "androidx.navigation:navigation-fragment:${Versions.vNavigation}"
//    val navigationUi = "androidx.navigation:navigation-ui:${Versions.vNavigation}"
//    val navigationRunTime = "androidx.navigation:navigation-runtime-ktx:2.0.0"
}

object Libraries {

    // Dagger2 core
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerDaggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val daggerDaggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val daggerDaggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    // KOIN
    const val koin = "org.koin:koin-android:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-android-viewmodel:${Versions.koin}"
    // ROOM
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomRunTime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    // RETROFIT
    const val retrofitCoroutineAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitCoroutines}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitGson}"
    const val httpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    // GLIDE
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideOkhttpIntegration = "com.github.bumptech.glide:okhttp3-integration:${Versions.glide}"
}

object KotlinLibraries {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.vKotlin}"
    const val kotlinCoroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
}

object AndroidLibraries {
    // KOTLIN
    const val kotlinCoroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    // ANDROID
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val navigation = "androidx.navigation:navigation-ui-ktx:${Versions.vNavigation}"
    const val navigationFrag = "androidx.navigation:navigation-fragment-ktx:${Versions.vNavigation}"
    const val paging = "androidx.paging:paging-runtime:${Versions.paging}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:2.1.0"
    const val lifecycleCommon = "androidx.lifecycle:lifecycle-common-java8:2.1.0-alpha04"
    const val palette = "androidx.palette:palette:${Versions.palette}"

}

object TestLibraries {
    // ANDROID TEST
    const val androidTestRunner = "androidx.test:runner:${Versions.androidTestRunner}"
    const val androidTestCore = "androidx.test:core:1.0.0"
    const val androidTestRules = "androidx.test:rules:1.1.0"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espressoCore}"
    const val archCoreTest = "androidx.arch.core:core-testing:${Versions.archCoreTest}"
    const val junit = "androidx.test.ext:junit:${Versions.androidJunit}"
    const val fragmentNav = "androidx.fragment:fragment-testing:${Versions.fragmentTest}"
    // KOIN
    const val koin = "org.koin:koin-test:${Versions.koin}"
    // MOCK WEBSERVER
    const val mockWebServer = "com.squareup.okhttp:mockwebserver:${Versions.mockwebserver}"
    // MOCK
    const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    // COROUTINE
    const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    // DATA BINDING
    const val databinding = "androidx.databinding:databinding-compiler:${Versions.dataBinding}"
}

object Modules {
    const val app = ":app"
    const val common = ":common"
    const val navigation = ":navigation"
    const val di = ":di"
    const val domain = ":domain"
    const val domainImple = ":domain-impl"
    const val domainNetworkImple = ":domain-network-impl"
    const val login = ":login"
    const val spotifyAppRemote = ":spotify-app-remote"
    const val spotifyPlayer = ":spotify-player"
}
