/*
 * Copyright 2007-2009 Jiemamy Project and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.jiemamy.utils.functor.core;

import org.jiemamy.utils.functor.Editor;

/**
 * {@link Editor}の骨格実装。
 * 
 * @version $Date: 2009-11-18 22:18:44 +0900 (水, 18 11 2009) $
 * @author Suguru ARAKAWA
 * @param <D> 操作対象の値の型
 * @param <S> 操作引数の型
 */
public abstract class AbstractEditor<D, S> implements Editor<D, S> {
//
//    public Functor<D, S> asFunctor(Generator<? extends D> source) {
//        if (source == null) {
//            throw new IllegalArgumentException("source is null"); //$NON-NLS-1$
//        }
//        return FunctorFromEditor.of(source, this);
//    }
}