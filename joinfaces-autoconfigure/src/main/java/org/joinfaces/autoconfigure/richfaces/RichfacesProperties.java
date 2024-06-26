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

package org.joinfaces.autoconfigure.richfaces;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import lombok.Data;
import org.joinfaces.autoconfigure.servlet.initparams.ServletContextInitParameter;
import org.joinfaces.autoconfigure.servlet.initparams.ServletContextInitParameterProperties;
import org.richfaces.application.CoreConfiguration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.convert.DurationUnit;

/**
 * Configuration properties of RichFaces.
 * Taken from
 * + core/src/main/java/org/richfaces/application/CoreConfiguration.java
 * + components/a4j/src/main/java/org/richfaces/application/CommonComponentsConfiguration.java
 * + components/rich/src/main/java/org/richfaces/application/IterationComponentsConfiguration.java
 *
 * @author Jamillo Santos
 * @author Lars Grefer
 */
@Data
@ConfigurationProperties(prefix = "joinfaces.richfaces")
public class RichfacesProperties implements ServletContextInitParameterProperties {

	// (defaultValue = "true", names = "org.richfaces.enableControlSkinning")
	@ServletContextInitParameter("org.richfaces.enableControlSkinning")
	private Boolean enableControlSkinning;

	// (defaultValue = "false", names = "org.richfaces.enableControlSkinningClasses")
	@ServletContextInitParameter("org.richfaces.enableControlSkinningClasses")
	private Boolean enableControlSkinningClasses;

	// (names = "org.richfaces.skin")
	@ServletContextInitParameter(CoreConfiguration.SKIN_PARAM_NAME)
	private String skin;

	// (names = "org.richfaces.baseSkin")
	@ServletContextInitParameter(CoreConfiguration.BASE_SKIN_PARAM_NAME)
	private String baseSkin;

	// (defaultValue = "86400" /* 24 * 60 * 60 */, names = "org.richfaces.resourceDefaultTTL", literal = true)
	@DurationUnit(ChronoUnit.SECONDS)
	@ServletContextInitParameter("org.richfaces.resourceDefaultTTL")
	private Duration resourceDefaultTtl;

	// (defaultValue = "512", names = "org.richfaces.resourceCacheSize", literal = true)
	@ServletContextInitParameter(CoreConfiguration.RESOURCES_CACHE_SIZE_PARAM_NAME)
	private Integer resourceCacheSize;

	// (names = "org.richfaces.resourceDefaultVersion")
	@ServletContextInitParameter("org.richfaces.resourceDefaultVersion")
	private String resourceDefaultVersion;

	@NestedConfigurationProperty
	private final Cache cache = new Cache();

	@NestedConfigurationProperty
	private final ResourceMapping resourceMapping = new ResourceMapping();

	@NestedConfigurationProperty
	private final ResourceOptimization resourceOptimization = new ResourceOptimization();

	// (defaultValue = "true", names = "org.richfaces.executeAWTInitializer", literal = true)
	@ServletContextInitParameter("org.richfaces.executeAWTInitializer")
	private Boolean executeAwtInitializer;

	@NestedConfigurationProperty
	private final Push push = new Push();

	@NestedConfigurationProperty
	private final Builtin builtin = new Builtin();

	@NestedConfigurationProperty
	private final Queue queue = new Queue();

	// (defaultValue = "false", names = "org.richfaces.datatableUsesViewLocale")
	@ServletContextInitParameter("org.richfaces.datatableUsesViewLocale")
	private String datatableUsesViewLocale;

	/**
	 * Cache namespace.
	 */
	@Data
	public static class Cache {

		// (names = "org.richfaces.cache.LRU_MAP_CACHE_SIZE", literal = true)
		@ServletContextInitParameter("org.richfaces.cache.LRU_MAP_CACHE_SIZE")
		private Integer lruMapCacheSize;
	}

	/**
	 * ResourceMapping namespace.
	 */
	@Data
	public static class ResourceMapping {

		// (names = "org.richfaces.resourceMapping.enabled", literal = true)
		@ServletContextInitParameter("org.richfaces.resourceMapping.enabled")
		private Boolean enabled;

		// (names = "org.richfaces.resourceMapping.location", literal = true)
		@ServletContextInitParameter("org.richfaces.resourceMapping.location")
		private String location;

		// (names = "org.richfaces.resourceMapping.mappingFile")
		@ServletContextInitParameter("org.richfaces.resourceMapping.mappingFile")
		private String mappingFile;

		// (names = "org.richfaces.resourceMapping.compressedStages")
		@ServletContextInitParameter("org.richfaces.resourceMapping.compressedStages")
		private String compressedStages;

		// (names = "org.richfaces.resourceMapping.packedStages")
		@ServletContextInitParameter("org.richfaces.resourceMapping.packedStages")
		private String packedStages;
	}

	/**
	 * Resource optimization namespace.
	 */
	@Data
	public static class ResourceOptimization {

		// (defaultValue = "false", names = { "org.richfaces.resourceOptimization.enabled", "org.richfaces.resourceMapping.enabled" }, literal = true)
		@ServletContextInitParameter("org.richfaces.resourceOptimization.enabled")
		private Boolean enabled;

		// (defaultValue = "Production,SystemTest", names = { "org.richfaces.resourceOptimization.compressionStages", "org.richfaces.resourceMapping.compressedStages" }, literal = true)
		@ServletContextInitParameter("org.richfaces.resourceOptimization.compressionStages")
		private String compressionStages;

		// (defaultValue = "All", names = { "org.richfaces.resourceOptimization.packagingStages", "org.richfaces.resourceMapping.packedStages" }, literal = true)
		@ServletContextInitParameter("org.richfaces.resourceOptimization.packagingStages")
		private String packedStages;
	}

	/**
	 * Push namespace.
	 */
	@Data
	public static class Push {

		// (names = "org.richfaces.push.handlerMapping", literal = true)
		@ServletContextInitParameter("org.richfaces.push.handlerMapping")
		private String handlerMapping;

		@NestedConfigurationProperty
		private final Jms jms = new Jms();

		// (defaultValue = "false", names="org.richfaces.push.initializeOnStartup")
		@ServletContextInitParameter("org.richfaces.push.initializeOnStartup")
		private Boolean initializeOnStartup;

		@NestedConfigurationProperty
		private final Session session = new Session();

		/**
		 * Jms namespace.
		 */
		@Data
		public static class Jms {

			// (defaultValue = "/ConnectionFactory", names = "org.richfaces.push.jms.connectionFactory")
			@ServletContextInitParameter("org.richfaces.push.jms.connectionFactory")
			private String connectionFactory;

			// (defaultValue = "", names = "org.richfaces.push.jms.enabled")
			@ServletContextInitParameter("org.richfaces.push.jms.enabled")
			private Boolean enabled;

			// (defaultValue = "/topic", names = "org.richfaces.push.jms.topicsNamespace")
			@ServletContextInitParameter("org.richfaces.push.jms.topicsNamespace")
			private String topicsNamespace;

			// (defaultValue = "", names = "org.richfaces.push.jms.connectionUsername")
			@ServletContextInitParameter("org.richfaces.push.jms.connectionUsername")
			private String connectionUsername;

			// (defaultValue = "", names = "org.richfaces.push.jms.connectionPassword")
			@ServletContextInitParameter("org.richfaces.push.jms.connectionPassword")
			private String connectionPassword;
		}

		/**
		 * Session namespace.
		 */
		@Data
		public static class Session {

			// (defaultValue = "300000", names="org.richfaces.push.session.maxInactiveInterval")
			@DurationUnit(ChronoUnit.MILLIS)
			@ServletContextInitParameter("org.richfaces.push.session.maxInactiveInterval")
			private Duration maxInactiveInterval;
		}

	}

	/**
	 * Builtin namespace.
	 */
	@Data
	public static class Builtin {

		@NestedConfigurationProperty
		private final Sort sort = new Sort();

		@NestedConfigurationProperty
		private final Filter filter = new Filter();

		/**
		 * Sort namespace.
		 */
		@Data
		public static class Sort {

			// (defaultValue = "true", names = "org.richfaces.builtin.sort.enabled")
			@ServletContextInitParameter("org.richfaces.builtin.sort.enabled")
			private Boolean enabled;
		}

		/**
		 * Filter namespace.
		 */
		@Data
		public static class Filter {

			// (defaultValue = "true", names = "org.richfaces.builtin.filter.enabled")
			@ServletContextInitParameter("org.richfaces.builtin.filter.enabled")
			private Boolean enabled;
		}
	}

	/**
	 * Queue namespace.
	 */
	@Data
	public static class Queue {

		// (defaultValue = "true", names = "org.richfaces.queue.enabled", literal = true)
		@ServletContextInitParameter("org.richfaces.queue.enabled")
		private Boolean enabled;
	}
}
