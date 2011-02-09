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

import org.jiemamy.utils.functor.Editor;
import org.jiemamy.utils.functor.Functor;
import org.jiemamy.utils.functor.Generator;

/**
 * 標準的な関数群。
 * 
 * @version $Date$
 * @author Suguru ARAKAWA
 */
public final class Functors {
	
	/**
	 * 指定の値を常に返す、{@link Functor}のインスタンスを返す。
	 * 
	 * <p>引数に{@code null}を指定した場合、メソッドの型推論が無効化される場合がある。
	 * そのような場合、
	 * {@code always((String) null)}のように型を明示的に指定してやるとよい。</p>
	 * 
	 * @param <T> 生成する定数の型
	 * @param value 生成する定数
	 * @return 生成したインスタンス
	 */
	public static <T>Functor<T, Object> always(final T value) {
		return new AbstractFunctor<T, Object>() {
			
			public T apply(Object argument) {
				return value;
			}
		};
	}
	
	/**
	 * 2つの{@link Functor}を合成した関数を生成して返す。
	 * 
	 * <p>合成した関数{@code composite}は、{@link Functor#apply(Object)}が実行された際に
	 * まず{@code f.apply()}を実行し、その結果にさらに{@code g.apply()}を
	 * 適用した結果を返す。
	 * つまり、
	 * {@code composite.apply(x) == g.apply(f.apply(x))}
	 * となる。</p>
	 * 
	 * @param <D> 最終的な変換後の値の型
	 * @param <I> 最初の変換後の値の型
	 * @param <S> 変換前の値の型
	 * @param g {@code f}の結果をさらに変換する関数
	 * @param f 最初に変換を行う関数
	 * @return 合成した関数
	 * @throws IllegalArgumentException 引数に{@code null}が含まれる場合
	 */
	public static <D, I, S>Functor<D, S> compose(Functor<? extends D, ? super I> g, Functor<? extends I, ? super S> f) {
		if (f == null) {
			throw new IllegalArgumentException("f is null"); //$NON-NLS-1$
		}
		if (g == null) {
			throw new IllegalArgumentException("g is null"); //$NON-NLS-1$
		}
		return new Composite<D, I, S>(f, g);
	}
	
	/**
	 * {@link Generator}と{@link Editor}を組み合わせて
	 * {@link Functor}のように振舞うインスタンスを生成する。
	 * 
	 * @param <D> 変換後の値({@code Editor}の操作対象)の型
	 * @param <S> 変換前の値({@code Editor}の操作引数)の型
	 * @param generator 結果となる値を生成する生成器
	 * @param editor {@code generator}によって生成された値を操作する操作器
	 * @return 生成した変換器
	 * @throws IllegalArgumentException 引数に{@code null}が含まれる場合
	 */
	public static <D, S>Functor<D, S> from(Editor<? super D, ? super S> editor, Generator<? extends D> generator) {
		if (editor == null) {
			throw new IllegalArgumentException("editor is null"); //$NON-NLS-1$
		}
		if (generator == null) {
			throw new IllegalArgumentException("generator is null"); //$NON-NLS-1$
		}
		return new FromEditor<D, S>(editor, generator);
	}
	
	/**
	 * 指定の {@link Generator}を元に {@link Functor}を構築して返す。
	 * <p>
	 * 返される関数の{@link Functor#apply(Object)}の引数は無視され、ラップする{@link Generator}が生成する値を返す。
	 * </p>
	 * @param <T> 生成される値の型
	 * @param generator 元となる生成器
	 * @return 生成したインスタンス
	 * @throws IllegalArgumentException 引数に{@code null}が含まれる場合
	 */
	public static <T>Functor<T, Object> from(Generator<? extends T> generator) {
		if (generator == null) {
			throw new IllegalArgumentException("generator is null"); //$NON-NLS-1$
		}
		return new FromGenerator<T>(generator);
	}
	
	/**
	 * 引数をそのまま返すような、恒等関数を返す。
	 * 
	 * <p>恒等関数の型を指定する場合、{@code Functors.<String>id()}のように記述する。</p>
	 * 
	 * @param <T> 恒等関数が取り扱う値の型
	 * @return 恒等関数
	 */
	public static <T>Functor<T, T> id() {
		return new AbstractFunctor<T, T>() {
			
			public T apply(T argument) {
				return argument;
			}
		};
	}
	
	/**
	 * 高階化された関数の、第1引数と第2引数の順序を入れ替えた関数を返す。
	 * 
	 * <p>引数に渡された関数が{@code f.apply(x).apply(y)}のように適用される場合、
	 * {@code t(f).apply(y).apply(x)}のように利用できることになる。</p>
	 * 
	 * @param <D> 高階化された関数の、最終的な結果の型
	 * @param <I> 高階化された関数の、本来の第2引数の型
	 * @param <S> 高階化された関数の、本来の第1引数の型
	 * @param functor 対象の高階化された関数
	 * @return 対象の引数の順序を入れ替えた関数
	 * @throws IllegalArgumentException 引数に{@code null}が含まれる場合
	 */
	@SuppressWarnings("unchecked")
	// 現行のコンパイラだと正しく解釈しない
	public static <D, I, S>Functor<Functor<D, S>, I> transpose(Functor<? extends Functor<D, I>, S> functor) {
		if (functor == null) {
			throw new IllegalArgumentException("functor is null"); //$NON-NLS-1$
		}
		if (functor instanceof Transpose) {
			@SuppressWarnings("rawtypes")
			Transpose transposed = (Transpose) functor;
			return transposed.original;
		}
		return new Transpose<D, I, S>(functor);
	}
	
	private Functors() {
	}
	

	private static class Composite<D, I, S> extends AbstractFunctor<D, S> {
		
		private Functor<? extends I, ? super S> former;
		
		private Functor<? extends D, ? super I> latter;
		

		Composite(Functor<? extends I, ? super S> former, Functor<? extends D, ? super I> latter) {
			assert former != null;
			assert latter != null;
			this.former = former;
			this.latter = latter;
		}
		
		public D apply(S argument) {
			I intermediate = former.apply(argument);
			D result = latter.apply(intermediate);
			return result;
		}
	}
	
	private static class FromEditor<D, S> extends AbstractFunctor<D, S> {
		
		private Generator<? extends D> generator;
		
		private Editor<? super D, ? super S> editor;
		

		FromEditor(Editor<? super D, ? super S> editor, Generator<? extends D> generator) {
			assert generator != null;
			assert editor != null;
			this.editor = editor;
			this.generator = generator;
		}
		
		public D apply(S argument) {
			D object = generator.generate();
			editor.edit(object, argument);
			return object;
		}
	}
	
	private static class FromGenerator<T> extends AbstractFunctor<T, Object> {
		
		private Generator<? extends T> generator;
		

		FromGenerator(Generator<? extends T> generator) {
			assert generator != null;
			this.generator = generator;
		}
		
		public T apply(Object argument) {
			return generator.generate();
		}
	}
	
	private static class Transpose<D, A, B> extends AbstractFunctor<Functor<D, B>, A> {
		
		final Functor<? extends Functor<D, A>, B> original;
		

		Transpose(Functor<? extends Functor<D, A>, B> original) {
			assert original != null;
			this.original = original;
		}
		
		public Functor<D, B> apply(final A a) {
			final Functor<? extends Functor<D, A>, B> f = original;
			return new AbstractFunctor<D, B>() {
				
				public D apply(final B b) {
					return f.apply(b).apply(a);
				}
			};
		}
	}
}
