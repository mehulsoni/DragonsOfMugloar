package main.com.bigbank.helper;

import com.google.gson.reflect.TypeToken;

import org.apache.log4j.Logger;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import main.com.bigbank.dto.MessageDto;
import main.com.bigbank.dto.SolvedMessageDto;
import main.com.bigbank.enums.ProbabilityType;
import main.com.bigbank.utility.CommonUtility;
import main.com.bigbank.utility.Constant;
import main.com.bigbank.utility.ProbabilityUtility;

public class MessageHelper {

	private static Logger LOG = Logger.getLogger(MessageHelper.class);

	private static MessageHelper INSTANCE;

	private MessageHelper() {
	}

	public static MessageHelper getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MessageHelper();
		}
		return INSTANCE;
	}

	private final List<MessageDto> messages = new ArrayList<>();

	public Optional<MessageDto> findMessage(String gameId) {
		LOG.info("Clearing old messages for game id [" + gameId + "]");
		messages.clear();
		LOG.info("Fetching messages for game id [" + gameId + "]");
		List<MessageDto> messages = fetchMessages(gameId);
		List<MessageDto> oMessages = getOrderedMessage(messages);
		this.messages.addAll(oMessages);
		return oMessages.stream().findFirst();
	}

	private List<MessageDto> fetchMessages(String gameId) {
		LOG.info("Get message from server for game id [" + gameId + "]");
		Optional<String> messages = ConnectionHelper.getInstance()
				.sendGet(String.format(Constant.ALL_MESSAGES_URL, gameId));
		Type listType = new TypeToken<ArrayList<MessageDto>>() {}.getType();
		return ConnectionHelper.getInstance().gson.fromJson(messages.orElse(Constant.EMPTY_ARRAY), listType);
	}

	private List<MessageDto> getOrderedMessage(List<MessageDto> messages) {
		return sortMessagesByRank(messages);
	}

	private List<MessageDto> sortMessagesByRank(List<MessageDto> messages) {
		return messages
				.stream()
				.filter(m -> !CommonUtility.isTrapMessage(m))
				.filter(c -> ProbabilityUtility.checkProbabilityType(c.getProbability()) != ProbabilityType.IGNORE)
				.peek(x -> x.setRank(CommonUtility.applyRating(x)))
				.peek(x -> x.setProbabilityRank(ProbabilityUtility.probabilityRanking(x.getProbability())))
				.sorted(Comparator.comparing(MessageDto::getProbabilityRank)
						.thenComparing(MessageDto::getExpiresIn)
						.thenComparing(MessageDto::getRank)
						.thenComparing(MessageDto::getReward)
				).collect(Collectors.toList());
	}

	public void solveMessage(String gameId, String addId) {
		Optional<String> optional = ConnectionHelper.getInstance().sendPost(
				String.format(Constant.SOLVE_MESSAGES_URL, gameId, addId));
		SolvedMessageDto solvedMessage = ConnectionHelper.getInstance().gson.fromJson(optional.orElse(Constant.EMPTY_JSON), SolvedMessageDto.class);
		ResultHelper.getInstance().setScore(solvedMessage.getScore());
		ResultHelper.getInstance().setGold(solvedMessage.getGold());
		ResultHelper.getInstance().setLive(solvedMessage.getLives());
		LOG.info(solvedMessage);
		LOG.info(ResultHelper.getInstance());
	}

	public List<MessageDto> messages() {
		return messages;
	}
}
