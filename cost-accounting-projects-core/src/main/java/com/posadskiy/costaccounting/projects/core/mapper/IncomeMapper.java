package com.posadskiy.costaccounting.projects.core.mapper;

import com.posadskiy.currencyconverter.CurrencyConverter;
import com.posadskiy.currencyconverter.enums.Currency;
import com.posadskiy.costaccounting.projects.core.db.model.DbIncome;
import com.posadskiy.costaccounting.projects.api.dto.Income;
import org.apache.commons.lang3.RandomStringUtils;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = CategoryQualifier.class)
@Component
public interface IncomeMapper {

	@Mapping(source = "id", target = "id", qualifiedByName = "idToDbId")
	@Mapping(source = "name", target = "name")
	@Mapping(target = "amount", ignore = true)
	@Mapping(source = "date", target = "date")
	@Mapping(source = "isPrivate", target = "isPrivate")
	@Mapping(source = "category", target = "category", qualifiedByName = "qualifyCategoryIdToInstance")
	DbIncome mapFromDto(Income purchase, @Context CurrencyConverter currencyConverter);

	@Named("idToDbId")
	default String mapIdToDbId(String id) {
		return id != null ? id : RandomStringUtils.randomAlphabetic(10);
	}

	@AfterMapping
	default void mapAmountAndCurrencyToAmount(@MappingTarget DbIncome target, Income income, @Context CurrencyConverter currencyConverter) {
		Double rate = currencyConverter.rateToUsd(Currency.valueOf(income.getCurrency()));
		target.setAmount(income.getAmount() * rate);
	}
}
