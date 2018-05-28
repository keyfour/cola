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
import android.support.annotation.NonNull;

import io.github.keyfour13.rxcola.usecase.Params;
import io.github.keyfour13.rxcola.usecase.UseCase;
import io.reactivex.Observable;

public class ExampleUseCase extends UseCase<String, String> {

    public ExampleUseCase() {
        super(threadExecutor, postExecutionThread, disposables);
    }

    public static class ExampleParams extends Params<String, String> {
        final String str = "Hello World!";

        protected ExampleParams(@NonNull Context context) {
            super(context);
            this.observable = Observable.just(str);
        }

        public static ExampleParams build(@NonNull Context context) {
            return new ExampleParams(context);
        }

        public ExampleParams setValue(String value) {
            setValues(value);
            return this;
        }
    }
}
