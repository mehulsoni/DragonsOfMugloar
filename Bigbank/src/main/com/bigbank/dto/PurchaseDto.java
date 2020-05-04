package main.com.bigbank.dto;

public class PurchaseDto {

	private String shoppingSuccess;
	private Number gold;
	private Number lives;
	private Number level;
	private Number turn;

	public String getShoppingSuccess() {
		return shoppingSuccess;
	}

	public void setShoppingSuccess(String shoppingSuccess) {
		this.shoppingSuccess = shoppingSuccess;
	}

	public Number getGold() {
		return gold;
	}

	public void setGold(Number gold) {
		this.gold = gold;
	}

	public Number getLives() {
		return lives;
	}

	public void setLives(Number lives) {
		this.lives = lives;
	}

	public Number getLevel() {
		return level;
	}

	public void setLevel(Number level) {
		this.level = level;
	}

	public Number getTurn() {
		return turn;
	}

	public void setTurn(Number turn) {
		this.turn = turn;
	}
}
