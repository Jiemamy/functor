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
package org.jiemamy.utils.functor;

import org.jiemamy.utils.functor.core.Cast;

/**
 * 値を別の値に変換する関数。
 * 
 * @version $Date: 2009-11-20 18:19:07 +0900 (金, 20 11 2009) $
 * @author Suguru ARAKAWA
 * @param <D> 変換後の値の型
 * @param <S> 変換前の値の型
 */
public interface Functor<D, S> {
	
	/**
	 * この関数の結果をさらに指定の関数で変換するような、合成された関数を返す。
	 * 
	 * <p>合成する関数が受け取る値の型は、この関数が出力する値の型に対し
	 * スーパータイプ関係が成り立たなければならない。</p>
	 * 
	 * <p>例えば、この関数が{@code Functor<String, ...>}である場合、
	 * 引数に取れる関数は
	 * {@code Functor<..., String>},
	 * {@code Functor<..., CharSequence>},
	 * {@code Functor<..., Object>}
	 * など、その変換前の値の型が{@code String}のスーパータイプであるものに限られる。
	 * なお、{@code Functor<..., Object>}のように変換前の値の型が
	 * {@code Object}である場合には、どのような関数にも適用できる。</p>
	 * 
	 * <p>上記の制約を緩和するには、{@link Cast}というクラスを利用するのがよい。
	 * {@link Cast#to(Class)}は関数が出力する値を型安全に変換できる。
	 * たとえば、{@code Functor<Object, String>}という関数{@code f}に対し、
	 * {@code f.andThen(Cast.to(Integer.class))}とした場合、
	 * 合成される関数は{@code Functor<Integer, String>}となる。
	 * この結果に対し、さらに{@code andThen()}メソッドを実行してやれば
	 * {@code Integer}の値を受け取ることができる関数と合成できる。</p>
	 * 
	 * <p>{@code Cast}クラスにはそのほかに{@link Cast#unchecked()}
	 * というメソッドも用意されている。
	 * このメソッドは{@code Cast.to()}同様に関数の結果型を変更するためのメソッドで、
	 * {@code Cast.<String>unchecked()}などのように利用する。
	 * {@code Cast.to()}との最大の違いは、{@code Cast.unchecked()}
	 * は変換時に型検査を行わない点である。
	 * 型安全でなくなるという問題はあるが、{@code Cast.to()}
	 * は変換先の型をクラスリテラルで指定しなければならず、
	 * {@code List<String>}などのパラメータ化型は指定できないなどの問題がある。
	 * その場合でも、{@code Cast.<List<String>>unchecked()}のような
	 * 指定を行うことで、強制的に関数の結果型を変更することができる。</p>
	 * 
	 * @param <R> 合成された関数の結果の型
	 * @param chain 合成する関数
	 * @return 合成結果
	 * @throws IllegalArgumentException 引数に{@code null}が含まれる場合
	 */
	<R>Functor<R, S> andThen(Functor<? extends R, ? super D> chain);
	
	/**
	 * この変換機を利用して、指定の値を変換する。
	 * 
	 * @param argument 変換する値
	 * @return 変換後の値
	 * @throws ApplyException 値の変換に失敗した場合
	 * @throws RuntimeException 値の変換中に、その他の実行時例外が発生した場合
	 */
	D apply(S argument);
}
