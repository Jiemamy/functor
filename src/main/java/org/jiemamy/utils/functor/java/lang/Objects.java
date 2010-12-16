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

import java.util.ArrayList;
import java.util.List;

import org.jiemamy.utils.functor.Functor;
import org.jiemamy.utils.functor.core.AbstractFunctor;
import org.jiemamy.utils.functor.core.Functors;

/**
 * {@link java.lang.Object}に関する関数群。
 * 
 * <p>このクラス自体が{@link java.lang.Object}を継承しているため、いくつかのメンバ名は本来のものと異なる。</p>
 * 
 * @version $Date: 2009-11-22 20:30:22 +0900 (日, 22 11 2009) $
 * @author Suguru ARAKAWA
 */
public abstract class Objects { // CHECKSTYLE IGNORE THIS LINE

	/**
	 * {@code Object}型の配列を{@link List}に変換する関数を返す。
	 * 
	 * @param elementFunctor 配列の個々の要素に対して適用する関数
	 * @param <D> 生成されるリストの要素型
	 * @param <S> 引数に取る配列の要素型
	 * @return 配列の個々の要素に要素関数を適用し、{@link List}に変換する関数
	 */
	public static <D, S>Functor<List<D>, S[]> arrayEach(final Functor<? extends D, ? super S> elementFunctor) {
		return new AbstractFunctor<List<D>, S[]>() {
			
			public List<D> apply(S[] argument) {
				List<D> results = new ArrayList<D>(argument.length);
				for (S element : argument) {
					results.add(elementFunctor.apply(element));
				}
				return results;
			}
		};
	}
	
	/**
	 * {@code Object}型の配列を{@link List}に変換する関数を返す。
	 * 
	 * @param <T> 配列の要素型
	 * @return {@code Object}型の配列を{@link List}に変換する関数
	 */
	public static <T>Functor<List<T>, T[]> arrayToList() {
		return arrayEach(Functors.<T> id());
	}
	
	/**
	 * {@link Object#toString()}を返す。
	 * 
	 * @return {@link Object#toString()}
	 */
	public static Functor<String, Object> asString() {
		return new AbstractFunctor<String, Object>() {
			
			public String apply(Object argument) {
				return argument.toString();
			}
		};
	}
	
	/**
	 * {@link Object#equals(Object)}のうち、引数を適用した状態の関数を返す。
	 * 
	 * <p>つまり、{@code object.equals(argument)}は
	 * {@code Objects.equalTo(argument).apply(object)}のように書ける。</p>
	 * 
	 * @param argument 適用する引数
	 * @return 指定の引数をの同値比較を行う関数
	 */
	public static Functor<Boolean, Object> equalTo(final Object argument) {
		return new AbstractFunctor<Boolean, Object>() {
			
			public Boolean apply(Object object) {
				return object.equals(argument);
			}
		};
	}
	
	/**
	 * 引数に指定した値と同値のオブジェクトに対して{@code true}を返すような関数を返す。
	 * 
	 * <p>{@link #equalTo(Object)}との差異は、引数に{@code null}が渡された際の動作である。
	 * {@code is(argument).apply(null)}は{@code argument}が{@code null}
	 * の場合に{@code true}を返し、それ以外では{@code false}を返す。
	 * ({@link #equalTo(Object)}では{@link NullPointerException}ガスローされる。)</p>
	 * 
	 * @param argument 適用する引数
	 * @return 指定の引数をの同値比較を行う関数
	 * @see #equalTo(Object)
	 */
	public static Functor<Boolean, Object> is(final Object argument) {
		return new AbstractFunctor<Boolean, Object>() {
			
			public Boolean apply(Object object) {
				if (object == null) {
					return argument == null;
				}
				return object.equals(argument);
			}
		};
	}
	
	/**
	 * {@link Object#getClass()}を返す。
	 * 
	 * @return {@link Object#getClass()}
	 */
	public static Functor<Class<?>, Object> toClass() {
		return new AbstractFunctor<Class<?>, Object>() {
			
			public Class<?> apply(Object argument) {
				return argument.getClass();
			}
		};
	}
	
	/**
	 * {@link Object#toString()}を返す。
	 * 
	 * @return {@link Object#toString()}
	 */
	public static Functor<Integer, Object> toHashCode() {
		return new AbstractFunctor<Integer, Object>() {
			
			public Integer apply(Object argument) {
				return argument.hashCode();
			}
		};
	}
}
