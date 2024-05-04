# Clean-MVVM-NewsApp

> An Android application built using Clean + MVVM architecture.

## Components used in the app.
- [Kotlin](https://kotlinlang.org/) - As a programming language.
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Modern toolkit for building native UI.
- [AndroidX Browser](https://developer.android.com/jetpack/androidx/releases/browser) - Library for integrating web content using Chrome Custom Tabs.
- [Paging](https://developer.android.com/topic/libraries/architecture/paging) - For handling large data sets smoothly.
- [Work Manager](https://developer.android.com/topic/libraries/architecture/workmanager) - For managing background tasks.
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Lifecycle aware Observable data holder class.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - To manage UI-related data in a lifecycle conscious way.
- [Flow](https://developer.android.com/kotlin/flow) - For handling asynchronous data streams within Android applications.
- [Retrofit](https://square.github.io/retrofit/) - For making network calls.
- [Dagger-Hilt](https://dagger.dev/hilt/) - For dependency injection specifically tailored for Android.
- [Room database](https://developer.android.com/topic/libraries/architecture/room) - To cache the response for offline access.
- C++ - Used to securely store and retrieve the base URL and API key, enhancing the security of the app’s configuration.

## Description
**NewsApp-MVVM-Architecture** demonstrates Clean Architecture and MVVM in Android development. This app uses Kotlin, Retrofit2, Room, and Kotlin Flow, and uses C++ for secure configuration management. It’s an ideal resource to improve skills in building secure, robust Android applications.

## Architecture
![Architecture](https://github.com/sagrawal2418/NewsApp-MVVM-Architecture/blob/main/image.png)

## Screenshots
<p>
<img alt="NewsApp" height="450px" src="https://github.com/sagrawal2418/NewsApp-MVVM-Architecture/blob/main/Screenshot_20240503_171501.png" />
<img alt="NewsApp" height="450px" src="https://github.com/sagrawal2418/NewsApp-MVVM-Architecture/blob/main/Screenshot_20240503_171514.png" />
<img alt="NewsApp" height="450px" src="https://github.com/sagrawal2418/NewsApp-MVVM-Architecture/blob/main/Screenshot_20240503_171523.png" />
<img alt="NewsApp" height="450px" src="https://github.com/sagrawal2418/NewsApp-MVVM-Architecture/blob/main/Screenshot_20240503_171531.png" />
<img alt="NewsApp" height="450px" src="https://github.com/sagrawal2418/NewsApp-MVVM-Architecture/blob/main/Screenshot_20240503_171604.png" />
</p>

## Steps to build the app
- Create your API key at [News API](https://newsapi.org/)
- Navigate to the `nativelib` directory in your project structure. 
- Open the `newsapp.cpp` file. 
- Locate the `getData` method:
   ```cpp
   static std::string getData(bool debugMode) {
       std::string app_secret = "Null";
       if (debugMode) {
           app_secret = "ba07a65dfd724ffd8481044578ae0cb8"; // Local API
       } else {
           app_secret = "ba07a65dfd724ffd8481044578ae0cb8"; // Live API
       }
       return app_secret;
   }
  
- Replace the placeholder API key (ba07a65dfd724ffd8481044578ae0cb8) with your actual API key for both the local and live environments as needed.
- Build and run the app.

## Donation
If this project help you reduce time to develop, you can buy me a cup of coffee :)

<a href="https://buymeacoffee.com/sandeepagrz" target="_blank"><img src="https://bmc-cdn.nyc3.digitaloceanspaces.com/BMC-button-images/custom_images/orange_img.png" alt="Buy Me A Coffee" style="height: auto !important;width: auto !important;" ></a>

Thank you!