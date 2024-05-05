# Clean-MVVM-NewsApp
![Architecture](https://github.com/sagrawal2418/NewsApp-MVVM-Architecture/blob/main/Your%20paragraph%20text%20(1).png)

> An Android application built using Clean + MVVM architecture.

## Major Highlights.
- [MVVM Architecture](https://developer.android.com/topic/architecture) - For structuring code to separate business logic from UI concerns, promoting a clean app development environment.
- [Offline-First](https://developer.android.com/topic/architecture/data-layer/offline-first) - Approach to app design that prioritizes device-side data management for robust offline usability.
- [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) - Kotlin's state management tool that builds on Flow to make data streams lifecycle-aware.
- [Coroutines](https://developer.android.com/kotlin/coroutines) - For asynchronous programming, allowing network and heavy computations to be handled smoothly.
- [Unit Testing](https://developer.android.com/training/testing/unit-testing) - For validating each component functions correctly in isolation.
- [UI Testing](https://developer.android.com/training/testing/ui-testing) - For ensuring the user interface works as expected across different devices and configurations.
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

## Features Implemented.
<h2>App Features</h2>
<ul>
  <li><strong>Fetching News:</strong> Utilizes Retrofit to retrieve the latest news updates efficiently.</li>
  <li><strong>Pagination:</strong> Implemented via the Paging library to manage data loading in manageable chunks, enhancing performance.</li>
  <li><strong>Top Headlines News:</strong> Highlights the most important news, making them accessible at a glance.</li>
  <li><strong>News Based on Source:</strong> Allows users to filter news content based on their preferred news outlets.</li>
  <li><strong>News Based on Country Selection:</strong> Enables filtering news by country, catering to geo-specific audiences.</li>
  <li><strong>News Based on Language Selection:</strong> Supports multiple languages, allowing users to receive news in their preferred language.</li>
  <li><strong>Instant Search:</strong> Enhanced by Kotlin Flow operators for real-time search capabilities:
    <ul>
      <li><strong>Debounce:</strong> Minimizes API call overload by pausing for a set time frame before executing the search.</li>
      <li><strong>Filter:</strong> Ensures only relevant results pass through the search query.</li>
      <li><strong>DistinctUntilChanged:</strong> Prevents duplicate results from being shown to the user.</li>
      <li><strong>FlatMapLatest:</strong> Ensures only the latest search inputs are considered for fetching data.</li>
    </ul>
  </li>
  <li><strong>Offline Support:</strong> Integrates Room database for caching articles, enabling offline reading.</li>
  <li><strong>WorkManager for Periodic News Fetching:</strong> Uses WorkManager to schedule background updates of news data at regular intervals.</li>
  <li><strong>Testing Frameworks:</strong>
    <ul>
      <li><strong>Unit Tests:</strong> Ensures that individual components function correctly, utilizing frameworks like JUnit.</li>
      <li><strong>Mockito:</strong> Facilitates mocking objects in tests to isolate and focus on the component being tested.</li>
      <li><strong>Espresso:</strong> Automates UI tests to ensure the app performs as expected on a device.</li>
      <li><strong>Turbine:</strong> A testing library for Kotlin Flows that simplifies the process of asserting flow emissions.</li>
    </ul>
  </li>
</ul>

## Complete Project Structure
![Project Structure](https://github.com/sagrawal2418/NewsApp-MVVM-Architecture/blob/main/Screenshot%202024-05-04%20at%2010.30.39%20AM.png)


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
