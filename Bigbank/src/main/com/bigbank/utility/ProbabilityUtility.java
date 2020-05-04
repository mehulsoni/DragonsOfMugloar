package main.com.bigbank.utility;

import main.com.bigbank.enums.ProbabilityType;

/**
 * Probability Utility Class used for mapping message probability with its success type
 */
public class ProbabilityUtility {

	/**
	 * This method is used to map probability with its attempt type (BLUE, GREEN, RED,YELLOW, BLACK, NO)
	 *
	 * @param type
	 * @return
	 */
	public static ProbabilityType checkProbabilityType(String type) {
		switch (type) {
			case Constant.RATHER_DETRIMENTAL:
				return ProbabilityType.RED;
			case Constant.SUICIDE_MISSION:
			case Constant.PLAYING_WITH_FIRE:
				return ProbabilityType.BLACK;
			case Constant.RISKY:
			case Constant.GAMBLE:
				return ProbabilityType.YELLOW;
			case Constant.HMMM:
			case Constant.QUITE_LIKELY:
			case Constant.A_WALK_IN_THE_PARK:
				return ProbabilityType.GREEN;
			case Constant.PIECE_OF_CAKE:
			case Constant.SURE_THING:
				return ProbabilityType.BLUE;
			case Constant.IMPOSSIBLE:
				return ProbabilityType.NO;
			default:
				return ProbabilityType.IGNORE;
		}
	}

	/**
	 * This method is used for returning probability level based on passed probability type
	 *
	 * @param type
	 * @return
	 */
	public static int probabilityRanking(String type) {
		switch (type) {
			case Constant.RATHER_DETRIMENTAL:
				return (ProbabilityType.RED.getLevel());
			case Constant.SUICIDE_MISSION:
			case Constant.PLAYING_WITH_FIRE:
				return (ProbabilityType.BLACK.getLevel());
			case Constant.RISKY:
			case Constant.GAMBLE:
				return (ProbabilityType.YELLOW.getLevel());
			case Constant.HMMM:
			case Constant.QUITE_LIKELY:
			case Constant.A_WALK_IN_THE_PARK:
				return (ProbabilityType.GREEN.getLevel());
			case Constant.PIECE_OF_CAKE:
			case Constant.SURE_THING:
				return (ProbabilityType.BLUE.getLevel());
			case Constant.IMPOSSIBLE:
				return (ProbabilityType.NO.getLevel());
			default:
				return (ProbabilityType.IGNORE.getLevel());
		}
	}
}
