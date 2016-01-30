package io.github.danielm59.m59Libs.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;

public class ClientProxy extends CommonProxy
{

	@Override
	public MinecraftServer getServer()
	{
		return Minecraft.getMinecraft().getIntegratedServer();
	}

	@Override
	public String getSaveFolder()
	{
		if (getServer() instanceof IntegratedServer)
		{
			IntegratedServer server = (IntegratedServer) getServer();
			String worldName = (server != null) ? server.getFolderName() : "world";
			return Minecraft.getMinecraft().mcDataDir + "/saves/" + worldName + "/";
		} else
		{
			return "PROXY_ERROR";
		}
	}

}
