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

import org.jiemamy.utils.functor.ApplyException;
import org.jiemamy.utils.functor.Functor;

/**
 * 任意の値を別の型に変換する関数。
 * 
 * <p>このクラスのオブジェクトは、{@link Functor#andThen(Functor)}の引数に渡し、
 * {@link Functor}オブジェクトの結果型を変更するために利用することを想定している。
 * 型安全でない方法で{@code Functor}が作成された場合、
 * その型は基本的に{@code Functor<Object, ...>}というように結果としてObject型の値を返すものとなる。
 * そのままでは利用しづらいため、
 * {@code functor.andThen(Cast.to(String.class))}といったように
 * 適切な戻り値型に変更してやってから利用することになる。</p>
 * 
 * @version $Date$
 * @author Suguru ARAKAWA
 * @param <T> 変換後の型
 * @see Functor#andThen(Functor)
 */
public abstract class Cast<T> extends AbstractFunctor<T, Object> {
	
	/**
	 * 受け取った値を指定の型に変換して返すような関数を生成して返す。
	 * 
	 * <p>返される関数が、値の型を変換するのに失敗した場合、{@link ApplyException}がスローされる。</p>
	 * 
	 * @param <R> 変換結果の型
	 * @param resultType 変換結果の型
	 * @return 生成した関数
	 * @throws IllegalArgumentException 引数に{@code null}が含まれる場合
	 */
	public static <R>Cast<R> to(Class<R> resultType) {
		return new Checked<R>(resultType);
	}
	
	/**
	 * 受け取った値を指定の型に変換して返すような関数を生成して返す。
	 * 
	 * <p>このメソッドは、{@code Cast.<String>unchecked()}のように
	 * 呼び出し時に型引数を指定するのがよい。
	 * 返される関数が値の型を変換するのに失敗した場合、
	 * その関数の中ではエラーが発生しない場合がある。
	 * そのような場合、それ以降に指定の値を利用しようとした際に
	 * {@link ClassCastException}などの例外がスローされる。
	 * つまり、この変換機を利用することでヒープ汚染が発生するため、
	 * 利用には注意が必要である。</p>
	 * 
	 * @param <R> 変換結果の型
	 * @return 生成した関数
	 * @throws IllegalArgumentException 引数に{@code null}が含まれる場合
	 */
	public static <R>Cast<R> unchecked() {
		return new Unchecked<R>();
	}
	

	private static class Checked<T> extends Cast<T> {
		
		private Class<? extends T> checker;
		

		Checked(Class<? extends T> checker) {
			assert checker != null;
			this.checker = checker;
		}
		
		public T apply(Object argument) {
			if (argument == null) {
				return null;
			}
			
			// 正常系の高速化のため、checker.isInstance()による検査を省略。
			try {
				T result = checker.cast(argument);
				return result;
			} catch (ClassCastException e) {
				throw new ApplyException(e);
			}
		}
	}
	
	private static class Unchecked<T> extends Cast<T> {
		
		Unchecked() {
			return;
		}
		
		public T apply(Object argument) {
			@SuppressWarnings("unchecked")
			T result = (T) argument;
			return result;
		}
	}
}
