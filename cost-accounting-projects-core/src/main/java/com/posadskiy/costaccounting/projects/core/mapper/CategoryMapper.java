package com.posadskiy.costaccounting.projects.core.mapper;

import com.posadskiy.costaccounting.projects.api.dto.Category;
import com.posadskiy.costaccounting.projects.core.db.model.DbCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CategoryMapper {
	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "name", target = "name")
	@Mapping(source = "projectId", target = "projectId")
	@Mapping(source = "emoji", target = "emoji")
	@Mapping(source = "isIncome", target = "isIncome")
	@Mapping(source = "isPurchase", target = "isPurchase")
    Category mapToDto(final DbCategory category);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "name", target = "name")
	@Mapping(source = "projectId", target = "projectId")
	@Mapping(source = "emoji", target = "emoji")
	@Mapping(source = "isIncome", target = "isIncome")
	@Mapping(source = "isPurchase", target = "isPurchase")
	DbCategory mapFromDto(final Category category);
}
