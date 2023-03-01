package com.dialogy.studio.shoplistv2.home.categorydetail.productlist.data

import com.dialogy.studio.shoplistv2.home.categorydetail.productlist.data.model.CategoryProductListResponse
import io.github.brunogabriel.mockpinterceptor.MOCK
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ICategoryProductListService {
    @GET("/products")
    fun getProductList(
        @Query(value="department")
        categoryId: String,
        @Query(value="page")
        page: Int,
    ): Call<CategoryProductListResponse?>
}
