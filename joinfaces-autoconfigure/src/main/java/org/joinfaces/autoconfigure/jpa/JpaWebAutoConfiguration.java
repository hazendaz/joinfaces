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

package org.joinfaces.autoconfigure.jpa;

import javax.persistence.EntityManager;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.ConditionalOnMissingFilterBean;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;

/**
 * Spring Boot will by default register a
 * {@link org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor OpenEntityManagerInViewInterceptor}
 * to apply the "Open EntityManager in View" pattern, i.e. to allow for lazy loading in web views.
 * <p>
 * Unfortunately the Interceptor doesn't work for JSF, so this AutoConfiguration registers a
 * {@link OpenEntityManagerInViewFilter} instead.
 *
 * @author Lars Grefer
 * @see org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration
 */
@AutoConfiguration(before = HibernateJpaAutoConfiguration.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass({EntityManager.class, OpenEntityManagerInViewFilter.class})
@ConditionalOnProperty(prefix = "spring.jpa", name = "open-in-view", havingValue = "true", matchIfMissing = true)
public class JpaWebAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean({OpenEntityManagerInViewInterceptor.class, OpenEntityManagerInViewFilter.class})
	@ConditionalOnMissingFilterBean(OpenEntityManagerInViewFilter.class)
	public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
		return new OrderedOpenEntityManagerInViewFilter();
	}
}
