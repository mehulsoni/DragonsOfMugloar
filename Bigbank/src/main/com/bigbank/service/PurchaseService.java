package main.com.bigbank.service;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

import main.com.bigbank.dto.MessageDto;
import main.com.bigbank.dto.SolvedMessageDto;
import main.com.bigbank.enums.ProbabilityType;
import main.com.bigbank.helper.ResultHolder;
import main.com.bigbank.utility.CommonUtility;
import main.com.bigbank.utility.Constant;

public class PurchaseService {
	private static PurchaseService INSTANCE;
	private Logger LOG = Logger.getLogger(PurchaseService.class);

	private PurchaseService() {
		super();
	}

	public static PurchaseService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PurchaseService();
		}
		return INSTANCE;
	}

	public void purchaseItem(String gameId) {
		LOG.info("Purchasing Item for game id [" + gameId + "]");
		if (isHealingPortionBudgetAvailable()) {
			LOG.info("Purchasing healing portion for game id [" + gameId + "]");
			purchaseItem(gameId, ResultHolder.getInstance().getGold(), Constant.HEALING_PORTION_CODE);
		}
		if (isNextItemInBudgetAvailable()) {
			LOG.info("Purchasing next required item for game id [" + gameId + "]");
			Optional<MessageDto> message = checkNextAvailableMessage(MessageService.getInstance().messages(),
					ProbabilityType.YELLOW,
					ProbabilityType.RED,
					ProbabilityType.BLACK);
			LOG.info("Purchasing next required item for game id [" + gameId + "] and " + message);
			purchaseItem(gameId, ResultHolder.getInstance().getGold(),
					CommonUtility.findBestItemToPurchase(ResultHolder.getInstance().getGold()));
		}
	}

	private Optional<MessageDto> checkNextAvailableMessage(List<MessageDto> orderedMessages, ProbabilityType... args) {
		for (ProbabilityType type : args) {
			MessageDto message = orderedMessages.stream()
					.filter(m -> m.getProbabilityRank() == type.getLevel() && m.getExpiresIn() > 1)
					.findFirst()
					.orElse(null);
			if (message == null) {
				continue;
			}
			return Optional.of(message);
		}
		return Optional.empty();
	}

	private void purchaseItem(String gameId, int gold, String code) {
		boolean isExists = ItemService.getInstance().getItems().stream()
				.filter(item -> item.getCost() <= gold)
				.anyMatch(item -> code.equals(item.getId()));

		if (isExists) {
			Optional<String> optional = ConnectionService.getInstance()
					.sendPost(String.format(Constant.PURCHASE_ITEM_URL, gameId, code));
			SolvedMessageDto updated = ConnectionService.getInstance().gson
					.fromJson(optional.orElse(Constant.EMPTY_JSON), SolvedMessageDto.class);
			ResultHolder.getInstance().setGold(updated.getGold());
		}
	}

	private boolean isNextItemInBudgetAvailable() {
		return (ResultHolder.getInstance().getGold() - (Constant.HEALING_POTION_COST * 2)) > 0;
	}

	private boolean isHealingPortionBudgetAvailable() {
		return ResultHolder.getInstance().getLive() <= Constant.THRESHOLD_LIVES
				&& Constant.HEALING_POTION_COST <= ResultHolder.getInstance().getGold();
	}

}
