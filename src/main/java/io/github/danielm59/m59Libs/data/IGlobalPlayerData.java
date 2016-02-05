package io.github.danielm59.m59Libs.data;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayerMP;

public interface IGlobalPlayerData
{
	public abstract void updatePlayerData(EntityPlayerMP player, int value);

	public abstract void loadData();

	public abstract boolean containsPlayer(EntityPlayerMP player);

	public abstract int getPlayerData(EntityPlayerMP player);
	
	public abstract int getPlayerData(UUID playerID);

	public abstract void registerPlayer(EntityPlayerMP player);
	
	public abstract int getDefaultValue();

}
