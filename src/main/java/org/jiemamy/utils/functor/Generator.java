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

/**
 * 値を生成する生成器。
 * 
 * @version $Date: 2009-11-18 16:09:26 +0900 (水, 18 11 2009) $
 * @author Suguru ARAKAWA
 * @param <T> 生成する値の型
 */
public interface Generator<T> {
	
	/**
	 * この生成器の結果をさらに指定の関数で変換するような、合成された生成器を返す。
	 * 
	 * <p>合成する関数が受け取る値の型は、この関数が出力する値の型に対し
	 * スーパータイプ関係が成り立たなければならない。</p>
	 * 
	 * @param <R> 合成された式の結果の型
	 * @param chain 合成する関数
	 * @return 合成結果
	 * @throws IllegalArgumentException 引数に{@code null}が含まれる場合
	 */
	<R>Generator<R> andThen(Functor<R, ? super T> chain);
	
	/**
	 * この生成器を利用して生成した値を返す。
	 * 
	 * @return 生成した値
	 * @throws ApplyException 値の生成に失敗した場合
	 */
	T generate();
}
