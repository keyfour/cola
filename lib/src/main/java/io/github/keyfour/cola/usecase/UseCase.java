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

package io.github.keyfour.cola.usecase;

public abstract class UseCase<T, P> implements UseCaseContract<T, P> {

    protected final Executor mainExecutor;
    protected final Executor postExecutor;

    protected UseCase(Executor mainExecutor, Executor postExecutor) {
        this.mainExecutor = mainExecutor;
        this.postExecutor = postExecutor;
    }

    public abstract T build(P params);

    public abstract void execute(P params);

    public abstract void cancel();

}