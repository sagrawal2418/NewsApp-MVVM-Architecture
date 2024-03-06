package com.sagrawal.newsapp.domain.usecase.topheadline

data class TopHeadlineUseCases(
    val getTopHeadlineUseCase: GetTopHeadlineUseCase,
    val getNewsByLanguageUseCase: GetNewsByLanguageUseCase,
    val getNewsBySourceUseCase: GetNewsBySourceUseCase
)