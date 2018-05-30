``/*
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

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.Iterator;
import java.util.Set;

import io.github.keyfour.cola.util.SharedPreferencesUtils;

/**
 * @author Alex Karpov <keyfour13@gmail.com> 2018
 */
public class SharedPrefSingleValue<V> extends SingleValueStorage<V> {

    private Context context;

    private static SharedPrefSingleValue instance;

    public SharedPrefSingleValue(String filename, String key, Context context) {
        super(filename, key);
        this.context = context;
    }

    public SharedPrefSingleValue(String filename, String key) {
        super(filename, key);
    }

    public static SharedPrefSingleValue getInstance(String filename, String key) {
        if (instance == null) {
            instance = new SharedPrefSingleValue<>(filename, key);
        }
    }

    public void setContext(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public void put(@NonNull V value) {
        if (context != null) {
            if (value instanceof String) {
                SharedPreferencesUtils.putString(context, filename, key, (String) value);
            } else if (value instanceof Boolean) {
                SharedPreferencesUtils.putBoolean(context, filename, key, (Boolean) value);
            } else if (value instanceof Integer) {
                SharedPreferencesUtils.putInt(context, filename, key, (Integer) value);
            } else if (value instanceof Float) {
                SharedPreferencesUtils.putFloat(context, filename, key, (Float) value);
            } else if (value instanceof Long) {
                SharedPreferencesUtils.putLong(context, filename, key, (Long) value);
            } else if (value instanceof Set && !((Set) value).isEmpty()) {
                Iterator iterator = ((Set) value).iterator();
                if (iterator.next() instanceof String) {
                    SharedPreferencesUtils.putStringSet(context,key, (Set<String>) value);
                }
            } else {
                throw new IllegalArgumentException("Value can be only String, Boolean, Integer," +
                        " Float, Long or Set<String>");
            }
        }
    }

    @Override
    public V get() {
        return null;
    }

    @Override
    public void remove() {

    }
}
