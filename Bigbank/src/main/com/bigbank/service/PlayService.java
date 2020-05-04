package main.com.bigbank.service;

import org.apache.log4j.Logger;

import java.util.Optional;

import main.com.bigbank.dto.GameDto;
import main.com.bigbank.dto.MessageDto;
import main.com.bigbank.helper.ResultHolder;

public class PlayService {
	private static Logger LOG = Logger.getLogger(PlayService.class);
	private static PlayService INSTANCE;

	private PlayService() {
		super();
	}

	public static PlayService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PlayService();
		}
		return INSTANCE;
	}

	/**
	 * This method load all available items and start game.
	 *
	 * @param game
	 */
	public void start(GameDto game) {
		ItemService.getInstance().loadItems(game);
		run(game.getGameId());
	}

	/**
	 * This method isi is entry point of game. It will run in loop till live is equal zero.
	 *
	 * @param gameId
	 */
	private void run(String gameId) {
		boolean isRunning;
		do {
			Optional<MessageDto> optional = MessageService.getInstance().findMessage(gameId);
			if (optional.isEmpty()) {
				isRunning = false;
				continue;
			}
			LOG.info("Solving " + optional.get());
			MessageService.getInstance().solveMessage(gameId, optional.get().getAdId());
			isRunning = isAlive(ResultHolder.getInstance().getLive());
			LOG.info(MessageService.getInstance().messages());
			PurchaseService.getInstance().purchaseItem(gameId);
		} while (isRunning);
		LOG.info("Score [" + ResultHolder.getInstance().getScore() + "]");
	}

	/**
	 * This method is used for checking is alive or not based on passed lives
	 *
	 * @param lives
	 * @return
	 */
	private boolean isAlive(int lives) {
		return lives > 0;
	}

}
