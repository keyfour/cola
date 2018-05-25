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

import io.github.keyfour.cola.usecase.UseCase;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class RxUseCase<R,T> extends UseCase<RxParams<R,T>> {

    protected CompositeDisposable disposables = new CompositeDisposable();
    protected RxParams<R,T> params;

    @Override
    public UseCase configure(RxParams<R, T> params) {
        this.params = params;
        return this;
    }

    @Override
    public RxUseCase<R,T> execute() {
        Disposable disposable;
        if (params.hasOnErrorConsumer()) {
            disposable = params.getObservable().subscribe(params.getOnNextConsumer(),
                    params.getOnErrorConsumer());
        } else {
            disposable = params.getObservable().subscribe(params.getOnNextConsumer());
        }
        disposables.add(disposable);
        return this;
    }

    @Override
    public RxUseCase<R,T> cancel() {
        if (disposables != null && !disposables.isDisposed()) {
            disposables.dispose();
        }
        return this;
    }
}
