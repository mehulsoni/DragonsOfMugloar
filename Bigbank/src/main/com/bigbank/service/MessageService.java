package main.com.bigbank.service;

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
import main.com.bigbank.helper.ResultHolder;
import main.com.bigbank.utility.CommonUtility;
import main.com.bigbank.utility.Constant;
import main.com.bigbank.utility.ProbabilityUtility;

public class MessageService {

	private static Logger LOG = Logger.getLogger(MessageService.class);

	private static MessageService INSTANCE;
	private final List<MessageDto> messages = new ArrayList<>();

	private MessageService() {
		super();
	}

	public static MessageService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MessageService();
		}
		return INSTANCE;
	}

	/**
	 * This method is used for getting message based on provided game id and return best message based on ranking and
	 * probability.
	 *
	 * @param gameId
	 * @return
	 */
	public Optional<MessageDto> findMessage(String gameId) {
		LOG.info("Clearing old messages for game id [" + gameId + "]");
		messages.clear();
		LOG.info("Fetching messages for game id [" + gameId + "]");
		List<MessageDto> messages = fetchMessages(gameId);
		List<MessageDto> oMessages = getOrderedMessage(messages);
		this.messages.addAll(oMessages);
		return oMessages.stream().findFirst();
	}

	/**
	 * THis method is used for getting message from http service based on game id.
	 *
	 * @param gameId
	 * @return
	 */
	private List<MessageDto> fetchMessages(String gameId) {
		LOG.info("Get message from server for game id [" + gameId + "]");
		Optional<String> messages = ConnectionService.getInstance()
				.sendGet(String.format(Constant.ALL_MESSAGES_URL, gameId));
		Type listType = new TypeToken<ArrayList<MessageDto>>() {}.getType();
		return ConnectionService.getInstance().gson.fromJson(messages.orElse(Constant.EMPTY_ARRAY), listType);
	}

	/**
	 * This method is sued for getting ordered messages
	 *
	 * @param messages
	 * @return
	 */
	private List<MessageDto> getOrderedMessage(List<MessageDto> messages) {
		return sortMessagesByRank(messages);
	}

	/**
	 * This method is used for filtering trap message and rank it based on probability. Also it return sorted messages
	 * based on probability, expiry, rank and reward.
	 *
	 * @param messages
	 * @return
	 */
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

	/**
	 * This method is used taking two parameter and call http request for solving based on passed adId and game id
	 *
	 * @param gameId
	 * @param addId
	 */
	public void solveMessage(String gameId, String addId) {
		Optional<String> optional = ConnectionService.getInstance().sendPost(
				String.format(Constant.SOLVE_MESSAGES_URL, gameId, addId));
		SolvedMessageDto solvedMessage = ConnectionService.getInstance().gson.fromJson(optional.orElse(Constant.EMPTY_JSON), SolvedMessageDto.class);
		ResultHolder.getInstance().setScore(solvedMessage.getScore());
		ResultHolder.getInstance().setGold(solvedMessage.getGold());
		ResultHolder.getInstance().setLive(solvedMessage.getLives());
		LOG.info(solvedMessage);
		LOG.info(ResultHolder.getInstance());
	}

	/**
	 * This method is used for returning lasted available messages
	 *
	 * @return
	 */
	public List<MessageDto> messages() {
		return messages;
	}
}
