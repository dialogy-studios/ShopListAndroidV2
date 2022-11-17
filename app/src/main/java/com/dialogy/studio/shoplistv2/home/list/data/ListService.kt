package com.dialogy.studio.shoplistv2.home.list.data

import com.dialogy.studio.shoplistv2.home.list.data.response.GetCategoryWithProductListResponse
import io.github.brunogabriel.mockpinterceptor.MOCK
import retrofit2.Call
import retrofit2.http.GET

interface ListService {
    @GET("https://google.com")
    @MOCK("fetch-list.json")
    fun getData(): Call<GetCategoryWithProductListResponse?>
}