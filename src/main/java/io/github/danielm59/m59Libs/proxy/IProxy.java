package io.github.danielm59.m59Libs.proxy;

import net.minecraft.server.MinecraftServer;

public interface IProxy
{
	public abstract MinecraftServer getServer();

	public abstract String getSaveFolder();
}
