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

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import io.github.keyfour.cola.view.BaseActivityView;

/**
 * @author Alex Karpov <keyfour13@gmail.com> 2018
 */
public class ExampleActivity extends BaseActivityView<Integer> {

    TextView textView;

    @Override
    public void onCreate(Bundle onSavedInstanceState) {
        this.presenter = new ExamplePresenter();
        super.onCreate(onSavedInstanceState);
        this.presenter.setView(this);
    }

    @Override
    public void updateView(@NonNull Integer model) {
        textView.setText(model.toString());
    }
}
