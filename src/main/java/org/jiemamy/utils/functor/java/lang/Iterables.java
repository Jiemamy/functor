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

/**
 * {@link java.lang.Iterable}に関する関数群。
 * @version $Date: 2009-11-22 20:30:22 +0900 (日, 22 11 2009) $
 * @author Suguru ARAKAWA
 */
public abstract class Iterables extends Objects {
	
	/**
	 * 指定の{@link Iterable}から取得可能な個々の要素に対して関数を適用し、
	 * その結果を格納したリストを返す。
	 * <p>
	 * 返されるリストの順序は、{@code iterable.iterator()}が反復する要素の順序と同じになる。
	 * </p>
	 * @param <D> 変換後の要素の型
	 * @param <S> 変換前の要素の型
	 * @param elementFunctor 個々の要素を変換する関数
	 * @return 対応する関数
	 */
	public static <D, S>Functor<List<D>, Iterable<? extends S>> each(
			final Functor<? extends D, ? super S> elementFunctor) {
		return new AbstractFunctor<List<D>, Iterable<? extends S>>() {
			
			public List<D> apply(Iterable<? extends S> self) {
				List<D> results = new ArrayList<D>();
				for (S element : self) {
					results.add(elementFunctor.apply(element));
				}
				return results;
			}
		};
	}
	
	/**
	 * 指定の{@link Iterable}から取得可能な値を、順に2階の関数に次々と適用して畳み込む関数を返す。
	 * <p>
	 * 例えば、{@code add.apply(a).apply(b) = a + b}となるような{@code add}が存在する場合、
	 * {@code fold(0, add).apply([a, b, c, d])}は、{@code a, b, c, d}の合計を返す。
	 * これは、次のように計算される。
	 * </p>
	 * <pre><code>
	 * x[0] = 0
	 * x[1] = x[0] + a
	 * x[2] = x[1] + b
	 * x[3] = x[2] + c
	 * x[4] = x[3] + d
	 * </code></pre>
	 * <p>
	 * つまり、返される関数に渡される引数を{@code list[0..]}であらわすとすると、
	 * 返される関数は次の一般式で表せる。
	 * </p>
	 * <pre><code>
	 * fold(leftMost, folder).apply(list) = x[k]
	 *   where x[0] = leftMost,
	 *         x[k] = folder.apply(x[k - 1]).apply(list[k - 1]) (k &lt; 0)
	 * </code></pre>
	 * <p>
	 * {@link Iterable}に要素がひとつも含まれない場合、この関数は常に
	 * {@code leftMost}で指定した値を返す。
	 * </p>
	 * @param <T> 対象の要素の型
	 * @param leftMost 畳み込みの初期値
	 * @param folder 畳み込み関数
	 * @return 対応する関数
	 */
	public static <T>Functor<T, Iterable<? extends T>> fold(final T leftMost,
			final Functor<? extends Functor<? extends T, ? super T>, ? super T> folder) {
		return new AbstractFunctor<T, Iterable<? extends T>>() {
			
			public T apply(Iterable<? extends T> self) {
				T left = leftMost;
				for (T right : self) {
					left = folder.apply(left).apply(right);
				}
				return left;
			}
		};
	}
	
	/**
	 * 指定の{@link Iterable}から取得可能な個々の要素に対して関数を適用し、
	 * それに{@code filter.apply()}を適用した結果が{@code Boolean.TRUE}
	 * となる要素だけを返すような関数を返す。
	 * <p>
	 * 返されるリストの順序は、{@code iterable.iterator()}が反復する要素の順序と同じになる。
	 * </p>
	 * @param <T> 要素の型
	 * @param filter それぞれの要素のフィルタ
	 * @return 対応する関数
	 */
	public static <T>Functor<List<T>, Iterable<? extends T>> only(final Functor<Boolean, ? super T> filter) {
		return new AbstractFunctor<List<T>, Iterable<? extends T>>() {
			
			public List<T> apply(Iterable<? extends T> self) {
				List<T> results = new ArrayList<T>();
				for (T element : self) {
					if (filter.apply(element) == Boolean.TRUE) {
						results.add(element);
					}
				}
				return results;
			}
		};
	}
}
