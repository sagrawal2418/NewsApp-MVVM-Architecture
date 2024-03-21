package com.sagrawal.newsapp.presentation

import com.sagrawal.newsapp.domain.model.ApiArticle
import com.sagrawal.newsapp.domain.model.ApiSource

object TestUtils {
    val testApiArticles: List<ApiArticle> = listOf(
        ApiArticle(
            title = "title1",
            description = "description1",
            url = "url1",
            imageUrl = "imageUrl1",
            apiSource = ApiSource(id = "sourceId1", name = "sourceName1")
        ),
        ApiArticle(
            title = "title2",
            description = "description2",
            url = "url2",
            imageUrl = "imageUrl2",
            apiSource = ApiSource(id = "sourceId2", name = "sourceName2")
        ),
        ApiArticle(
            title = "title3",
            description = "description3",
            url = "url3",
            imageUrl = "imageUrl3",
            apiSource = ApiSource(id = "sourceId3", name = "sourceName3")
        ),
        ApiArticle(
            title = "title4",
            description = "description4",
            url = "url4",
            imageUrl = "imageUrl4",
            apiSource = ApiSource(id = "sourceId4", name = "sourceName4")
        ),
        ApiArticle(
            title = "title5",
            description = "description5",
            url = "url5",
            imageUrl = "imageUrl5",
            apiSource = ApiSource(id = "sourceId5", name = "sourceName5")
        ),
        ApiArticle(
            title = "title6",
            description = "description6",
            url = "url6",
            imageUrl = "imageUrl6",
            apiSource = ApiSource(id = "sourceId6", name = "sourceName6")
        )
    )

}