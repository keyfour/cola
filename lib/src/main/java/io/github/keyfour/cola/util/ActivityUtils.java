/*
 * Copyright 2018 Alex Karpov <keyfour13@gmail.com>
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

package io.github.keyfour.cola.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 *
 * Common utils for adding fragments to activities
 *
 * @author Alex Karpov <keyfour13@gmail.com> 2017
 * @version 0.1
 */

public class ActivityUtils {

    public static void addFragmentToActivity (FragmentManager fragmentManager,
                                              Fragment fragment, int frameId, boolean isAddToBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        if (isAddToBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

    public static void replaceFragmentInActivity (FragmentManager fragmentManager,
                                                  Fragment fragment, int frameId, boolean isAddToBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        if (isAddToBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

}
