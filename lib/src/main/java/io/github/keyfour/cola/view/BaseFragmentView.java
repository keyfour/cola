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

package io.github.keyfour.cola.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import io.github.keyfour.cola.contract.PresenterContract;
import io.github.keyfour.cola.contract.ViewContract;

/**
 * @author Alex Karpov <keyfour13@gmail.com> 2018
 *
 * MVP View class based on {@link Fragment} and {@link ViewContract}
 *
 */
public abstract class BaseFragmentView<V> extends Fragment implements ViewContract<V> {

    PresenterContract presenter;

    public BaseFragmentView(@NonNull PresenterContract presenter) {
        this.presenter = presenter;
        this.presenter.setView(this);
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public abstract void updateView(V model);

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.stop();
    }

}
