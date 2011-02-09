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
 * 値を操作する操作器。
 * 
 * @version $Date$
 * @author Suguru ARAKAWA
 * @param <D> 操作対象のオブジェクトの型
 * @param <S> 操作引数の型
 */
public interface Editor<D, S> {
	
	/**
	 * この操作器を利用し、指定の{@code target}を操作する。
	 * 
	 * @param target 操作対象のオブジェクト
	 * @param argument 操作引数
	 * @return {@code target}
	 * @throws EditException 操作に失敗した場合
	 * @throws IllegalArgumentException 引数{@code target}に{@code null}が含まれる場合
	 */
	D edit(D target, S argument);
}
