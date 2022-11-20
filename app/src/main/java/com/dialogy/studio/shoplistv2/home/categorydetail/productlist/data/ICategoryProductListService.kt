package com.dialogy.studio.shoplistv2.home.categorydetail.productlist.data

import com.dialogy.studio.shoplistv2.home.categorydetail.productlist.data.model.CategoryProductListResponse
import io.github.brunogabriel.mockpinterceptor.MOCK
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ICategoryProductListService {
    @GET("/category/{id}/products")
    @MOCK("category_1_products.json")
    fun getProductList(
        @Path("id")
        categoryId: String
    ): Call<CategoryProductListResponse?>
}
