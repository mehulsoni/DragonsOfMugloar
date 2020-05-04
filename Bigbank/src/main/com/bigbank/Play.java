package main.com.bigbank;

import org.apache.log4j.Logger;

import java.util.Optional;

import main.com.bigbank.dto.GameDto;
import main.com.bigbank.dto.MessageDto;
import main.com.bigbank.helper.ItemHelper;
import main.com.bigbank.helper.MessageHelper;
import main.com.bigbank.helper.PurchaseHelper;
import main.com.bigbank.helper.ResultHelper;

public class Play {
	private static Logger LOG = Logger.getLogger(Play.class);
	private static Play INSTANCE;

	private Play() {
	}

	public static Play getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Play();
		}
		return INSTANCE;
	}

	void start(GameDto game) throws InterruptedException {
		ItemHelper.getInstance().loadItems(game);
		run(game.getGameId());
	}

	private void run(String gameId) throws InterruptedException {
		boolean isRunning;
		do {
			Optional<MessageDto> optional = MessageHelper.getInstance().findMessage(gameId);
			if (optional.isPresent()) {
				isRunning = false;
				continue;
			}
			LOG.info("Solving " + optional.get());
			MessageHelper.getInstance().solveMessage(gameId, optional.get().getAdId());
			isRunning = isAlive(ResultHelper.getInstance().getLive());
			LOG.info("Is Running " + isRunning);
			PurchaseHelper.getInstance().purchaseItem(gameId);
		} while (isRunning);
		LOG.info("Score [" + ResultHelper.getInstance().getScore() + "]");
	}

	private boolean isAlive(int lives) {
		return lives > 0;
	}

}
