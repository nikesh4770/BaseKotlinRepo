package com.app.recipe.di

import android.content.Context
import androidx.room.Room
import com.app.recipe.BuildConfig
import com.app.recipe.db.user.UserDatabase
import com.app.recipe.network.api.ApiKeyInterceptor
import com.app.recipe.network.api.RecipeApiInterface
import com.app.recipe.other.Constant.BASE_URL
import com.app.recipe.other.Constant.DATABASE_NAME
import com.app.recipe.repositories.RecipeRepository
import com.app.recipe.repositories.RecipeRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesUserDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, UserDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun providesUserDao(userDatabase: UserDatabase) = userDatabase.userDao()

    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun providesApiKeyInterceptor(): ApiKeyInterceptor = ApiKeyInterceptor()

    @Singleton
    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        apiKeyInterceptor: ApiKeyInterceptor
    ) =
        OkHttpClient.Builder().also { builder ->
            if (BuildConfig.DEBUG) {
                builder.addInterceptor(httpLoggingInterceptor)
            }
            builder.addInterceptor(apiKeyInterceptor)
        }.build()

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .build()

    @Singleton
    @Provides
    fun providesRecipeApiInterface(
        retrofit: Retrofit
    ): RecipeApiInterface = retrofit.create(RecipeApiInterface::class.java)

    @Singleton
    @Provides
    fun providesRecipeRepositoryInterface(apiInterface: RecipeApiInterface) =
        RecipeRepository(apiInterface) as RecipeRepositoryInterface
}