package com.posadskiy.costaccounting.projects.core;

import com.posadskiy.costaccounting.projects.core.mapper.*;
import com.posadskiy.currencyconverter.CurrencyConverter;
import com.posadskiy.currencyconverter.config.ConfigBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableAutoConfiguration
@EnableMongoRepositories
@ComponentScan
public class SpringConfiguration {
    @Value("${currencyConverterApi.apiKey}")
    private String currencyConverterApiApiKey;

    @Value("${currencyLayer.apiKey}")
    private String currencyLayerApiKey;

    @Value("${openExchangeRates.apiKey}")
    private String openExchangeRatesApiKey;

	@Bean
	public CategoryMapper categoryMapper() {
		return new CategoryMapperImpl();
	}

    @Bean
	public IncomeMapper incomeMapper() {
		return new IncomeMapperImpl();
	}

	@Bean
	public PurchaseMapper purchaseMapper() {
		return new PurchaseMapperImpl();
	}

	@Bean
	public UserMapper userMapper() {
		return new UserMapperImpl();
	}

    @Bean
    public CategoryQualifier categoryQualifier() {
        return new CategoryQualifier();
    }

    @Bean
    public CurrencyConverter currencyConverter() {
        return new CurrencyConverter(
            new ConfigBuilder()
                .currencyConverterApiApiKey(currencyConverterApiApiKey)
                .currencyLayerApiKey(currencyLayerApiKey)
                .openExchangeRatesApiKey(openExchangeRatesApiKey)
                .build()
        );
    }

}
