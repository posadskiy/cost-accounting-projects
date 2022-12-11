package com.posadskiy.costaccounting.projects.core.mapper;

import com.posadskiy.costaccounting.moneyactions.api.dto.Category;
import com.posadskiy.costaccounting.moneyactions.api.dto.User;
import com.posadskiy.costaccounting.projects.core.db.model.DbCategory;
import com.posadskiy.costaccounting.projects.core.db.model.DbUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

	@Mapping(source = "id", target = "id")
	@Mapping(source = "name", target = "name")
	User mapToDto(DbUser user);
	
	@Mapping(source = "id", target = "id")
	Category mapToDto(DbCategory category);
}
