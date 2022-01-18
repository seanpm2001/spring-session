/*
 * Copyright 2014-2020 the original author or authors.
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

package org.springframework.session.jdbc;

import org.springframework.session.config.SessionRepositoryCustomizer;

/**
 * A {@link SessionRepositoryCustomizer} implementation that applies MySQL specific
 * optimized SQL statements to {@link JdbcIndexedSessionRepository}.
 *
 * @author Vedran Pavic
 * @since 2.5.0
 */
public class MySqlJdbcIndexedSessionRepositoryCustomizer
		implements SessionRepositoryCustomizer<JdbcIndexedSessionRepository> {

	// @formatter:off
	private static final String CREATE_SESSION_ATTRIBUTE_QUERY = ""
			+ "INSERT INTO %TABLE_NAME%_ATTRIBUTES (SESSION_PRIMARY_ID, ATTRIBUTE_NAME, ATTRIBUTE_BYTES) "
			+ "VALUES (?, ?, ?) "
			+ "ON DUPLICATE KEY UPDATE ATTRIBUTE_BYTES = VALUES(ATTRIBUTE_BYTES)";
	// @formatter:on

	@Override
	public void customize(JdbcIndexedSessionRepository sessionRepository) {
		sessionRepository.setCreateSessionAttributeQuery(CREATE_SESSION_ATTRIBUTE_QUERY);
	}

}