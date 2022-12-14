package com.posadskiy.costaccounting.projects.core.db;

import com.posadskiy.costaccounting.projects.core.db.model.DbProject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<DbProject, String> {
}
