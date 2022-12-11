package com.posadskiy.costaccounting.projects.core.db;

import com.posadskiy.costaccounting.projects.core.db.model.DbUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<DbUser, String> {
	List<DbUser> findAllByProjectId(String projectId);
}
