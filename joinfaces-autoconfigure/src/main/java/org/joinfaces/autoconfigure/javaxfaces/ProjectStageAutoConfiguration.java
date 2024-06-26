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

import javax.faces.application.ProjectStage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

/**
 * Auto configuration of JSF {@link ProjectStage}.
 *
 * @author Lars Grefer
 */
@Slf4j
@AutoConfiguration(before = JavaxFacesAutoConfiguration.class)
@ConditionalOnClass(ProjectStage.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class ProjectStageAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty("debug")
	public static ProjectStageCustomizer developmentProjectStageCustomizer() {
		return new ProjectStageCustomizer(ProjectStage.Development);
	}

	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(value = "debug", havingValue = "false", matchIfMissing = true)
	public static ProjectStageCustomizer productionProjectStageCustomizer() {
		return new ProjectStageCustomizer(ProjectStage.Production);
	}

	/**
	 * {@link BeanPostProcessor} for setting the JSF
	 * {@link JavaxFaces2_0Properties#projectStage project stage}.
	 *
	 * @author Lars Grefer
	 */
	@RequiredArgsConstructor
	public static class ProjectStageCustomizer implements BeanPostProcessor {

		private final ProjectStage projectStage;

		@Override
		public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
			if (bean instanceof JavaxFaces2_0Properties && ((JavaxFaces2_0Properties) bean).getProjectStage() == null) {
				((JavaxFaces2_0Properties) bean).setProjectStage(this.projectStage);
			}
			return bean;
		}
	}
}
