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

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public abstract class UseCase<T>  {

    private final ThreadExecutor threadExecutor;
    private final ThreadExecutor postExecutionThread;
    private final CompositeDisposable disposables;
    private final Consumer<? super Throwable> errorConsumer;

    public UseCase(ThreadExecutor threadExecutor, ThreadExecutor postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.disposables = new CompositeDisposable();
        this.errorConsumer = new ErrorConsumer();
    }

    /**
     *  Build an {@link Observable} for executing current {@link UseCase}
     */
    abstract Observable<T> buildUseCaseObservable(Params params);

    /**
     *
     * Execute for end-point consumers
     *
     * @param consumer end-point {@link Consumer}
     * @param errorConsumer error(@link {@link Consumer}
     * @param params parameters for execution
     */
    public void execute(Consumer<T> consumer, Consumer<? super Throwable> errorConsumer,
                        Params params) {
        final Observable<T> observable = this.buildUseCaseObservable(params)
                .subscribeOn(threadExecutor.getScheduler())
                .observeOn(postExecutionThread.getScheduler());
        Disposable disposable = observable.subscribe(consumer, errorConsumer);
        disposables.add(disposable);
    }

    /**
     *
     * Execute for end-point consumers
     *
     * @param consumer end-point {@link Consumer}
     * @param errorConsumer error(@link {@link Consumer}
     */
    public void execute(Consumer<T> consumer, Consumer<? super Throwable> errorConsumer) {
        final Observable<T> observable = this.buildUseCaseObservable(null)
                .subscribeOn(threadExecutor.getScheduler())
                .observeOn(postExecutionThread.getScheduler());
        Disposable disposable = observable.subscribe(consumer, errorConsumer);
        disposables.add(disposable);
    }

    /**
     *
     * Execute for end-point consumers
     *
     * @param consumer end-point {@link Consumer}
     * @param params parameters for execution
     */
    public void execute(Consumer<T> consumer, Params params) {
        final Observable<T> observable = this.buildUseCaseObservable(params)
                .subscribeOn(threadExecutor.getScheduler())
                .observeOn(postExecutionThread.getScheduler());
        Disposable disposable = observable.subscribe(consumer, errorConsumer);
        disposables.add(disposable);
    }

    /**
     *
     * Execute for end-point consumers
     *
     * @param consumer end-point {@link Consumer}
     */
    public void execute(Consumer<T> consumer) {
        final Observable<T> observable = this.buildUseCaseObservable(null)
                .subscribeOn(threadExecutor.getScheduler())
                .observeOn(postExecutionThread.getScheduler());
        Disposable disposable = observable.subscribe(consumer, errorConsumer);
        disposables.add(disposable);
    }

    /**
     * Execute for end-point observer
     *
     * @param observer end-point {@link Observer}
     * @param params {@link Params} for execution
     */
    public void execute(Observer<T> observer, Params params) {
        final Observable<T> observable = this.buildUseCaseObservable(params)
                .subscribeOn(threadExecutor.getScheduler())
                .observeOn(postExecutionThread.getScheduler());
        observable.safeSubscribe(observer);
    }

    /**
     * Execute for end-point observer
     *
     * @param observer end-point {@link Observer}
     */
    public void execute(Observer<T> observer) {
        final Observable<T> observable = this.buildUseCaseObservable(null)
                .subscribeOn(threadExecutor.getScheduler())
                .observeOn(postExecutionThread.getScheduler());
        observable.safeSubscribe(observer);
    }

    /**
     *
     * Execute for intermediate observers
     *
     * @param params parameters for execution
     * @return {@link Observable}
     */
    public Observable<T> execute(Params params) {
        return this.buildUseCaseObservable(params)
                .subscribeOn(threadExecutor.getScheduler())
                .observeOn(postExecutionThread.getScheduler());
    }

    /**
     *
     * Execute for intermediate observers
     *
     * @return {@link Observable}
     */
    public Observable<T> execute() {
        return this.buildUseCaseObservable(null)
                .subscribeOn(threadExecutor.getScheduler())
                .observeOn(postExecutionThread.getScheduler());
    }

    /**
     * Unsubscibe from all subscriptions
     */
    public void unsubscribe() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }
}
