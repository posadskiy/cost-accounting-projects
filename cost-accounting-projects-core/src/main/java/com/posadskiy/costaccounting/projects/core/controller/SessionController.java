package com.posadskiy.costaccounting.projects.core.controller;

import com.posadskiy.costaccounting.projects.core.db.model.DbSession;

public interface SessionController {
	DbSession create(String sessionId, String userId);
}
