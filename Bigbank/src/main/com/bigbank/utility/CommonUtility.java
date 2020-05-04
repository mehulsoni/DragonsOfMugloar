package main.com.bigbank.utility;

import java.util.HashMap;
import java.util.Map;

import main.com.bigbank.dto.MessageDto;
import main.com.bigbank.enums.ProbabilityType;

public class CommonUtility {

	private static final Map<String, Integer> CART = new HashMap<>();

	public static boolean isTrapMessage(MessageDto message) {
		return applyPattern(message.getMessage(), "super awesome diamond");
	}

	private static boolean applyPattern(String message, String pattern) {
		return message.contains(pattern);
	}

	public static int applyRating(MessageDto message) {
		if (applyPattern(message.getMessage(), "and share some of the profit")) {
			return 3;
		}
		if (applyPattern(message.getMessage(), "advertisement")) {
			return 2;
		}
		if (applyPattern(message.getMessage(), "deranged")) {
			return 2;
		}
		if (applyPattern(message.getMessage(), "magic")) {
			return 2;
		}
		return 1;
	}

	public static String findBestItemToPurchase(MessageDto message, int gold) {
//		String key = extractMessageKey(message.getMessage());
//
//		System.out.println("Found key :" + key);
//		if (List.of("keep").contains(key)) {
//			return checkIntoCart("cs", 1);
////			"name": "Claw Sharpening"
//		}
//		if (List.of("transport").contains(key)) {
//			return checkIntoCart("gas", 1);
////			"name": "Gasoline",
//		}
//		if (List.of("attacked", "palace", "church", "tower").contains(key)) {
//			return checkIntoCart("wax", 1);
////			"name": "Copper Plating",
//		}
//		if (List.of("mystery").contains(key)) {
//			return checkIntoCart("tricks", 1);
////			"name": "Book of Tricks",
//		}
//		if (List.of("bog", "village", "plains", "meadow").contains(key)) {
//			return checkIntoCart("wingpot", 1);
//			//"name": "Potion of Stronger Wings",
//		}
//
//		if (List.of("savannah").contains(key)) {
//			return checkIntoCart("ch", 2);
////			"name": "Claw Honing",
//		}
//		if (List.of("transport").contains(key)) {
//			return checkIntoCart("rf", 2);
////			"name": "Rocket Fuel",
//		}
//		if (List.of("dungeon", "fort", "stronghold", "castle").contains(key)) {
//			return checkIntoCart("iron", 2);
//			//			"name": "Iron Plating",
//		}
//		if (List.of("investigate").contains(key)) {
//			return checkIntoCart("mtrix", 2);
//
//			//			"name": "Book of Megatricks",
//		}
//		if (List.of("swamp", "peninsula", "grassland", "mountains", "manor").contains(key)) {
//			return checkIntoCart("wingpotmax", 1);
////			"name":"Potion of Awesome Wings",
//		}
//		return checkIntoCart("cs", 1);

		if (gold >= 100) {
			return message != null && message.getProbabilityRank() == ProbabilityType.BLACK.getLevel() ? "wingpotmax" : "ch";
		} else {
			return message != null && message.getProbabilityRank() == ProbabilityType.BLACK.getLevel() ? "wingpot" : "cs";
		}
	}

//	private static String checkIntoCart(String code, Integer defaultValue) {
//		Integer count = CART.get(code);
//		if (count != null && count > 0) {
//			CART.put(code, --count);
//			return "no";
//		}
//		CART.put(code, defaultValue);
//		return code;
//	}
//
//
//	private static String extractMessageKey(String message) {
//
//		if (message.toLowerCase().contains("transport")) {
//			return "transport";
//		}
//
//		if (message.toLowerCase().contains("investigate") || message.toLowerCase().contains("magic")) {
//			return "investigate";
//		}
//
//		if (message.toLowerCase().contains("attacked")) {
//			return "attacked";
//		}
//
//		final Pattern p = Pattern.compile("Help defending (\\w++) in (\\w++) from the intruders");
//		final Matcher m = p.matcher(message);
//		if (m.matches()) {
//			return m.group(1);
//		}
//
//		final Pattern p2 = Pattern.compile("Help defending (\\w++) (\\w++) in (\\w++) from the intruders");
//		final Matcher m2 = p.matcher(message);
//		if (m2.matches()) {
//			return m2.group(1);
//		}
//
//		return "skip";
//	}

}
