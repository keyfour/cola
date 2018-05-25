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

import io.github.keyfour.cola.usecase.Params;
import io.reactivex.functions.Consumer;

public class RxParams<T> extends Params {

    private Consumer<T> onNextConsumer;
    private Consumer<? super Throwable> onErrorConsumer;

    private boolean hasOnErrorConsumer;

    public RxParams(Context context, Consumer<T> onNextConsumer) {
        super(context);
        this.onNextConsumer = onNextConsumer;
        hasOnErrorConsumer = false;
    }

    public RxParams(Context context, Consumer<T> onNextConsumer,
                    Consumer<? super Throwable> onErrorConsumer) {
        super(context);
        this.onNextConsumer = onNextConsumer;
        this.onErrorConsumer = onErrorConsumer;
        hasOnErrorConsumer = true;
    }

    public boolean hasOnErrorConsumer() {
        return hasOnErrorConsumer;
    }

    public Consumer<T> getOnNextConsumer() {
        return onNextConsumer;
    }

    public Consumer<? super Throwable> getOnErrorConsumer() {
        return onErrorConsumer;
    }
}
