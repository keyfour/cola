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

package io.github.keyfour.cola.contract;

/**
 * @author Alex Karpov <keyfour13@gmail.com> 2018
 *
 * Extension for {@link ViewContract}
 *
 */
public interface LoadViewContract<V> extends ViewContract<V> {

    /**
     * Set view to show or hide load indication
     * @param enabled
     */
    void setLoadIndicatorEnabled(boolean enabled);

    /**
     * Attach message to load indication
     * @param message
     */
    void setLoadIndicatorMessage(String message);

}
