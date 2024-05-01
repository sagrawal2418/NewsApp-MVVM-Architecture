package com.sagrawal.newsapp

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.sagrawal.newsapp.utils.AppConstant
import com.sagrawal.newsapp.utils.TimeUtil
import com.sagrawal.newsapp.worker.FetchTopHeadlinesWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class NewsApplication : Application(), Configuration.Provider{

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var workManager: WorkManager

    override fun onCreate() {
        super.onCreate()
        initWorkManager()
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    private fun initWorkManager() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = PeriodicWorkRequest.Builder(
            FetchTopHeadlinesWorker::class.java,
            24,
            TimeUnit.HOURS
        )
            .setConstraints(constraints)
            .setInitialDelay(TimeUtil.getInitialDelay(), TimeUnit.MILLISECONDS)
            .addTag(AppConstant.UNIQUE_WORK_NAME)
            .build()

        workManager.enqueueUniquePeriodicWork(
            AppConstant.UNIQUE_WORK_NAME,
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE, workRequest
        )
    }
}