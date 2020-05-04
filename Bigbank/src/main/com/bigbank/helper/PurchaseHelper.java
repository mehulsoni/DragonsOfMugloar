package main.com.bigbank.helper;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

import main.com.bigbank.dto.MessageDto;
import main.com.bigbank.dto.SolvedMessageDto;
import main.com.bigbank.enums.ProbabilityType;
import main.com.bigbank.utility.CommonUtility;
import main.com.bigbank.utility.Constant;

public class PurchaseHelper {
	private Logger LOG = Logger.getLogger(PurchaseHelper.class);

	private static PurchaseHelper INSTANCE;

	private PurchaseHelper() {
	}

	public static PurchaseHelper getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PurchaseHelper();
		}
		return INSTANCE;
	}

	public void purchaseItem(String gameId) {
		LOG.info("Purchasing Item for game id [" + gameId + "]");
		if (isHealingPortionBudgetAvailable()) {
			LOG.info("Purchasing healing portion for game id [" + gameId + "]");
			purchaseItem(gameId, ResultHelper.getInstance().getGold(), Constant.HEALING_PORTION_CODE);
		}
		if (isNextItemInBudgetAvailable()) {
			LOG.info("Purchasing next required item for game id [" + gameId + "]");
			Optional<MessageDto> message = checkNextAvailableMessage(MessageHelper.getInstance().messages(),
					ProbabilityType.YELLOW,
					ProbabilityType.RED,
					ProbabilityType.BLACK);
			LOG.info("Purchasing next required item for game id [" + gameId + "] and " + message);

			purchaseItem(gameId, ResultHelper.getInstance().getGold(),
					CommonUtility.findBestItemToPurchase(message.isPresent() ? null : message.get()
							, ResultHelper.getInstance().getGold()));
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
		boolean isExists = ItemHelper.getInstance().getItems().stream()
				.filter(item -> item.getCost() <= gold)
				.anyMatch(item -> code.equals(item.getId()));

		if (isExists) {
			Optional<String> optional = ConnectionHelper.getInstance()
					.sendPost(String.format(Constant.PURCHASE_ITEM_URL, gameId, code));
			SolvedMessageDto updated = ConnectionHelper.getInstance().gson
					.fromJson(optional.orElse(Constant.EMPTY_JSON), SolvedMessageDto.class);
			ResultHelper.getInstance().setGold(updated.getGold());
		}
	}

	private boolean isNextItemInBudgetAvailable() {
		return (ResultHelper.getInstance().getGold() - (Constant.HEALING_POTION_COST * 2)) > 0;
	}

	private boolean isHealingPortionBudgetAvailable() {
		return ResultHelper.getInstance().getLive() <= Constant.THRESHOLD_LIVES
				&& Constant.HEALING_POTION_COST <= ResultHelper.getInstance().getGold();
	}

}
