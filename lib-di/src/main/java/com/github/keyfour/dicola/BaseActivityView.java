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

package com.github.keyfour.dicola;

import android.content.Context;
import android.os.Bundle;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.github.keyfour.cola.contract.PresenterContract;
import io.github.keyfour.cola.contract.ViewContract;

/**
 * @author Alex Karpov <keyfour13@gmail.com> 2018
 *
 * MVP View class based on {@link DaggerAppCompatActivity}
 *
 */
public abstract class BaseActivityView<V> extends DaggerAppCompatActivity
        implements ViewContract<V> {

    /**
     * Instance of {@link PresenterContract} to interact with presenter
     */
    @Inject
    protected PresenterContract<V> presenter;

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    public abstract void updateView(V model);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (presenter != null) {
            presenter.setView(this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.start();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.stop();
        }
    }

}
