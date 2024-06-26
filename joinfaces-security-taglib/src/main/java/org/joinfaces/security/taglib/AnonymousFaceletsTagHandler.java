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

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;

/**
 * TagHandler of anonymous user.
 * @author Marcelo Fernandes
 */
public class AnonymousFaceletsTagHandler extends TagHandler {

	public AnonymousFaceletsTagHandler(TagConfig config) {
		super(config);
	}

	public void apply(FaceletContext faceletContext, UIComponent parent) throws IOException {
		AnonymousFaceletsTag anonymousTag = new AnonymousFaceletsTag();

		boolean isAuthorized = anonymousTag.authorize();

		if (isAuthorized) {
			this.nextHandler.apply(faceletContext, parent);
		}
	}

}
