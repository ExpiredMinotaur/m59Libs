package io.github.danielm59.m59Libs.proxy;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.server.FMLServerHandler;

public class ServerProxy extends CommonProxy
{

	@Override
	public MinecraftServer getServer()
	{
		return FMLServerHandler.instance().getServer();
	}

	@Override
	public String getSaveFolder()
	{
		MinecraftServer server = getServer();
		String worldName = (server != null) ? server.getFolderName() : "world";
		return FMLServerHandler.instance().getSavesDirectory() + "/" + worldName + "/";
	}

}
