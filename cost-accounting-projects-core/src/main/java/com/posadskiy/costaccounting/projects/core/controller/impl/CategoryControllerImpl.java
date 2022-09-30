package com.posadskiy.costaccounting.projects.core.controller.impl;

import com.posadskiy.costaccounting.projects.core.controller.CategoryController;
import com.posadskiy.costaccounting.projects.core.db.CategoriesRepository;
import com.posadskiy.costaccounting.projects.core.db.model.DbCategory;
import com.posadskiy.costaccounting.projects.api.dto.Category;
import com.posadskiy.costaccounting.projects.core.exception.CategoryDoesNotExistException;
import com.posadskiy.costaccounting.projects.core.mapper.CategoryMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CategoryControllerImpl implements CategoryController {

	private final CategoriesRepository categoriesRepository;
	private final CategoryMapper categoryMapper;

	@Autowired	
	public CategoryControllerImpl(CategoriesRepository categoriesRepository, CategoryMapper categoryMapper) {
		this.categoriesRepository = categoriesRepository;
		this.categoryMapper = categoryMapper;
	}

	@Override
	public List<Category> getIncomeCategories() {
		List<Category> list = new ArrayList<>();
		for (DbCategory category : categoriesRepository.findAll()) {
			if (category.getIsIncome() == Boolean.TRUE) {
				list.add(
					categoryMapper.mapToDto(category)
				);
			}
		}
		return list;
	}

	@Override
	public List<Category> getPurchaseCategories() {
		List<Category> list = new ArrayList<>();
		for (DbCategory category : categoriesRepository.findAll()) {
			if (category.getIsPurchase() == Boolean.TRUE) {
				list.add(
					categoryMapper.mapToDto(category)
				);
			}
		}
		return list;
	}

	@Override
	public @NotNull DbCategory getById(@NotNull final String id) {
		final Optional<DbCategory> foundCategory = categoriesRepository.findById(id);
		if (!foundCategory.isPresent()) throw new CategoryDoesNotExistException();
		
		return foundCategory.get();
	}

	@Override
	public @NotNull DbCategory getByName(@NotNull final String name) {
		final DbCategory foundCategory = categoriesRepository.findByName(name);
		if (foundCategory == null) throw new CategoryDoesNotExistException();

		return foundCategory;
	}

	/*@Override
	public String getNameWithEmoji(DbCategory category) {
		StringBuilder nameWithEmoji = new StringBuilder();
		String emoji = category.getEmoji() != null ? category.getEmoji() : DEFAULT_EMOJI;
		nameWithEmoji.append(emoji).append(" ").append(category.getName());
		
		return nameWithEmoji.toString();
	}*/
}
