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
 * {@link Number}に関する関数群。
 * @version $Date$
 * @author Suguru ARAKAWA
 */
public class Numbers extends Objects {
	
	/**
	 * {@link Number#byteValue()}に対応する関数を返す。
	 * @return 対応する関数オブジェクト
	 */
	public static Functor<Byte, Number> byteValue() {
		return new AbstractFunctor<Byte, Number>() {
			
			public Byte apply(Number self) {
				return self.byteValue();
			}
		};
	}
	
	/**
	 * {@link Number#doubleValue()}に対応する関数を返す。
	 * @return 対応する関数オブジェクト
	 */
	public static Functor<Double, Number> doubleValue() {
		return new AbstractFunctor<Double, Number>() {
			
			public Double apply(Number self) {
				return self.doubleValue();
			}
		};
	}
	
	/**
	 * {@link Number#floatValue()}に対応する関数を返す。
	 * @return 対応する関数オブジェクト
	 */
	public static Functor<Float, Number> floatValue() {
		return new AbstractFunctor<Float, Number>() {
			
			public Float apply(Number self) {
				return self.floatValue();
			}
		};
	}
	
	/**
	 * {@link Number#intValue()}に対応する関数を返す。
	 * @return 対応する関数オブジェクト
	 */
	public static Functor<Integer, Number> intValue() {
		return new AbstractFunctor<Integer, Number>() {
			
			public Integer apply(Number self) {
				return self.intValue();
			}
		};
	}
	
	/**
	 * {@link Number#longValue()}に対応する関数を返す。
	 * @return 対応する関数オブジェクト
	 */
	public static Functor<Long, Number> longValue() {
		return new AbstractFunctor<Long, Number>() {
			
			public Long apply(Number self) {
				return self.longValue();
			}
		};
	}
	
	/**
	 * {@link Number#shortValue()}に対応する関数を返す。
	 * @return 対応する関数オブジェクト
	 */
	public static Functor<Short, Number> shortValue() {
		return new AbstractFunctor<Short, Number>() {
			
			public Short apply(Number self) {
				return self.shortValue();
			}
		};
	}
}
