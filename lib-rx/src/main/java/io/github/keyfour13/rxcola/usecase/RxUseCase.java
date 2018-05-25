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

import io.github.keyfour.cola.usecase.Executor;
import io.github.keyfour.cola.usecase.UseCase;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class RxUseCase<T> extends UseCase<Observable<T>, RxParams<T>> {

    private CompositeDisposable disposables;

    protected RxUseCase(Executor mainExecutor, Executor postExecutor) {
        super(mainExecutor, postExecutor);
    }

    @Override
    public abstract Observable<T> build(RxParams<T> params);

    @Override
    public void execute(RxParams<T> params) {
        final Observable<T> observable = this.build(params)
                .subscribeOn(((RxThreadExecutor)mainExecutor).getScheduler())
                .observeOn(((RxThreadExecutor)postExecutor).getScheduler());
        Disposable disposable;
        if (params.hasOnErrorConsumer()) {
            disposable = observable.subscribe(params.getOnNextConsumer(),
                    params.getOnErrorConsumer());
        } else {
            disposable = observable.subscribe(params.getOnNextConsumer());
        }
        disposables.add(disposable);
    }

    @Override
    public void cancel() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }
}
