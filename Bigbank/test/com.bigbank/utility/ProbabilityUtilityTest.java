package com.bigbank.utility;

import org.junit.Assert;
import org.junit.Test;

import main.com.bigbank.enums.ProbabilityType;
import main.com.bigbank.utility.Constant;
import main.com.bigbank.utility.ProbabilityUtility;

public class ProbabilityUtilityTest {

	@Test
	public void checkProbabilityRanking() {
		ProbabilityUtility.checkProbabilityType(Constant.QUITE_LIKELY);
		Assert.assertEquals(ProbabilityUtility.probabilityRanking(Constant.QUITE_LIKELY), 2);
	}

	@Test
	public void checkIgnoreProbabilityRanking() {
		ProbabilityUtility.checkProbabilityType(Constant.QUITE_LIKELY);
		Assert.assertEquals(ProbabilityUtility.probabilityRanking(ProbabilityType.IGNORE.name()), 7);
	}

	@Test
	public void checkProbabilityType() {
		ProbabilityUtility.checkProbabilityType(Constant.QUITE_LIKELY);
		Assert.assertEquals(ProbabilityUtility.checkProbabilityType(Constant.QUITE_LIKELY), ProbabilityType.GREEN);
	}

	@Test
	public void checkInvalidProbabilityType() {
		ProbabilityUtility.checkProbabilityType(Constant.QUITE_LIKELY);
		Assert.assertEquals(ProbabilityUtility.checkProbabilityType(ProbabilityType.IGNORE.name()), ProbabilityType.IGNORE);
	}

}
