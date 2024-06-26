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

package org.joinfaces.autoconfigure.javaxfaces;

import javax.faces.context.FacesContext;
import javax.faces.flow.FlowHandler;
import javax.faces.push.PushContext;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;

/**
 * Auto configuration for Standard Javax Faces Properties.
 *
 * @author Marcelo Fernandes
 */
@AutoConfiguration
@ConditionalOnClass(FacesContext.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class JavaxFacesAutoConfiguration {

	/**
	 * Auto configuration for JSF 2.0.
	 */
	@Configuration(proxyBeanMethods = false)
	@EnableConfigurationProperties(JavaxFaces2_0Properties.class)
	public static class JavaxFaces2_0AutoConfiguration {
	}

	/**
	 * Auto configuration for JSF 2.1.
	 */
	@Configuration(proxyBeanMethods = false)
	@EnableConfigurationProperties(JavaxFaces2_1Properties.class)
	public static class JavaxFaces2_1AutoConfiguration {
	}

	/**
	 * Auto configuration for JSF 2.2.
	 */
	@Configuration(proxyBeanMethods = false)
	@EnableConfigurationProperties(JavaxFaces2_2Properties.class)
	@ConditionalOnClass(FlowHandler.class)
	public static class JavaxFaces2_2AutoConfiguration {
	}

	/**
	 * Auto configuration for JSF 2.3.
	 */
	@Configuration(proxyBeanMethods = false)
	@EnableConfigurationProperties(JavaxFaces2_3Properties.class)
	@ConditionalOnClass(PushContext.class)
	public static class JavaxFaces2_3AutoConfiguration {

		@Bean
		public static FacesServletPropertiesPostProcessor disableFacesservletToXhtml(ApplicationContext applicationContext) {
			return new FacesServletPropertiesPostProcessor(applicationContext);
		}

		@RequiredArgsConstructor
		public static class FacesServletPropertiesPostProcessor implements BeanPostProcessor, PriorityOrdered {

			private final ApplicationContext applicationContext;

			@Override
			public int getOrder() {
				return Ordered.HIGHEST_PRECEDENCE;
			}

			@Override
			public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
				if (bean instanceof FacesServletProperties) {
					FacesServletProperties facesServletProperties = (FacesServletProperties) bean;
					Boolean disableFacesservletToXhtml = this.applicationContext.getBean(JavaxFaces2_3Properties.class).getDisableFacesservletToXhtml();
					if (disableFacesservletToXhtml != null && disableFacesservletToXhtml) {
						facesServletProperties.getUrlMappings().remove("*.xhtml");
					}
				}
				return bean;
			}
		}
	}

}
