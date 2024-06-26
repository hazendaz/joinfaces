/*
 * Copyright 2016-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.joinfaces.autoconfigure.tomcat;

/**
 * Tomcat runtime environment.
 *
 * @author Marcelo Fernandes
 */
public enum TomcatRuntime  {
	/**
	 * Runtime jar inside IDE.
	 */
	UNPACKAGED_JAR,
	/**,
	 * Runtime jar testing or war testing.
	 */
	TEST,
	/**
	 * Runtime java -jar jar packaging.
	 */
	UBER_JAR,
	/**
	 * Runtime java -jar war packaging.
	 */
	UBER_WAR,
	/**
	 * Runtime war inside tomcat servlet container.
	 */
	UNPACKAGED_WAR
}
