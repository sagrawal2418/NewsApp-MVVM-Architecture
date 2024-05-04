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
**NewsApp-MVVM-Architecture** is a showcase Android application designed to demonstrate the implementation of Clean Architecture and the MVVM pattern in a modern Android app. This project serves as an educational tool for developers interested in adopting these architectural principles in their applications. In addition to employing Kotlin, Retrofit2 for network operations, Room database for data persistence, and Kotlin Flow for handling asynchronous data streams, **NewsApp-MVVM-Architecture** uniquely integrates C++ to manage critical configuration details securely. By storing the base URL and API key within native C++ code, the application enhances security, preventing easy access to sensitive information and demonstrating an effective strategy for protecting app configurations. This app is an excellent resource for developers looking to enhance their understanding of Android application architecture, particularly those interested in seeing how different programming languages and technologies can be combined to create robust, scalable, and maintainable applications. By exploring this app, developers can gain hands-on experience with advanced technologies and design patterns, as well as strategies for secure data management.
