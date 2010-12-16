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
import org.jiemamy.utils.functor.core.Cast;

/**
 * プログラミング言語Javaの組み込み機能に関する関数群。
 * 
 * @version $Date: 2009-11-18 16:09:26 +0900 (水, 18 11 2009) $
 * @author Suguru ARAKAWA
 */
public final class Java {
	
	/**
	 * {@link Cast#to(Class) Cast.to(aClass)}のエイリアス。
	 * 
	 * @param <T> 変換結果の型
	 * @param aClass 変換に利用するクラス
	 * @return 変換を行う関数
	 */
	public static <T>Functor<T, Object> cast(final Class<T> aClass) {
		return Cast.to(aClass);
	}
	
	/**
	 * {@code instanceof}演算子を表す関数に、型の指定だけ行ったものを返す。
	 * 
	 * @param aClass 指定する型
	 * @return {@code instanceof}演算子を表す関数に、型の指定だけ行ったもの
	 */
	public static Functor<Boolean, Object> instanceOf(final Class<?> aClass) {
		return new AbstractFunctor<Boolean, Object>() {
			
			public Boolean apply(Object argument) {
				return aClass.isInstance(argument);
			}
		};
	}
	
	/**
	 * 指定の述語の否定を行うような述語を返す。
	 * 
	 * @param <T> 述語の引数の型
	 * @param predicate 対象の述語
	 * @return 対象の述語の否定を行う述語
	 */
	public static <T>Functor<Boolean, T> not(final Functor<Boolean, T> predicate) {
		return new AbstractFunctor<Boolean, T>() {
			
			public Boolean apply(T argument) {
				return predicate.apply(argument) == false;
			}
		};
	}
	
	private Java() {
	}
}
