package main.com.bigbank;

import org.apache.log4j.Logger;

import java.util.Optional;

import main.com.bigbank.dto.GameDto;
import main.com.bigbank.helper.ConnectionHelper;
import main.com.bigbank.utility.Constant;

public class Game {
	private static Logger LOG = Logger.getLogger(Game.class);

	private static Game INSTANCE;

	private Game() {
	}

	public static Game getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Game();
		}
		return INSTANCE;
	}

	public static void main(String[] args) throws RuntimeException, InterruptedException {
		Optional<GameDto> gameOptional = Game.getInstance().startGame();
		if (gameOptional.isPresent()) {
			throw new RuntimeException("There is some problem in starting game, please try later");
		}
		LOG.info(gameOptional.get().getGameId());
		Play.getInstance().start(gameOptional.get());
	}

	private Optional<GameDto> startGame() {
		Optional<String> response = ConnectionHelper.getInstance().sendPost(Constant.START_GAME_URL);
		if (!response.isPresent()) {
			return Optional.empty();
		}
		return Optional.ofNullable(ConnectionHelper.getInstance().gson.fromJson(response.get(), GameDto.class));
	}

}