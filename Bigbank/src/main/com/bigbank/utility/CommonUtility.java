package main.com.bigbank.utility;

import org.apache.log4j.Logger;

import main.com.bigbank.dto.MessageDto;

public class CommonUtility {

	private static Logger LOG = Logger.getLogger(CommonUtility.class);

	/**
	 * This method checked message is trap type or normal
	 *
	 * @param message
	 * @return
	 */
	public static boolean isTrapMessage(MessageDto message) {
		LOG.debug("Checking trap message" + message);
		return match(message.getMessage(), Constant.SUPER_AWESOME_DIAMOND);
	}

	private static boolean match(String message, String pattern) {
		return message.contains(pattern);
	}

	/**
	 * This method return reputation rank based on message type
	 * @param message
	 * @return
	 */
	public static int applyRating(MessageDto message) {
		if (match(message.getMessage(), Constant.SHARE_SOME_OF_THE_PROFIT)) {
			return 3;
		}
		if (match(message.getMessage(), Constant.ADVERTISEMENT)) {
			return 2;
		}

		if (match(message.getMessage(), Constant.DERANGED)) {
			return 2;
		}

		if (match(message.getMessage(), Constant.MAGIC)) {
			return 2;
		}
		return 1;
	}

	public static String findBestItemToPurchase(int gold) {
		// Here , Instead of directly sending item based on gold. we can add logic to check message
		// type and extract keys from message like well, savanna, tower and select item based on it.
		if (gold >= 100) {
			return Constant.WING_POT_MAX;
		} else {
			return Constant.WING_POT;
		}
	}

}
