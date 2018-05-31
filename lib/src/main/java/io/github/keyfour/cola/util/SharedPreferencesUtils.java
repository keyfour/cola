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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class SharedPreferencesUtils {

    public static final String DEFAULT_FILE_NAME = "preferences";
    public static final Class CLASSES[] = {String.class, Integer.class, Boolean.class, Float.class,
                                    Long.class, Set.class};
    public static final String PREFIXES[] = {"str_", "int_", "bool_", "float_", "long_", "set_"};
    private static final String GET_METHODS[] = {"getString", "getInt", "getBoolean", "getFloat",
            "getLong", "getStringSet"};
    private static final String PUT_METHODS[] = {"putString", "putInt", "putBoolean", "putFloat",
            "putLong"};


    public static void putValue(@NonNull Context context, @NonNull String filename,
                                @NonNull String key, @NonNull Object value) {
        SharedPreferences.Editor editor = getSharedPreferences(context, filename).edit();
        put(editor, key, value);
        editor.apply();
    }

    public static void putValue(@NonNull Context context, @NonNull String key,
                                @NonNull Object value) {
        putValue(context, DEFAULT_FILE_NAME, key, value);
    }

    public static Object getValue(@NonNull Context context, @NonNull String filename,
                                @NonNull String key, @NonNull Class clazz) {
        if (clazz.isInstance(String.class)) {
            return getSharedPreferences(context, filename).getString("str_" + key, "");
        } else if (clazz.isInstance(Integer.class)) {
            return getSharedPreferences(context, filename).getInt("int_" + key, 0);
        } else if (clazz.isInstance(Boolean.class)) {
            return getSharedPreferences(context, filename).getBoolean("bool_" + key, false);
        } else if (clazz.isInstance(Float.class)) {
            return getSharedPreferences(context, filename).getFloat(key, 0.0f);
        } else if (clazz.isInstance(Long.class)) {
            return getSharedPreferences(context, filename).getLong(key, 0L);
        } else if (clazz.isInstance(Set.class)) {
            return getSharedPreferences(context, filename).getStringSet(key, null);
        } else {
            return null;
        }
    }

    public static Object getValue(@NonNull Context context, @NonNull String key,
                                  @NonNull Class clazz) {
        return getValue(context, DEFAULT_FILE_NAME, key, clazz);
    }

    public static void putValues(@NonNull Context context, @NonNull String filename,
                                   @NonNull HashMap<String, Object> hashMap) {
        SharedPreferences.Editor editor = getSharedPreferences(context, filename).edit();
        Set<String> keys = hashMap.keySet();
        for (String key : keys) {
            put(editor, key, hashMap.get(key));
        }
        editor.apply();
    }

    private static SharedPreferences.Editor put(@NonNull SharedPreferences.Editor editor,
                                                @NonNull String key, @NonNull Object value) {
        if (value instanceof String) {
            return editor.putString("str_" + key, (String) value);
        } else if (value instanceof Integer) {
            return  editor.putInt("int_" + key, (Integer) value);
        } else if (value instanceof Boolean) {
            return editor.putBoolean("bool_" + key, (Boolean) value);
        } else if (value instanceof Float) {
            return editor.putFloat("float_" + key, (Float) value);
        } else if (value instanceof Long) {
            return editor.putLong("long_" + key, (Long) value);
        } else if (value instanceof Set  && !((Set) value).isEmpty()) {
            Iterator iterator = ((Set) value).iterator();
            if (iterator.next() instanceof String) {
                return editor.putStringSet("set_" + key, (Set<String>) value);
            } else {
                throw new IllegalArgumentException("Set must consist of String objects");
            }
        } else {
            throw new IllegalArgumentException("Value can be only String, Boolean, Integer, " +
                    "Float, Long or Set<String>");
        }
    }

    private static SharedPreferences getSharedPreferences(@NonNull Context context,
                                                          @NonNull String filename) {
        return context.getSharedPreferences(filename,
                    Context.MODE_PRIVATE);
    }

}
