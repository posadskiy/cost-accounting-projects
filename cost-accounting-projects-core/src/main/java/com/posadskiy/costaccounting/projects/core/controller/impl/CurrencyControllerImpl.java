package com.posadskiy.costaccounting.projects.core.controller.impl;

import com.posadskiy.currencyconverter.enums.Currency;
import com.posadskiy.costaccounting.projects.core.controller.CurrencyController;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CurrencyControllerImpl implements CurrencyController {
	@Override
	public List<String> getCurrencies() {
		return Arrays
			.stream(Currency.values())
			.map(Enum::name)
			.collect(Collectors.toList());
	}
}
