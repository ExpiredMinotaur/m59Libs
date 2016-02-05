package io.github.danielm59.m59Libs.data;

import java.util.HashMap;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayerMP;

public class GlobalPlayerData implements IGlobalPlayerData
{
	private String filePath;
	private int defultValue;
	protected HashMap<UUID, Integer> data = new HashMap<UUID, Integer>();
	
	public GlobalPlayerData(String filePath, int defaultValue)
	{
		this.filePath = filePath;
		this.defultValue = defaultValue;
	}
	
	@Override
	public void updatePlayerData(EntityPlayerMP player, int value)
	{
		UUID playerID = player.getGameProfile().getId();
		data.put(playerID, value);
		DataHelper.saveData(data, filePath);

	}

	@SuppressWarnings("unchecked")
	@Override
	public void loadData()
	{
		data = DataHelper.loadData(data, filePath);

	}

	@Override
	public boolean containsPlayer(EntityPlayerMP player)
	{
		UUID playerID = player.getGameProfile().getId();
		return data.containsKey(playerID);
	}

	@Override
	public int getPlayerData(EntityPlayerMP player)
	{
		return getPlayerData(player.getGameProfile().getId());
		
	}

	@Override
	public int getPlayerData(UUID playerID)
	{
		return data.get(playerID);

	}

	@Override
	public void registerPlayer(EntityPlayerMP player)
	{
		if (!containsPlayer(player))
		{
			updatePlayerData(player, getDefaultValue());
		}

	}

	@Override
	public int getDefaultValue()
	{
		return defultValue;
	}

}
