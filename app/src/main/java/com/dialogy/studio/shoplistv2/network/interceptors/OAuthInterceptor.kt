package com.dialogy.studio.shoplistv2.network.interceptors

import android.content.Context
import com.dialogy.studio.shoplistv2.constants.AUTHENTICATION_SHARED_PREFERENCES_KEY
import com.dialogy.studio.shoplistv2.constants.AUTHORIZATION_KEY
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton


@Singleton
class OAuthInterceptor @Inject constructor(
    @ApplicationContext private val context: Context
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val oauthToken = context.getSharedPreferences(AUTHENTICATION_SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)?.getString(AUTHORIZATION_KEY, "").orEmpty()
        val request = chain.request().newBuilder()
        request.addHeader(AUTHORIZATION_KEY, oauthToken).build().apply {
            return chain.proceed(this)
        }
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OAuthInterceptorImp