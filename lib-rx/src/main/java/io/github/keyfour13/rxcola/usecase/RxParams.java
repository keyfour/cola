/*
 * Copyright 2018 Alexander Karpov <keyfour13@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.github.keyfour13.rxcola.usecase;

import android.content.Context;
import android.support.annotation.NonNull;

import io.github.keyfour.cola.usecase.Params;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class RxParams<R, T> extends Params<T> {

    protected RxThreadExecutor mainExecutor;
    protected RxThreadExecutor postExecutor;
    protected Observable<R> observable;
    protected Consumer<R> onNextConsumer;
    protected Consumer<? super Throwable> onErrorConsumer;

    private boolean hasOnErrorConsumer;


    protected RxParams(@NonNull Context context) {
        super(context);
        hasOnErrorConsumer = false;
    }

    protected RxParams(@NonNull Context context, @NonNull Consumer<R> onNextConsumer) {
        super(context);
        this.onNextConsumer = onNextConsumer;
        hasOnErrorConsumer = false;
    }

    protected RxParams(@NonNull Context context, @NonNull Consumer<R> onNextConsumer,
                    @NonNull Consumer<? super Throwable> onErrorConsumer) {
        super(context);
        this.onNextConsumer = onNextConsumer;
        this.onErrorConsumer = onErrorConsumer;
        hasOnErrorConsumer = true;
        observable = null;
    }

    protected RxParams(@NonNull Context context, @NonNull Observable<R> observable,
                       @NonNull Consumer<R> onNextConsumer,
                       @NonNull Consumer<? super Throwable> onErrorConsumer) {
        super(context);
        this.observable = observable;
        this.onNextConsumer = onNextConsumer;
        this.onErrorConsumer = onErrorConsumer;
    }

    public boolean hasOnErrorConsumer() {
        return hasOnErrorConsumer;
    }

    public Observable<R> getObservable() {
        return observable;
    }

    public Consumer<R> getOnNextConsumer() {
        return onNextConsumer;
    }

    public Consumer<? super Throwable> getOnErrorConsumer() {
        return onErrorConsumer;
    }

    public static <R,T> RxParams<R,T> build(@NonNull Context context) {
        return new RxParams<>(context);
    }

    public RxParams<R,T> setObservable(@NonNull Observable<R> observable) {
        this.observable = observable;
        return this;
    }

    public RxParams<R,T> setOnNextConsumer(@NonNull Consumer<R> consumer) {
        this.onNextConsumer = consumer;
        return this;
    }

    public RxParams<R,T> setOnErrorConsumer(@NonNull Consumer<? super Throwable> consumer) {
        this.onErrorConsumer = consumer;
        return  this;
    }

    public RxParams<R,T> setMainExecutor(@NonNull RxThreadExecutor executor) {
        this.mainExecutor = executor;
        return this;
    }

    public RxParams<R,T> setPostExecutor(@NonNull RxThreadExecutor executor) {
        this.postExecutor = executor;
        return this;
    }
}
