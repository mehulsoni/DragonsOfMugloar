package com.bigbank.utility;

import org.junit.Assert;
import org.junit.Test;

import main.com.bigbank.dto.MessageDto;
import main.com.bigbank.utility.CommonUtility;
import main.com.bigbank.utility.Constant;

public class CommonUtilityTest {


	@Test
	public void testIsTrapMessage() {
		MessageDto messageDto = new MessageDto();
		messageDto.setMessage(Constant.SUPER_AWESOME_DIAMOND);
		Boolean isTrap = CommonUtility.isTrapMessage(messageDto);
		Assert.assertTrue(isTrap);
	}

	@Test
	public void testIsNotTrapMessage() {
		MessageDto messageDto = new MessageDto();
		messageDto.setMessage(Constant.EMPTY_ARRAY);
		Boolean isTrap = CommonUtility.isTrapMessage(messageDto);
		Assert.assertFalse(isTrap);
	}

	@Test
	public void testApplyRating() {
		MessageDto messageDto = new MessageDto();
		messageDto.setMessage(Constant.SHARE_SOME_OF_THE_PROFIT);
		int rate_1 = CommonUtility.applyRating(messageDto);
		Assert.assertEquals(rate_1, 3);

		messageDto.setMessage(Constant.WING_POT_MAX);
		int rate_2 = CommonUtility.applyRating(messageDto);
		Assert.assertEquals(rate_2, 1);
	}

	@Test
	public void testFindBestItem() {

		String item_1 = CommonUtility.findBestItemToPurchase(20);
		Assert.assertEquals(item_1, Constant.WING_POT);

		String item_2 = CommonUtility.findBestItemToPurchase(120);
		Assert.assertEquals(item_2, Constant.WING_POT_MAX);
	}

}
