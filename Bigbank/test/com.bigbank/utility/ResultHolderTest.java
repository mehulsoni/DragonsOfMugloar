package com.bigbank.utility;

import org.junit.Assert;
import org.junit.Test;

import main.com.bigbank.helper.ResultHolder;

public class ResultHolderTest {

	@Test
	public void testResultHelperData() {
		ResultHolder resultHolder = ResultHolder.getInstance();
		resultHolder.setScore(100);
		resultHolder.setGold(1000);
		resultHolder.setLive(3);

		Assert.assertEquals(ResultHolder.getInstance().getGold(), 1000);
		Assert.assertEquals(ResultHolder.getInstance().getLive(), 3);
		Assert.assertEquals(ResultHolder.getInstance().getScore(), 100);

	}
}
