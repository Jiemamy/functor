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

import java.util.Iterator;
import java.util.List;

import org.jiemamy.utils.functor.Functor;
import org.jiemamy.utils.functor.core.AbstractFunctor;

/**
 * {@link java.lang.String}に関する関数群。
 * @version $Date: 2009-11-18 16:09:26 +0900 (水, 18 11 2009) $
 * @author Suguru ARAKAWA
 */
public abstract class Strings extends Objects {
	
	/**
	 * {@link String#charAt(int)}に対応する関数を返す。
	 * @param index 与えられた文字列から添え字の位置を算出する関数
	 * @return {@code x -> x.charAt(index(x))}
	 */
	public static Functor<Character, String> charAt(final Functor<Integer, String> index) {
		return new AbstractFunctor<Character, String>() {
			
			public Character apply(String self) {
				return self.charAt(index.apply(self));
			}
		};
	}
	
	/**
	 * {@link String#charAt(int)}に対応する関数を返す。
	 * @param index 添え字の位置
	 * @return {@code x -> x.charAt(index)}
	 */
	public static Functor<Character, String> charAt(final int index) {
		return new AbstractFunctor<Character, String>() {
			
			public Character apply(String self) {
				return self.charAt(index);
			}
		};
	}
	
	/**
	 * 引数の文字列リストを、指定の区切文字列を利用して連接した文字列を返すような関数を返す。
	 * @param delimiter 区切り文字列
	 * @return リストの文字列を順に、区切り文字列で連接した文字列を返すような関数
	 */
	public static Functor<String, List<String>> join(final String delimiter) {
		return new AbstractFunctor<String, List<String>>() {
			
			public String apply(List<String> list) {
				Iterator<String> iter = list.iterator();
				if (iter.hasNext() == false) {
					return ""; //$NON-NLS-1$
				}
				StringBuilder buf = new StringBuilder();
				buf.append(iter.next());
				while (iter.hasNext()) {
					buf.append(delimiter);
					buf.append(iter.next());
				}
				return buf.toString();
			}
		};
	}
	
	/**
	 * {@link String#length()}に対応する関数。
	 * @return 対応する関数
	 */
	public static Functor<Integer, String> length() {
		return new AbstractFunctor<Integer, String>() {
			
			public Integer apply(String self) {
				return self.length();
			}
		};
	}
}
