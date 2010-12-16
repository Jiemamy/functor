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
 * 標準的な生成器群。
 * 
 * @version $Date$
 * @author Suguru ARAKAWA
 */
public final class Generators {
	
	/**
	 * 指定の生成器の結果に対し、さらに指定の関数を適用する合成された生成器を返す。
	 * 
	 * @param <T> 最終的な値の型
	 * @param <I> 最初に生成器が生成する値の型
	 * @param generator 最初に値を生成する生成器
	 * @param functor {@code source}が生成した値をさらに変換する関数
	 * @return 合成された生成器
	 * @throws IllegalArgumentException 引数に{@code null}が含まれる場合
	 */
	public static <T, I>Generator<T> compose(Functor<? extends T, ? super I> functor, Generator<? extends I> generator) {
		if (functor == null) {
			throw new IllegalArgumentException("functor is null"); //$NON-NLS-1$
		}
		if (generator == null) {
			throw new IllegalArgumentException("generator is null"); //$NON-NLS-1$
		}
		return new Filtered<T, I>(generator, functor);
	}
	
	/**
	 * 指定の値を常に生成する、{@link Generator}のインスタンスを返す。
	 * 
	 * <p>引数に{@code null}を指定した場合、メソッドの型推論が無効化される場合がある。
	 * そのような場合、
	 * {@code always((String) null)}のように型を明示的に指定してやるとよい。</p>
	 * 
	 * @param <T> 生成する定数の型
	 * @param value 生成する定数
	 * @return 生成したインスタンス
	 */
	public static <T>Generator<T> constant(final T value) {
		return new AbstractGenerator<T>() {
			
			public T generate() {
				return value;
			}
		};
	}
	
	private Generators() {
	}
	

	private static class Filtered<T, I> extends AbstractGenerator<T> {
		
		private Generator<? extends I> source;
		
		private Functor<? extends T, ? super I> functor;
		

		/**
		 * インスタンスを生成する。
		 * 
		 * @param source 最初に値を生成する生成器
		 * @param functor {@code source}が生成した値をさらに変換する関数
		 * @throws IllegalArgumentException 引数に{@code null}が含まれる場合
		 */
		public Filtered(Generator<? extends I> source, Functor<? extends T, ? super I> functor) {
			assert source != null;
			assert functor != null;
			this.source = source;
			this.functor = functor;
		}
		
		public T generate() {
			I intermediate = source.generate();
			T result = functor.apply(intermediate);
			return result;
		}
	}
}
