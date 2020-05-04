package main.com.bigbank.service;

import com.google.gson.reflect.TypeToken;

import org.apache.log4j.Logger;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import main.com.bigbank.dto.GameDto;
import main.com.bigbank.dto.ItemDto;
import main.com.bigbank.utility.Constant;

public class ItemService {
	private static ItemService INSTANCE;
	private final List<ItemDto> items = new ArrayList<>();
	private Logger LOG = Logger.getLogger(ItemService.class);

	private ItemService() {
		super();
	}

	public static ItemService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ItemService();
		}
		return INSTANCE;
	}

	/**
	 * This method is used getting items from http call.
	 * @param gameId
	 * @return
	 */
	private List<ItemDto> getShopItem(String gameId) {
		LOG.debug("Inside Item Helper :: Game ID [" + gameId + "]");
		Optional<String> optional = ConnectionService.getInstance()
				.sendGet(String.format(Constant.SHOP_ITEM_URL, gameId));
		Type listType = new TypeToken<ArrayList<ItemDto>>() {}.getType();
		return ConnectionService.getInstance().gson.fromJson(optional.orElse(Constant.EMPTY_ARRAY), listType);
	}

	public void loadItems(GameDto gameDto) {
		items.addAll(getShopItem(gameDto.getGameId()));
	}

	/**
	 * This method is used for returning items stored.
	 *
	 * @return
	 */
	public List<ItemDto> getItems() {
		LOG.info("Getting Item List for Purchasing");
		LOG.debug("Item List: " + items);
		return items;
	}
}
