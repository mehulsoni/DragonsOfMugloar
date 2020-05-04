package main.com.bigbank.utility;

import org.apache.log4j.Logger;

import main.com.bigbank.dto.MessageDto;
import main.com.bigbank.helper.ResultHolder;
import main.com.bigbank.service.MessageService;

public class CommonUtility {

	private static Logger LOG = Logger.getLogger(CommonUtility.class);

	public static boolean isTrapMessage(MessageDto message) {
		LOG.debug("Checking trap message" + message);
		return match(message.getMessage(), Constant.SUPER_AWESOME_DIAMOND);
	}

	private static boolean match(String message, String pattern) {
		return message.contains(pattern);
	}

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

	public static String findBestItemToPurchase(MessageDto message, int gold) {
		LOG.info("We can get best item based on message type and message contains");
		LOG.info("Next available " + message);
		if (gold >= 100) {
			return Constant.WING_POT_MAX;
		} else {
			return Constant.WING_POT;
		}
	}

}
