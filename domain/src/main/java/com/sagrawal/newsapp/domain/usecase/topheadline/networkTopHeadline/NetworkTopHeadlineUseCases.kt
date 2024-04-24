package com.sagrawal.newsapp.domain.usecase.topheadline.networkTopHeadline

data class NetworkTopHeadlineUseCases(
    val getTopHeadlineUseCase: GetNetworkTopHeadlineUseCase,
    val getNewsByLanguageUseCase: GetNewsByLanguageUseCase,
    val getNewsBySourceUseCase: GetNewsBySourceUseCase
)