package com.posadskiy.costaccounting.projects.core.db.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "session")
public class DbSession {
	@Id
	private String id;
	private String userId;
	private long time;

	public DbSession(String userId, long time) {
		this.userId = userId;
		this.time = time;
	}
}
