package main.com.bigbank.dto;

public class SolvedMessageDto {
	private Boolean success;
	private int lives;
	private int gold;
	private int score;
	private int highScore;
	private int turn;
	private String message;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Solved Response [" +
				"success=" + success +
				", lives=" + lives +
				", gold=" + gold +
				", score=" + score +
				", highScore=" + highScore +
				", turn=" + turn +
				", message='" + message + '\'' +
				']';
	}
}
