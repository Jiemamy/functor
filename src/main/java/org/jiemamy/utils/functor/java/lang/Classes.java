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

import org.jiemamy.utils.functor.ApplyException;
import org.jiemamy.utils.functor.Functor;
import org.jiemamy.utils.functor.core.AbstractFunctor;

/**
 * {@link java.lang.Class}に関する関数群。
 * @version $Date$
 * @author Suguru ARAKAWA
 */
public abstract class Classes extends Objects {
	
	/**
	 * {@link Class#asSubclass(Class)}に対応する関数を返す。
	 * @param <T> 引数に指定するクラスの型
	 * @param clazz 返される関数に適用する引数
	 * @return 対応する関数
	 */
	public static <T>Functor<Class<? extends T>, Class<?>> asSubclass(final Class<T> clazz) {
		return new AbstractFunctor<Class<? extends T>, Class<?>>() {
			
			public Class<? extends T> apply(Class<?> self) {
				return self.asSubclass(clazz);
			}
		};
	}
	
	/**
	 * {@link Class#getName()}に対応する関数を返す。
	 * @return {@link Class#getName()}に対応する関数
	 */
	public static Functor<String, Class<?>> name() {
		return new AbstractFunctor<String, Class<?>>() {
			
			public String apply(Class<?> self) {
				return self.getName();
			}
		};
	}
	
	/**
	 * {@link Class#newInstance()}に対応する関数を返す。
	 * @param <T> 対象クラスの型
	 * @return 対応する関数
	 */
	public static <T>Functor<T, Class<T>> newInstance() {
		return new AbstractFunctor<T, Class<T>>() {
			
			public T apply(Class<T> self) {
				try {
					return self.newInstance();
				} catch (InstantiationException e) {
					throw new ApplyException(e);
				} catch (IllegalAccessException e) {
					throw new ApplyException(e);
				}
			}
		};
	}
	
	/**
	 * 値を{@code Class<?>}型の値に変換する関数を返す。
	 * <p>
	 * 型引数を変更する場合には、{@link #asSubclass(Class)}と組み合わせて
	 * {@code Classes.value().andThen(Classes.asSubclass(Runnable.class))}
	 * のように書く必要がある。
	 * @return {@code List<?>}型に変換する関数
	 */
	public static Functor<Class<?>, Object> value() {
		return new AbstractFunctor<Class<?>, Object>() {
			
			public Class<?> apply(Object source) {
				return (Class<?>) source;
			}
		};
	}
}
