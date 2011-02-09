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
package org.jiemamy.utils.functor.java.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.jiemamy.utils.functor.Functor;
import org.jiemamy.utils.functor.core.AbstractFunctor;
import org.jiemamy.utils.functor.java.lang.Iterables;

/**
 * {@link java.util.List}に関する関数群。
 * 
 * @version $Date$
 * @author Suguru ARAKAWA
 */
public abstract class Lists extends Iterables {
	
	/**
	 * {@link ArrayList#ArrayList(java.util.Collection)}に対応する関数を返す。
	 * 
	 * @param <T> 要素の型
	 * @return 対応する関数
	 */
	public static <T>Functor<ArrayList<T>, Collection<? extends T>> copy() {
		return new AbstractFunctor<ArrayList<T>, Collection<? extends T>>() {
			
			public ArrayList<T> apply(Collection<? extends T> self) {
				return new ArrayList<T>(self);
			}
		};
	}
	
	/**
	 * {@link List#get(int)}に対応する関数を返す。
	 * 
	 * <p>返される関数は、指定された引数が適用済みとなっている。</p>
	 * 
	 * @param <T> リストの要素型
	 * @param index 返される関数に適用する引数
	 * @return 対応する関数
	 */
	public static <T>Functor<T, List<? extends T>> get(final int index) {
		return new AbstractFunctor<T, List<? extends T>>() {
			
			public T apply(List<? extends T> self) {
				return self.get(index);
			}
		};
	}
	
	/**
	 * {@link Collections#sort(List)}に対応する関数を返す。
	 * 
	 * @param <T> 要素の型
	 * @return 対応する関数
	 */
	public static <T extends Comparable<? super T>>Functor<List<T>, List<? extends T>> sort() {
		return new AbstractFunctor<List<T>, List<? extends T>>() {
			
			public List<T> apply(List<? extends T> self) {
				ArrayList<T> copy = new ArrayList<T>(self);
				Collections.sort(copy);
				return copy;
			}
		};
	}
	
	/**
	 * {@link Collections#sort(List, Comparator)}に対応する関数を返す。
	 * 
	 * @param <T> 要素の型
	 * @param comparator 返される関数に適用する引数(第2引数)
	 * @return 対応する関数
	 */
	public static <T>Functor<List<T>, List<? extends T>> sort(final Comparator<? super T> comparator) {
		return new AbstractFunctor<List<T>, List<? extends T>>() {
			
			public List<T> apply(List<? extends T> self) {
				ArrayList<T> copy = new ArrayList<T>(self);
				Collections.sort(copy, comparator);
				return copy;
			}
		};
	}
	
	/**
	 * {@link Collections#unmodifiableList(List)}に対応する関数を返す。
	 * 
	 * @param <T> 要素の型
	 * @return 対応する関数
	 */
	public static <T>Functor<List<T>, List<? extends T>> unmodifiable() {
		return new AbstractFunctor<List<T>, List<? extends T>>() {
			
			public List<T> apply(List<? extends T> self) {
				return Collections.unmodifiableList(self);
			}
		};
	}
	
	/**
	 * 値を{@code List<?>}型の値に変換する関数を返す。
	 * 
	 * <p>要素型まで指定する場合には、{@link Iterables#each(Functor)}と組み合わせて
	 * {@code Lists.value().andThen(Lists.each(Cast.to(String.class)))}
	 * のように書く必要がある。</p>
	 * 
	 * @return {@code List<?>}型に変換する関数
	 */
	public static Functor<List<?>, Object> value() {
		return new AbstractFunctor<List<?>, Object>() {
			
			public List<?> apply(Object source) {
				return (List<?>) source;
			}
		};
	}
}
