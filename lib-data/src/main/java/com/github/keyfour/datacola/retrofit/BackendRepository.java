/*
 * Copyright 2018 Alexander Karpov <keyfour13@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.keyfour.datacola.retrofit;

import android.support.annotation.NonNull;

import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author Alex Karpov <keyfour13@gmail.com> 2018
 */
public abstract class BackendRepository<S> {

    private final Retrofit retrofit;
    private final Class serviceClass;
    private final S service;

    protected BackendRepository(@NonNull Retrofit retrofit, Class<S> serviceClass) {
        this.retrofit = retrofit;
        this.serviceClass = serviceClass;
        this.service = retrofit.create(serviceClass);
    }

    protected BackendRepository(@NonNull String url, Class<S> serviceClass) {
        this.retrofit = new Retrofit.Builder().baseUrl(url).build();
        this.serviceClass = serviceClass;
        this.service = retrofit.create(serviceClass);
    }

    protected BackendRepository(@NonNull String url, @NonNull Converter.Factory converter,
                                Class<S> serviceClass) {
        this.retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(converter).build();
        this.serviceClass  = serviceClass;
        this.service = retrofit.create(serviceClass);
    }

    protected BackendRepository(@NonNull String url, @NonNull Converter.Factory converter,
                                @NonNull CallAdapter.Factory adapter, Class<S> serviceClass) {
        this.retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(converter)
                .addCallAdapterFactory(adapter).build();
        this.serviceClass = serviceClass;
        this.service = retrofit.create(serviceClass);
    }

    public S getService() {
        return service;
    }

}
