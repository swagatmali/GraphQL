package com.swagatmali.graphql.data.di

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import com.swagatmali.graphql.data.repository.CountryRepoImpl
import com.swagatmali.graphql.domain.repository.CountryRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Singleton
    @Provides
    fun provideApolloClient(): ApolloClient {
        val okHttpClient = OkHttpClient.Builder().build()
        val apolloClient = ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/")
            .okHttpClient(okHttpClient)
            .build()
        return apolloClient
    }

    @Provides
    fun provideCountryRepo(apolloClient: ApolloClient): CountryRepo {
        return CountryRepoImpl(apolloClient)
    }
}