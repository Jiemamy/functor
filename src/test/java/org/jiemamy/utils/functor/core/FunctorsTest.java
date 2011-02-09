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

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import org.jiemamy.utils.functor.Editor;
import org.jiemamy.utils.functor.Functor;
import org.jiemamy.utils.functor.Generator;

/**
 * Test for {@link Functors}.
 * 
 * @version $Date$
 * @author Suguru ARAKAWA
 */
public class FunctorsTest {
	
	/**
	 * Test method for {@link Functors#always(java.lang.Object)}.
	 */
	@Test
	public void testAlways() {
		Object value = new Object();
		Functor<Object, Object> always = Functors.always(value);
		assertThat(always.apply(value), sameInstance(value));
		assertThat(always.apply("value"), sameInstance(value));
		assertThat(always.apply(null), sameInstance(value));
	}
	
	/**
	 * Test method for {@link Functors#compose(Functor, Functor)}.
	 */
	@Test
	public void testCompose() {
		Functor<String, String> c = Functors.compose(new Add("g"), new Add("f"));
		assertThat(c.apply("a"), is("afg"));
	}
	
	/**
	 * Test method for {@link Functors#from(Editor, Generator)}.
	 */
	@Test
	public void testFromEditor() {
		NewStringBuilder gen = new NewStringBuilder("-");
		Editor<StringBuilder, Object> append = new Append();
		Functor<StringBuilder, Object> builder = Functors.from(append, gen);
		
		assertThat(builder.apply("A").toString(), is("-A"));
		assertThat(builder.apply("Hello").toString(), is("-Hello"));
	}
	
	/**
	 * Test method for {@link Functors#from(Generator)}.
	 */
	@Test
	public void testFromGenerator() {
		Generator<Integer> generator = Generators.constant(100);
		Functor<Integer, Object> functor = Functors.from(generator);
		assertThat(functor.apply(1), is(100));
		assertThat(functor.apply("hello"), is(100));
		assertThat(functor.apply(null), is(100));
	}
	
	/**
	 * Test method for {@link Functors#id()}.
	 */
	@Test
	public void testId() {
		Object value = new Object();
		assertThat(Functors.id().apply(value), sameInstance(value));
		assertThat(Functors.id().apply(null), is(nullValue()));
	}
	
	/**
	 * Test method for {@link Functors#transpose(Functor)}.
	 */
	@Test
	public void testTranspose() {
		Sub sub = new Sub();
		Functor<? extends Functor<Integer, Integer>, Integer> tsub = Functors.transpose(sub);
		assertThat(tsub.apply(2).apply(1), is(-1));
	}
	
	/**
	 * Test method for {@link Functors#transpose(Functor)}.
	 */
	@Test
	public void testTranspose_twice() {
		Sub sub = new Sub();
		Functor<? extends Functor<Integer, Integer>, Integer> tsub = Functors.transpose(sub);
		Functor<? extends Functor<Integer, Integer>, Integer> ttsub = Functors.transpose(tsub);
		assertThat(ttsub.apply(2).apply(1), is(1));
	}
}
