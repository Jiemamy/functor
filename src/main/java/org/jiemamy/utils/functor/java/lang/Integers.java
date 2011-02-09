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
package org.jiemamy.utils.functor.java.lang;

import org.jiemamy.utils.functor.Functor;
import org.jiemamy.utils.functor.core.AbstractFunctor;

/**
 * {@code int}型、{@code Integer}型に関する関数群。
 * @version $Date$
 * @author Suguru ARAKAWA
 */
public abstract class Integers extends Numbers {
	
	/**
	 * {@link Integer#parseInt(String)}に対応する関数を返す。
	 * @return 対応する関数
	 */
	public static Functor<Integer, String> parse() {
		return new AbstractFunctor<Integer, String>() {
			
			public Integer apply(String string) {
				return Integer.parseInt(string);
			}
		};
	}
}
