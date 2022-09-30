package com.posadskiy.costaccounting.projects.core.controller.impl;

import com.posadskiy.costaccounting.projects.core.controller.UserController;
import com.posadskiy.costaccounting.projects.core.db.UserRepository;
import com.posadskiy.costaccounting.projects.core.db.model.DbIncome;
import com.posadskiy.costaccounting.projects.core.db.model.DbPurchase;
import com.posadskiy.costaccounting.projects.core.db.model.DbUser;
import com.posadskiy.costaccounting.projects.api.dto.User;
import com.posadskiy.costaccounting.projects.core.exception.DeletingOldIncomeWithoutIdException;
import com.posadskiy.costaccounting.projects.core.exception.DeletingOldPurchaseWithoutIdException;
import com.posadskiy.costaccounting.projects.core.exception.UserDoesNotExistException;
import com.posadskiy.costaccounting.projects.core.exception.UserIsIncorrectException;
import com.posadskiy.costaccounting.projects.core.mapper.UserMapper;
import com.posadskiy.costaccounting.projects.core.validation.UserValidation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserControllerImpl implements UserController {
	public static final int LAST_PURCHASES_COUNT = 10;
	public static final int LAST_INCOMES_COUNT = 10;

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final UserValidation userValidation;

	@Autowired
	public UserControllerImpl(UserRepository userRepository, UserMapper userMapper, UserValidation userValidation) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.userValidation = userValidation;
	}

	public DbUser getById(String userId) {
		Optional<DbUser> foundUser = userRepository.findById(userId);
		if (!foundUser.isPresent()) throw new UserDoesNotExistException();
		
		return foundUser.get();
	}

	@Override
	public User getMappedById(String userId) {
		return userMapper.mapToDto(
			getById(userId)
		);
	}

	@Override
	public DbUser getByEmail(String email) {
		DbUser foundUser = userRepository.findByEmail(email);
		if (foundUser == null) throw new UserDoesNotExistException();

		return foundUser;
	}	

	@Override
	public DbUser getByChatId(Long chatId) {
		DbUser foundUser = userRepository.findByChatId(chatId);
		if (foundUser == null) throw new UserDoesNotExistException();

		return foundUser;
	}
	
	@Override
	public DbUser save(@NotNull DbUser dbUser) {
		return userRepository.save(dbUser);
	}

	@Override
	public void savePurchase(String userId, DbPurchase dbPurchase) {
		DbUser foundUser = getById(userId);

		foundUser.getPurchases().add(dbPurchase);

		//categoryStatisticsController.increasePurchaseToStatisticCategory(foundUser, dbPurchase);

		this.save(foundUser);
	}

	@Override
	public void saveIncome(String userId, DbIncome dbIncome) {
		DbUser foundUser = getById(userId);

		foundUser.getIncomes().add(dbIncome);

		//categoryStatisticsController.increaseIncomeToStatisticCategory(foundUser, dbIncome);

		this.save(foundUser);
	}

	@Override
	public List<DbPurchase> lastPurchases(String userId) {
		DbUser foundUser = getById(userId);

		return foundUser.getPurchases()
			.stream()
			.sorted(Comparator.comparing(DbPurchase::getDate).reversed())
			.limit(LAST_PURCHASES_COUNT)
			.collect(Collectors.toList());
	}

	@Override
	public List<DbIncome> lastIncomes(String userId) {
		DbUser foundUser = getById(userId);

		return foundUser.getIncomes()
			.stream()
			.sorted(Comparator.comparing(DbIncome::getDate).reversed())
			.limit(LAST_INCOMES_COUNT)
			.collect(Collectors.toList());
	}
	
	public DbUser deletePurchase(String userId, String purchaseId) {
		DbUser foundUser = getById(userId);

		final DbPurchase deletedPurchase = CollectionUtils.find(foundUser.getPurchases(), dbPurchase -> StringUtils.equals(dbPurchase.getId(), purchaseId));
		if (deletedPurchase == null) throw new DeletingOldPurchaseWithoutIdException();

		final List<DbPurchase> purchasesWithoutDeleted = ListUtils.removeAll(foundUser.getPurchases(), Collections.singleton(deletedPurchase));

		/*categoryStatisticsController.decreaseMoneyActionToStatisticCategory(
			foundUser.getStatistics()
				.get(Utils.getMonthAndYear(deletedPurchase.getDate()))
				.getPurchaseCategories()
				.get(deletedPurchase.getCategory().getId())
			, deletedPurchase);*/

		foundUser.setPurchases(purchasesWithoutDeleted);
		return this.save(foundUser);
	}

	public DbUser deleteIncome(String userId, String incomeId) {
		DbUser foundUser = getById(userId);

		final DbIncome deletedIncome = CollectionUtils.find(foundUser.getIncomes(), dbIncome -> StringUtils.equals(dbIncome.getId(), incomeId));
		if (deletedIncome == null) throw new DeletingOldIncomeWithoutIdException();

		final List<DbIncome> incomesWithoutDeleted = ListUtils.removeAll(foundUser.getIncomes(), Collections.singleton(deletedIncome));

		/*categoryStatisticsController.decreaseMoneyActionToStatisticCategory(
			foundUser.getStatistics()
				.get(Utils.getMonthAndYear(deletedIncome.getDate()))
				.getIncomeCategories()
				.get(deletedIncome.getCategory().getId())
			, deletedIncome);*/
		
		foundUser.setIncomes(incomesWithoutDeleted);
		return this.save(foundUser);
	}
	
	public List<User> getAllUsersByUserId(String userId) {
		final String projectId = getById(userId).getProjectId();
		return getAllUsersByProjectId(projectId).stream().map(userMapper::mapToDto).collect(Collectors.toList());
	}
	
	public List<DbUser> getAllUsersByProjectId(String projectId) {
		return userRepository.findAllByProjectId(projectId);
	}

	@Override
	public User updateUser(User user) {
		final DbUser foundUser = getById(user.getId());
		
		if (!userValidation.userValidate(user)) throw new UserIsIncorrectException();
		
		foundUser.setName(user.getName());
		foundUser.setDefaultCurrency(user.getDefaultCurrency());
		return userMapper.mapToDto(
			this.save(foundUser)
		);
	}
}
