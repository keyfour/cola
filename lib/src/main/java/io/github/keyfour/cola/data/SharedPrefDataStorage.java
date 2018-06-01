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
package io.github.keyfour.cola.data;

import android.app.Application;
import android.support.annotation.NonNull;

import io.github.keyfour.cola.util.SharedPreferencesUtils;

/**
 * @author Alex Karpov <keyfour13@gmail.com> 2018
 */
public class SharedPrefDataStorage implements KeyValueDataStorage<String,Object> {

    private final Application application;
    private final String filename;

    private static SharedPrefDataStorage instance;

    public SharedPrefDataStorage(Application application, String filename) {
        this.application = application;
        this.filename = filename;
    }


    public static SharedPrefDataStorage getInstance(Application application, String filename) {
        if (instance == null) {
            instance = new SharedPrefDataStorage(application, filename);
        }
        return instance;
    }

    @Override
    public void put(@NonNull String key, Object value) {
        if (value == null) value = "";
        SharedPreferencesUtils.putValue(application, filename, key, value);
    }

    @Override
    public Object get(@NonNull String key, Object value) {
        if (value == null) value = "";
        return SharedPreferencesUtils.getValue(application, filename, key, value.getClass());
    }

    @Override
    public void remove(String key) {
        SharedPreferencesUtils.removeValue(application, filename, key);
    }

    @Override
    public void clear() {
        SharedPreferencesUtils.removeAllValues(application, filename);
    }
}
