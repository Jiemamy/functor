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

import java.util.ArrayList;
import java.util.List;

import org.jiemamy.utils.functor.Editor;
import org.jiemamy.utils.functor.Functor;

/**
 * 標準的な操作器群。
 * 
 * @version $Date$
 * @author Suguru ARAKAWA
 */
public final class Editors {
	
	/**
	 * 指定の操作器の引数を、指定の関数で変換してから利用するような操作器を返す。
	 * 
	 * @param <D> 操作対象の値の型
	 * @param <S> 本来の操作引数の型
	 * @param <A> 実際の操作引数の型
	 * @param editor 実際に操作を行う操作器
	 * @param functor 操作器に渡す引数を変換する関数
	 * @return 生成した操作器
	 * @throws IllegalArgumentException 引数に{@code null}が含まれる場合
	 */
	public static <D, S, A>Editor<D, A> compose(Editor<? super D, ? super S> editor,
			Functor<? extends S, ? super A> functor) {
		if (editor == null) {
			throw new IllegalArgumentException("editor is null"); //$NON-NLS-1$
		}
		if (functor == null) {
			throw new IllegalArgumentException("functor is null"); //$NON-NLS-1$
		}
		return new Filtered<D, S, A>(editor, functor);
	}
	
	/**
	 * 複数の操作器を連続して適用する操作器を生成して返す。
	 * 
	 * <p>返される操作器のは、{@link Iterable#iterator() editor.iterator()}
	 * が返す操作器を順に適用する。</p>
	 * 
	 * @param <D> 操作対象の値の型
	 * @param <S> 操作引数の型
	 * @param editors 連続して適用する操作器の一覧
	 * @return 生成した操作器
	 * @throws IllegalArgumentException 引数に{@code null}が含まれる場合
	 */
	public static <D, S>Editor<D, S> compose(Iterable<? extends Editor<? super D, ? super S>> editors) {
		if (editors == null) {
			throw new IllegalArgumentException("editors is null"); //$NON-NLS-1$
		}
		return new Composite<D, S>(editors);
	}
	
	/**
	 * 何も行わない操作器を返す。
	 * 
	 * <p>操作器の型を指定する場合は、{@code Editors.<A, B>through()}
	 * のように明示的に型引数を指定してやる必要がある。
	 * または、代入時には型推論を利用して
	 * {@code Editor<A, B> editor = Editors.through()}
	 * のように書ける。</p>
	 * 
	 * @param <D> 操作対象の値の型
	 * @param <S> 操作引数の型
	 * @return 生成した操作器
	 */
	public static <D, S>Editor<D, S> through() {
		return new Through<D, S>();
	}
	
	private Editors() {
	}
	

	private static class Composite<D, S> extends AbstractEditor<D, S> {
		
		private List<Editor<? super D, ? super S>> editors;
		

		Composite(Iterable<? extends Editor<? super D, ? super S>> editors) {
			assert editors != null;
			this.editors = new ArrayList<Editor<? super D, ? super S>>();
			for (Editor<? super D, ? super S> each : editors) {
				this.editors.add(each);
			}
		}
		
		public D edit(D target, S argument) {
			if (target == null) {
				throw new IllegalArgumentException("target is null"); //$NON-NLS-1$
			}
			for (Editor<? super D, ? super S> each : editors) {
				each.edit(target, argument);
			}
			return target;
		}
	}
	
	private static class Filtered<D, I, S> extends AbstractEditor<D, S> {
		
		private Editor<? super D, ? super I> editor;
		
		private Functor<? extends I, ? super S> filter;
		

		Filtered(Editor<? super D, ? super I> editor, Functor<? extends I, ? super S> filter) {
			assert editor != null;
			assert filter != null;
			this.editor = editor;
			this.filter = filter;
		}
		
		public D edit(D target, S argument) {
			I intermediate = filter.apply(argument);
			editor.edit(target, intermediate);
			return target;
		}
	}
	
	private static class Through<D, S> extends AbstractEditor<D, S> {
		
		Through() {
			return;
		}
		
		public D edit(D target, S argument) {
			if (target == null) {
				throw new IllegalArgumentException("target is null"); //$NON-NLS-1$
			}
			return target;
		}
	}
}
