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

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.jiemamy.utils.functor.Functor;
import org.jiemamy.utils.functor.core.AbstractFunctor;
import org.jiemamy.utils.functor.java.lang.Objects;

/**
 * {@link java.util.Map}に関する関数群。
 * 
 * @version $Date: 2009-11-18 16:09:26 +0900 (水, 18 11 2009) $
 * @author Suguru ARAKAWA
 */
public class Maps extends Objects {
	
	/**
	 * {@link Map#entrySet()}に対応する関数を返す。
	 * 
	 * @param <K> キーの型
	 * @param <V> 値の型
	 * @return 対応する関数
	 */
	public static <K, V>Functor<Set<Map.Entry<K, V>>, Map<K, V>> entrySet() {
		return new AbstractFunctor<Set<Map.Entry<K, V>>, Map<K, V>>() {
			
			public Set<Map.Entry<K, V>> apply(Map<K, V> self) {
				return self.entrySet();
			}
		};
	}
	
	/**
	 * {@link Map#get(Object)}に対応する関数を返す。
	 * 
	 * <p>返される関数は、指定された引数が適用済みとなっている。</p>
	 * 
	 * @param <V> 値の型
	 * @param key 返される関数に適用する引数
	 * @return 対応する関数
	 */
	public static <V>Functor<V, Map<?, V>> get(final Object key) {
		return new AbstractFunctor<V, Map<?, V>>() {
			
			public V apply(Map<?, V> self) {
				return self.get(key);
			}
		};
	}
	
	/**
	 * {@link Map#keySet()}に対応する関数を返す。
	 * 
	 * @param <K> キーの型
	 * @return 対応する関数
	 */
	public static <K>Functor<Set<K>, Map<K, ?>> keySet() {
		return new AbstractFunctor<Set<K>, Map<K, ?>>() {
			
			public Set<K> apply(Map<K, ?> self) {
				return self.keySet();
			}
		};
	}
	
	/**
	 * 指定のマップに対して、{@link Map#get(Object)}を利用した関数を返す。
	 * 
	 * @param <V> 対象の値の型
	 * @param map 対象のマップ
	 * @return {@link Map#get(Object)}を利用した関数
	 */
	public static <V>Functor<V, Object> of(final Map<?, V> map) {
		return new AbstractFunctor<V, Object>() {
			
			public V apply(Object key) {
				return map.get(key);
			}
		};
	}
	
	/**
	 * 値を{@code Map<?, ?>}型の値に変換する関数を返す。
	 * 
	 * @return {@code Map<?, ?>}型に変換する関数
	 */
	public static Functor<Map<?, ?>, Object> value() {
		return new AbstractFunctor<Map<?, ?>, Object>() {
			
			public Map<?, ?> apply(Object source) {
				return (Map<?, ?>) source;
			}
		};
	}
	
	/**
	 * {@link Map#values()}に対応する関数を返す。
	 * 
	 * @param <V> 値の型
	 * @return 対応する関数
	 */
	public static <V>Functor<Collection<V>, Map<?, V>> values() {
		return new AbstractFunctor<Collection<V>, Map<?, V>>() {
			
			public Collection<V> apply(Map<?, V> self) {
				return self.values();
			}
		};
	}
}
