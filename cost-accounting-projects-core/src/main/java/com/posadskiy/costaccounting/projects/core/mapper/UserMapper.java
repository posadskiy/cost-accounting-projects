package com.posadskiy.costaccounting.projects.core.mapper;

import com.posadskiy.costaccounting.projects.core.db.model.DbCategory;
import com.posadskiy.costaccounting.projects.core.db.model.DbUser;
import com.posadskiy.costaccounting.projects.api.dto.Category;
import com.posadskiy.costaccounting.projects.api.dto.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {
	
	@Mapping(target = "roles", ignore = true)
	@Mapping(target = "purchases", ignore = true)
	@Mapping(target = "purchaseCategories", ignore = true)
	@Mapping(target = "projectId", ignore = true)
	@Mapping(target = "incomes", ignore = true)
	@Mapping(target = "incomeCategories", ignore = true)
	@Mapping(target = "chatId", ignore = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "name", target = "name")
	@Mapping(source = "email", target = "email")
	@Mapping(source = "password", target = "password")
	@Mapping(source = "defaultCurrency", target = "defaultCurrency")
	DbUser mapFromDto(User user);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "name", target = "name")
	@Mapping(source = "email", target = "email")
	@Mapping(source = "password", target = "password", ignore = true)
	@Mapping(source = "defaultCurrency", target = "defaultCurrency")
	@Mapping(source = "purchaseCategories", target = "purchaseCategories")
	@Mapping(source = "incomeCategories", target = "incomeCategories")
	User mapToDto(DbUser user);
	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "name", target = "name")
	@Mapping(source = "emoji", target = "emoji")
	@Mapping(source = "projectId", target = "projectId")
	@Mapping(source = "isPurchase", target = "isPurchase")
	@Mapping(source = "isIncome", target = "isIncome")
	Category mapToDto(DbCategory category);
}
