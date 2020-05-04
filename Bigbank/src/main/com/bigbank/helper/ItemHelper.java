package main.com.bigbank.helper;

import com.google.gson.reflect.TypeToken;

import org.apache.log4j.Logger;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import main.com.bigbank.dto.GameDto;
import main.com.bigbank.dto.ItemDto;
import main.com.bigbank.utility.Constant;

public class ItemHelper {
	private Logger LOG = Logger.getLogger(ItemHelper.class);
	private static ItemHelper INSTANCE;

	private ItemHelper() {
	}

	public static ItemHelper getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ItemHelper();
		}
		return INSTANCE;
	}

	private final List<ItemDto> items = new ArrayList<>();

	private List<ItemDto> getShopItem(String gameId) {
		Optional<String> optional = ConnectionHelper.getInstance()
				.sendGet(String.format(Constant.SHOP_ITEM_URL, gameId));
		Type listType = new TypeToken<ArrayList<ItemDto>>() {}.getType();
		return ConnectionHelper.getInstance().gson.fromJson(optional.orElse(Constant.EMPTY_ARRAY), listType);
	}

	public void loadItems(GameDto gameDto) {
		items.addAll(getShopItem(gameDto.getGameId()));
	}

	public List<ItemDto> getItems() {
		return items;
	}
}
