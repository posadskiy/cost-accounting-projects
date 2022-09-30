package com.posadskiy.costaccounting.projects.core.db;

import com.posadskiy.costaccounting.projects.core.db.model.DbSession;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SessionRepository extends MongoRepository<DbSession, String> {
}
