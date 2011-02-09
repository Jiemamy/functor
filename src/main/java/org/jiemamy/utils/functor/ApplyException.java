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
 * {@link Functor}の適用に失敗したことを表す例外。
 * 
 * @version $Date$
 * @author Suguru ARAKAWA
 */
public class ApplyException extends RuntimeException {
	
	private static final long serialVersionUID = 5590913018636614714L;
	

	/**
	 * インスタンスを生成する。
	 */
	public ApplyException() {
		super();
	}
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param message メッセージ(省略可)
	 */
	public ApplyException(String message) {
		super(message);
	}
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param message メッセージ(省略可)
	 * @param cause この例外の原因となった例外(省略可)
	 */
	public ApplyException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * インスタンスを生成する。
	 * 
	 * @param cause この例外の原因となった例外(省略可)
	 */
	public ApplyException(Throwable cause) {
		super(cause);
	}
}
