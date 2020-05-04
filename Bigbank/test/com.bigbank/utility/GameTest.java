package com.bigbank.utility;

import org.junit.Assert;
import org.junit.Test;

import main.com.bigbank.Game;
import main.com.bigbank.helper.ResultHolder;

public class GameTest {

	@Test
	public void play() {
		Game.main(null);
		Assert.assertTrue(ResultHolder.getInstance().getScore() > 1000);
		Assert.assertTrue(ResultHolder.getInstance().getLive() == 0);
	}

	@Test
	public void playMultipleTimes() throws InterruptedException {
		for (int i = 0; i <= 5; i++) {
			Game.main(null);
			Assert.assertTrue(ResultHolder.getInstance().getScore() >= 1000);
			Thread.sleep(100);
		}
	}

}
