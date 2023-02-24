package com.dialogy.studio.shoplistv2.home.categorydetail.productlist.data

import com.dialogy.studio.shoplistv2.home.categorydetail.productlist.data.model.CategoryProductListResponse
import retrofit2.Call
import javax.inject.Inject

interface ICategoryProductListRepository {
    suspend fun fetchProductList(categoryId: String, page: Int, authorization: String): Call<CategoryProductListResponse?>
}

class CategoryProductListRepository @Inject constructor(
    private val service: ICategoryProductListService
) : ICategoryProductListRepository {
    override suspend fun fetchProductList(categoryId: String, page: Int, authorization: String): Call<CategoryProductListResponse?> =
        service.getProductList(categoryId, page, authorization)

}