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

import android.util.LruCache;

/**
 * @author Alex Karpov <keyfour13@gmail.com> 2018
 */
public class LocalCache<K,V> implements KeyValueDataStorage<K, V> {

    private final int size;
    private final LruCache<K,V> cache;

    public LocalCache(int size) {
        this.size = size;
        this.cache = new LruCache<>(size);
    }

    public LocalCache() {
        this.size =  4 * 1024 * 1024; // 4MiB
        this.cache = new LruCache<>(size);
    }

    public int getSize() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        cache.put(key,value);
    }

    @Override
    public V get(K key, V value) {
        return cache.get(key);
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
    }

    @Override
    public void clear() {
        cache.evictAll();
    }
}
