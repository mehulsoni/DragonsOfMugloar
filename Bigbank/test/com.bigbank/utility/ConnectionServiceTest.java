package com.bigbank.utility;

import com.google.gson.reflect.TypeToken;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import main.com.bigbank.dto.GameDto;
import main.com.bigbank.dto.MessageDto;
import main.com.bigbank.service.ConnectionService;
import main.com.bigbank.utility.Constant;

public class ConnectionServiceTest {

	@Test
	public void testSendPost() {
		GameDto dto = getGame();
		Assert.assertTrue(!dto.getGameId().isEmpty());
	}

	@Test
	public void testSendWrong_URL_In_postCall() {
		Optional optional = ConnectionService.getInstance().sendPost("https://www.wrong_url.com");
		Assert.assertTrue(optional.isEmpty());
	}

	private GameDto getGame() {
		Optional<String> optional = ConnectionService.getInstance().sendPost(Constant.START_GAME_URL);
		Assert.assertTrue(optional.isPresent());
		GameDto dto = ConnectionService.getInstance().gson.fromJson(optional.get().toString(), GameDto.class);
		return dto;
	}

	@Test
	public void testSendGet() {
		Optional<String> optional = ConnectionService.getInstance().sendPost(Constant.START_GAME_URL);
		Assert.assertTrue(optional.isPresent());
		GameDto dto = ConnectionService.getInstance().gson.fromJson(optional.get().toString(), GameDto.class);
		Optional<String> optionalMessage = ConnectionService.getInstance()
				.sendGet(String.format(Constant.ALL_MESSAGES_URL, dto.getGameId()));
		Assert.assertTrue(optionalMessage.isPresent());
		Type listType = new TypeToken<ArrayList<MessageDto>>() {}.getType();
		List<MessageDto> messages = ConnectionService
				.getInstance()
				.gson
				.fromJson(optionalMessage.orElse(
						Constant.EMPTY_ARRAY), listType);
		Assert.assertTrue(!messages.isEmpty());
	}
}
