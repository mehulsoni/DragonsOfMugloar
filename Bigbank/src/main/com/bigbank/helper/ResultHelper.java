package main.com.bigbank.helper;

public class ResultHelper {

	private int availableGold;
	private int score;
	private int live;

	private static ResultHelper INSTANCE;

	private ResultHelper() {
	}

	public static ResultHelper getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ResultHelper();
		}
		return INSTANCE;
	}

	public int getGold() {
		return availableGold;
	}

	public void setGold(int availableGold) {
		this.availableGold = availableGold;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLive() {
		return live;
	}

	public void setLive(int live) {
		this.live = live;
	}

	@Override
	public String toString() {
		return "Result [" +
				"availableGold=" + availableGold +
				", score=" + score +
				", live=" + live +
				']';
	}
}
