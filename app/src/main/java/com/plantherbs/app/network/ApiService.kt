package com.plantherbs.app.network

import com.plantherbs.app.data.remote.datastore.response.LoginResponse
import com.plantherbs.app.data.remote.datastore.response.RegisterResponse
import com.plantherbs.app.model.AddBookmarkResponse
import com.plantherbs.app.model.BookmarkResponse
import com.plantherbs.app.model.DefaultResponse
import com.plantherbs.app.model.DetailResponse
import com.plantherbs.app.model.HerbResponse
import com.plantherbs.app.model.UserResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {

    @FormUrlEncoded
    @POST("auth/register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("user/get/{id}")
    suspend fun getUserById(
        @Path("id") userId: String
    ): UserResponse

    @GET("foods/get/{HerbsId}")
    suspend fun getFoodById(
        @Path("HerbId") foodsId: Int
    ): DetailResponse

    @GET("bookmarks/{userId}")
    suspend fun getUserBookmark(
        @Path("userId") userId: String
    ): BookmarkResponse

    @GET("bookmarks/{userId}/{herbsId}")
    suspend fun getSpesificBookmark(
        @Path("userId") userId: String,
        @Path("foodId") foodId: String,
    ): DetailResponse

    @FormUrlEncoded
    @POST("bookmarks/{userId}")
    suspend fun addBookmark(
        @Path("userId") username: String,
        @Field("HerbId") foodId: String,
    ) : AddBookmarkResponse

    @DELETE("bookmarks/{userId}/{bookmarkId}")
    suspend fun deleteBookmark(
        @Path("userId") userId: String,
        @Path("bookmarkId") bookmarkId: String,
    ) : DefaultResponse


    @DELETE("history/{userId}/{historyId}")
    suspend fun deleteHistory(
        @Path("userId") userId: String,
        @Path("historyId") bookmarkId: String,
    ) : DefaultResponse

    @GET("foods/searchFoodTitle/{keyword}")
    suspend fun getFoodByKeyword(
        @Path("keyword") keyword: String,
    ): HerbResponse

    @GET("foods/searchHerb/{foodName}")
    suspend fun scanHerb(
        @Path("foodName") foodName: String
    ): HerbResponse

}