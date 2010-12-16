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

import org.jiemamy.utils.functor.Generator;

/**
 * Test for {@link Generators}.
 * 
 * @version $Date$
 * @author Suguru ARAKAWA
 */
public class GeneratorsTest {
	
	/**
	 * Test method for {@link org.jiemamy.utils.functor.core.Generators#compose(org.jiemamy.utils.functor.Functor, org.jiemamy.utils.functor.Generator)}.
	 */
	@Test
	public void testCompose() {
		Generator<String> generator = Generators.constant("Hello");
		Add functor = new Add(", world!");
		Generator<String> composed = Generators.compose(functor, generator);
		assertThat(composed.generate(), is("Hello, world!"));
	}
	
	/**
	 * Test method for {@link Generators#constant(java.lang.Object)}.
	 */
	@Test
	public void testConstant() {
		Object value = new Object();
		Generator<Object> generator = Generators.constant(value);
		assertThat(generator.generate(), sameInstance(value));
	}
	
	/**
	 * Test method for {@link Generators#constant(java.lang.Object)}.
	 */
	@Test
	public void testConstant_Null() {
		Generator<Object> generator = Generators.constant(null);
		assertThat(generator.generate(), is(nullValue()));
	}
}
