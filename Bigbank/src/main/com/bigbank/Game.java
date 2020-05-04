package main.com.bigbank;

import org.apache.log4j.Logger;

import java.util.Optional;

import main.com.bigbank.dto.GameDto;
import main.com.bigbank.service.ConnectionService;
import main.com.bigbank.service.PlayService;
import main.com.bigbank.utility.Constant;

public class Game {
	private static Logger LOG = Logger.getLogger(Game.class);

	private static Game INSTANCE;

	private Game() {
		super();
	}

	public static Game getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Game();
		}
		return INSTANCE;
	}

	public static void main(String[] args) {
		Optional<GameDto> gameOptional = Game.getInstance().startGame();
		if (gameOptional.isEmpty()) {
			throw new RuntimeException("There is some problem in starting game, please try later");
		}
		LOG.info(gameOptional.get().getGameId());
		PlayService.getInstance().start(gameOptional.get());
	}

	private Optional<GameDto> startGame() {
		Optional<String> response = ConnectionService.getInstance().sendPost(Constant.START_GAME_URL);
		if (response.isEmpty()) {
			return Optional.empty();
		}
		return Optional.ofNullable(ConnectionService.getInstance().gson.fromJson(response.get(), GameDto.class));
	}

}