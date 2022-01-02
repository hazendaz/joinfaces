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

package org.joinfaces.security.taglib;

import org.springframework.security.taglibs.authz.AbstractAuthorizeTag;

/**
 * A concrete implementation of {@link AbstractAuthorizeTag} for use with
 * standard Facelets rendering technology.
 * @author Marcelo Fernandes
 */
public class AuthenticatedFaceletsTag extends AbstractFaceletsAuthorizeTag {

	/**
	 * A default constructor. Callers of this constructor are responsible for
	 * setting one or more of the tag attributes in
	 * {@link AbstractAuthorizeTag}.
	 */
	public AuthenticatedFaceletsTag() {
		setAccess("isAuthenticated()");
	}

}
