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

import org.jiemamy.utils.functor.Functor;

/**
 * {@link Functor}の骨格実装。
 * 
 * @version $Date$
 * @author Suguru ARAKAWA
 * @param <D> 変換後の値の型
 * @param <S> 変換前の値の型
 */
public abstract class AbstractFunctor<D, S> implements Functor<D, S> {
	
	public <R>Functor<R, S> andThen(Functor<? extends R, ? super D> chain) {
		if (chain == null) {
			throw new IllegalArgumentException("chain is null"); //$NON-NLS-1$
		}
		return Functors.compose(chain, this);
	}
}
