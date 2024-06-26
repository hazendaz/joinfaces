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

package org.joinfaces.autoconfigure.butterfaces;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
		properties = "joinfaces.bootsfaces.get-jquery-from-cdn=false",
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class ButterfacesBootsfacesServletContextInitializerIT {

	@Autowired
	private WebApplicationContext applicationContext;

	@Test
	public void testOverriddenInitParam() {
		assertThat(this.applicationContext.getServletContext().getInitParameter("net.bootsfaces.get_jquery_from_cdn")).isEqualTo("true");
	}
}
