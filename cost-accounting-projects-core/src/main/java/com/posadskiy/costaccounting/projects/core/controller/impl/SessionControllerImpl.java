package com.posadskiy.costaccounting.projects.core.controller.impl;

import com.posadskiy.costaccounting.projects.core.controller.SessionController;
import com.posadskiy.costaccounting.projects.core.db.SessionRepository;
import com.posadskiy.costaccounting.projects.core.db.model.DbSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionControllerImpl implements SessionController {

	public static final long SESSION_LIFE_TIME_MS = 2592000000L;
	public static final int SESSION_LIFE_TIME_S = 2592000;

	private final SessionRepository repository;

	@Autowired
	public SessionControllerImpl(SessionRepository repository) {
		this.repository = repository;
	}

	public DbSession create(String sessionId, String userId) {
		return repository.save(new DbSession(sessionId, userId, System.currentTimeMillis() + SESSION_LIFE_TIME_MS));
	}
}
