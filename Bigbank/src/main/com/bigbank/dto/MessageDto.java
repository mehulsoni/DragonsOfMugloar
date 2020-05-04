package main.com.bigbank.dto;

public class MessageDto {

	private String adId;
	private String message;
	private String reward;
	private int expiresIn;
	private String probability;
	private int rank;
	private int probabilityRank;

	public String getAdId() {
		return adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getReward() {
		return Integer.valueOf(reward);
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getProbability() {
		return probability;
	}

	public void setProbability(String probability) {
		this.probability = probability;
	}


	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getProbabilityRank() {
		return probabilityRank;
	}

	public void setProbabilityRank(int probabilityRank) {
		this.probabilityRank = probabilityRank;
	}

	@Override
	public String toString() {
		return "Message [" +
				"adId='" + adId + '\'' +
				", message='" + message + '\'' +
				", reward='" + reward + '\'' +
				", expiresIn=" + expiresIn +
				", probability='" + probability + '\'' +
				", rank=" + rank +
				", probabilityRank=" + probabilityRank +
				']';
	}
}
