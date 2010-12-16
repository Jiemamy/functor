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
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

import org.jiemamy.utils.functor.ApplyException;

/**
 * Test for {@link Cast}.
 * 
 * @version $Date$
 * @author Suguru ARAKAWA
 */
public class CastTest {
	
	/**
	 * Test method for {@link Cast#to(java.lang.Class)}.
	 */
	@Test
	public void testTo() {
		Cast<String> cast = Cast.to(String.class);
		assertThat(cast.apply("Hello"), is("Hello"));
		assertThat(cast.apply(null), is(nullValue()));
	}
	
	/**
	 * Test method for {@link Cast#to(java.lang.Class)}.
	 */
	@Test(expected = ApplyException.class)
	public void testTo_Exception() {
		Cast<String> cast = Cast.to(String.class);
		cast.apply(new Integer(1));
	}
	
	/**
	 * Test method for {@link Cast#unchecked()}.
	 */
	@Test
	public void testUnchecked() {
		Cast<String> cast = Cast.<String> unchecked();
		assertThat(cast.apply("Hello"), is("Hello"));
		assertThat(cast.apply(null), is(nullValue()));
	}
	
	/**
	 * Test method for {@link Cast#unchecked()}.
	 */
	@Test(expected = ClassCastException.class)
	public void testUnchecked_Exception() {
		Cast<String> cast = Cast.<String> unchecked();
		String result = cast.apply(new Integer(1));
		fail(result);
	}
}
