package main.com.bigbank.enums;

public enum ProbabilityType {
	BLUE(1), GREEN(2), YELLOW(3), RED(4), BLACK(5), NO(6), IGNORE(7);
	private final int level;

	ProbabilityType(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}
}