package com.posadskiy.costaccounting.projects.core.db;

import com.posadskiy.costaccounting.projects.core.db.model.DbCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoriesRepository extends MongoRepository<DbCategory, String> {
	DbCategory findByName(String name);
    List<DbCategory> findAllByProjectId(String projectId);
}
