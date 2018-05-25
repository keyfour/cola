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

package com.github.keyfour.example;


import android.support.annotation.NonNull;

import io.github.keyfour.cola.contract.RequestPresenterContract;
import io.github.keyfour.cola.contract.ViewContract;
import io.github.keyfour.cola.presenter.BasePresenter;
import io.github.keyfour13.rxcola.usecase.RxIoThreadExecutor;
import io.github.keyfour13.rxcola.usecase.RxMainThreadExecutor;
import io.github.keyfour13.rxcola.usecase.RxParams;
import io.reactivex.functions.Consumer;

/**
 * @author Alex Karpov <keyfour13@gmail.com> 2018
 */
public class ExamplePresenter extends BasePresenter implements
        RequestPresenterContract<String,String> {

    private final ExampleUseCase useCase = new ExampleUseCase();

    @Override
    public void start() {
        request(null,null);
    }

    @Override
    public void request(String type, String data) {
        RxParams<String, String> params =
                ExampleUseCase.ExampleParams.build(view.getViewContext())
                        .setMainExecutor(RxIoThreadExecutor.getInstance())
                        .setPostExecutor(RxMainThreadExecutor.getInstance())
                        .setOnNextConsumer(new ExampleConsumer(view));
        useCase.configure(params).execute();

    }

    public static class ExampleConsumer implements Consumer<String> {

        private ViewContract<String> view;

        ExampleConsumer(@NonNull ViewContract<String> view) {
            this.view = view;
        }

        @Override
        public void accept(String s) throws Exception {
            if (s != null) {
                view.updateView(s);
            }
        }
    }

    @Override
    public void stop() {
        useCase.cancel();
    }
}
