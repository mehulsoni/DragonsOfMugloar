package com.bigbank.utility;

import org.junit.Assert;
import org.junit.Test;

import main.com.bigbank.helper.ResultHelper;

public class ResultHelperTest {

	@Test
	public void testResultHelperData() {
		ResultHelper resultHelper = ResultHelper.getInstance();
		resultHelper.setScore(100);
		resultHelper.setGold(1000);
		resultHelper.setLive(3);

		Assert.assertEquals(ResultHelper.getInstance().getGold(), 1000);
		Assert.assertEquals(ResultHelper.getInstance().getLive(), 3);
		Assert.assertEquals(ResultHelper.getInstance().getScore(), 100);

	}
}
