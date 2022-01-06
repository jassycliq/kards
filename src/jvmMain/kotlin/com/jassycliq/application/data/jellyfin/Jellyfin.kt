/*
 * MIT License
 *
 * Copyright (c) 2022 Jose Salgado
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.jassycliq.application.data.jellyfin

import com.jassycliq.application.data.GenericMediaServerAPI
import org.jellyfin.sdk.api.client.ApiClient
import org.jellyfin.sdk.api.client.extensions.userApi
import org.jellyfin.sdk.createJellyfin
import org.jellyfin.sdk.model.ClientInfo
import org.jellyfin.sdk.model.api.AuthenticateUserByName
import org.jellyfin.sdk.model.api.AuthenticationResult

class Jellyfin : GenericMediaServerAPI {
    private val jellyfin = createJellyfin {
        clientInfo = ClientInfo(name = "Kards - Media Browser", version = "pre-alpha",)
    }

    override val apiClient: ApiClient = jellyfin.createApi(
    baseUrl = "https://demo.jellyfin.org/stable/",
    // Optional options:
    // accessToken = "access token or api key"
    // httpClientOptions = HttpClientOptions() // allows setting additional options
    )

    override suspend fun authenticateUser(): AuthenticationResult {
        val userApi = apiClient.userApi

        val authenticationResult by userApi.authenticateUserByName(
            AuthenticateUserByName(
                username = "demo",
                password = "",
            )
        )

        // Use access token in api instance
        apiClient.accessToken = authenticationResult.accessToken
        return authenticationResult
    }

}
