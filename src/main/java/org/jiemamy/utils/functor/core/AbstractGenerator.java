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
import org.jiemamy.utils.functor.Generator;

/**
 * {@link Generator}の骨格実装。
 * 
 * @version $Date: 2009-11-20 13:52:34 +0900 (金, 20 11 2009) $
 * @author Suguru ARAKAWA
 * @param <T> 生成する値の型
 */
public abstract class AbstractGenerator<T> implements Generator<T> {
	
	public <R>Generator<R> andThen(Functor<R, ? super T> chain) {
		if (chain == null) {
			throw new IllegalArgumentException("chain is null"); //$NON-NLS-1$
		}
		return Generators.compose(chain, this);
	}
}
