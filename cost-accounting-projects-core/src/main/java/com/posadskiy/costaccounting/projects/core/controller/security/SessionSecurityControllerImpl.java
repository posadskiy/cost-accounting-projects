package com.posadskiy.costaccounting.projects.core.controller.security;

import com.posadskiy.restsecurity.controller.SessionSecurityController;
import com.posadskiy.restsecurity.exception.SessionDoesNotExistException;
import com.posadskiy.costaccounting.projects.core.db.SessionRepository;
import com.posadskiy.costaccounting.projects.core.db.model.DbSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SessionSecurityControllerImpl implements SessionSecurityController {
	
	private final SessionRepository sessionRepository;

	@Autowired
	public SessionSecurityControllerImpl(SessionRepository sessionRepository) {
		this.sessionRepository = sessionRepository;
	}

	public DbSession getSessionById(String sessionId) {
		Optional<DbSession> foundSession = sessionRepository.findById(sessionId);
		if (!foundSession.isPresent()) throw new SessionDoesNotExistException();
		
		return foundSession.get();
	}

	@Override
	public boolean isSessionExist(String sessionId) {
		DbSession sessionById = getSessionById(sessionId);
		return sessionById != null;
	}

	@Override
	public boolean isSessionExpired(String sessionId) {
		DbSession sessionById = getSessionById(sessionId);
		return sessionById.getTime() < System.currentTimeMillis();
	}

	@Override
	public String getUserIdBySessionId(String sessionId) {
		final DbSession foundSession = getSessionById(sessionId);
		return  foundSession.getUserId();
	}
}
