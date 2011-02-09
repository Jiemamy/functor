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
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import org.jiemamy.utils.functor.Editor;

/**
 * Test for {@link Editors}.
 * 
 * @version $Date$
 * @author Suguru ARAKAWA
 */
public class EditorsTest {
	
	/**
	 * Test method for {@link Editors#compose(java.lang.Iterable)}.
	 */
	@Test
	public void testComposeEditors() {
		List<Editor<StringBuilder, String>> list = new ArrayList<Editor<StringBuilder, String>>();
		list.add(Editors.<StringBuilder, String, String> compose(new Append(), new Add("0")));
		list.add(Editors.<StringBuilder, String, String> compose(new Append(), new Add("1")));
		list.add(Editors.<StringBuilder, String, String> compose(new Append(), new Add("2")));
		
		Editor<StringBuilder, String> editor = Editors.compose(list);
		StringBuilder buf = new StringBuilder();
		StringBuilder edited = editor.edit(buf, "Hello");
		assertThat(edited.toString(), is("Hello0Hello1Hello2"));
	}
	
	/**
	 * Test method for {@link Editors#compose(java.lang.Iterable)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testComposeEditors_EditNull() {
		List<Editor<StringBuilder, String>> list = new ArrayList<Editor<StringBuilder, String>>();
		list.add(Editors.<StringBuilder, String, String> compose(new Append(), new Add("0")));
		list.add(Editors.<StringBuilder, String, String> compose(new Append(), new Add("1")));
		list.add(Editors.<StringBuilder, String, String> compose(new Append(), new Add("2")));
		Editor<StringBuilder, String> editor = Editors.compose(list);
		editor.edit(null, "Hello");
	}
	
	/**
	 * Test method for {@link Editors#compose(org.jiemamy.utils.functor.Editor, org.jiemamy.utils.functor.Functor)}.
	 */
	@Test
	public void testComposeFunctor() {
		Editor<StringBuilder, String> editor = Editors.compose(new Append(), new Add(", world!"));
		StringBuilder buf = new StringBuilder();
		StringBuilder edited = editor.edit(buf, "Hello");
		assertThat(edited.toString(), is("Hello, world!"));
	}
	
	/**
	 * Test method for {@link Editors#compose(org.jiemamy.utils.functor.Editor, org.jiemamy.utils.functor.Functor)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testComposeFunctor_EditNull() {
		Editor<StringBuilder, String> editor = Editors.compose(new Append(), new Add(", world!"));
		editor.edit(null, "Hello");
	}
	
	/**
	 * Test method for {@link Editors#through()}.
	 */
	@Test
	public void testThrough() {
		Editor<StringBuilder, String> editor = Editors.through();
		StringBuilder buf = new StringBuilder();
		StringBuilder edited = editor.edit(buf, "Hello");
		assertThat(edited.length(), is(0));
		assertThat(edited, sameInstance(buf));
	}
	
	/**
	 * Test method for {@link Editors#through()}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testThrough_EditNull() {
		Editor<StringBuilder, String> editor = Editors.through();
		editor.edit(null, "Hello");
	}
}
