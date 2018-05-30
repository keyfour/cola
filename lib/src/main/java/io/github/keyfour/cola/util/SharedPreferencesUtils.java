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

package io.github.keyfour.cola.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.Set;

public class SharedPreferencesUtils {

    public static final String DEFAULT_FILE_NAME = "preferences";

    public static void addString(@NonNull Context context, @NonNull String filename,
                                 @NonNull String key, @NonNull String value) {
        getSharedPreferences(context, filename).edit().putString(key,value).apply();
    }

    public static void addString(@NonNull Context context, @NonNull String key,
                                 @NonNull String value) {
        addString(context, DEFAULT_FILE_NAME, key, value);
    }

    public static void addInt(@NonNull Context context, @NonNull String filename,
                              @NonNull String key, @NonNull Integer value) {
        getSharedPreferences(context, filename).edit().putInt(key, value).apply();
    }

    public static void addInt(@NonNull Context context, @NonNull String key,
                              @NonNull Integer value) {
        addInt(context, DEFAULT_FILE_NAME, key, value);
    }

    public static void addBoolean(@NonNull Context context, @NonNull String filename,
                                  @NonNull String key, @NonNull Boolean value) {
        getSharedPreferences(context, filename).edit().putBoolean(key, value).apply();
    }

    public static void addBoolean(@NonNull Context context, @NonNull String key,
                                  @NonNull Boolean value) {
        addBoolean(context, DEFAULT_FILE_NAME, key, value);
    }

    public static void addFloat(@NonNull Context context, @NonNull String filename,
                                @NonNull String key, @NonNull Float value) {
        getSharedPreferences(context, filename).edit().putFloat(key, value).apply();
    }

    public static void addFloat(@NonNull Context context, @NonNull String key,
                                @NonNull Float value) {
        addFloat(context, DEFAULT_FILE_NAME, key, value);
    }

    public static void addLong(@NonNull Context context, @NonNull String filename,
                               @NonNull String key, @NonNull Long value) {
        getSharedPreferences(context, filename).edit().putLong(key, value).apply();
    }

    public static void addLong(@NonNull Context context, @NonNull String key,
                               @NonNull Long value) {
        addLong(context, DEFAULT_FILE_NAME, key, value);
    }

    public static void addStringSet(@NonNull Context context, @NonNull String filename,
                                    @NonNull String key, @NonNull Set<String> values) {
        getSharedPreferences(context, filename).edit().putStringSet(key, values).apply();
    }

    public static void addStringSet(@NonNull Context context, @NonNull String key,
                                    @NonNull Set<String> values) {
        addStringSet(context, DEFAULT_FILE_NAME, key, values);
    }

    public static void removeObject(@NonNull Context context, @NonNull String filename,
                                    @NonNull String key) {
        getSharedPreferences(context, filename).edit().remove(key).apply();
    }

    public static void removeAllObjects(@NonNull Context context, @NonNull String filename){
        getSharedPreferences(context, filename).edit().clear().apply();
    }

    public static void removeAllObjects(Context context) {
        removeAllObjects(context, DEFAULT_FILE_NAME);
    }

    private static SharedPreferences getSharedPreferences(@NonNull Context context,
                                                          @NonNull String filename) {
        return context.getSharedPreferences(filename,
                    Context.MODE_PRIVATE);
    }

}
