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

package com.github.keyfour.example;

import android.content.Context;

import io.github.keyfour.cola.usecase.Executor;
import io.github.keyfour13.rxcola.usecase.RxParams;
import io.github.keyfour13.rxcola.usecase.RxUseCase;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class ExampleUseCase extends RxUseCase<String> {

    protected ExampleUseCase(Executor mainExecutor, Executor postExecutor) {
        super(mainExecutor, postExecutor);
    }

    @Override
    public Observable<String> build(RxParams<String> params) {
        return Observable.just(true).map(aBoolean -> ((ExampleParams)params).str);
    }

    public static class ExampleParams extends RxParams<String> {
        final String str = "Hello World!";

        public ExampleParams(Context context, Consumer<String> consumer) {
            super(context, consumer);
        }
    }
}
