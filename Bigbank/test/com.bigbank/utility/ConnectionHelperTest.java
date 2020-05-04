package com.bigbank.utility;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.net.HttpURLConnection;
import java.net.URL;

import main.com.bigbank.helper.ConnectionHelper;

@PrepareForTest({URL.class, HttpURLConnection.class})
public class ConnectionHelperTest {

	@Mock
	HttpURLConnection mockHttpConnection;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void test() throws Exception {
//		verify(mockHttpConnection).setRequestMethod("POST");

		HttpURLConnection urlConnection = PowerMockito.mock(HttpURLConnection.class);
		URL finalUrl = PowerMockito.mock(URL.class);

		PowerMockito.whenNew(URL.class).withArguments("http://www.mock.com/api/v2/game/start").thenReturn(finalUrl);
		PowerMockito.when(finalUrl.openConnection()).thenReturn(urlConnection);
		PowerMockito.when(urlConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
		ConnectionHelper.getInstance().sendPost("http://www.mock.com/api/v2/game/start");

	}


}
