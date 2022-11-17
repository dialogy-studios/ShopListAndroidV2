package com.dialogy.studio.shoplistv2.home.list.data

import com.dialogy.studio.shoplistv2.home.list.data.response.GetCategoryWithProductListResponse
import retrofit2.Call
import javax.inject.Inject

interface IListRepository {
    suspend fun getList(): Call<GetCategoryWithProductListResponse?>
}

class ListRepository @Inject constructor(
    private val service: ListService
) : IListRepository {
    override suspend fun getList(): Call<GetCategoryWithProductListResponse?> = service.getData()

}