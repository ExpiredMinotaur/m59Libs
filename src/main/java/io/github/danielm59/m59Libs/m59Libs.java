package io.github.danielm59.m59Libs;

import io.github.danielm59.m59Libs.events.M59EventHandler;
import io.github.danielm59.m59Libs.jsonData.M59data;
import io.github.danielm59.m59Libs.log.LogHelper;
import io.github.danielm59.m59Libs.proxy.IProxy;
import io.github.danielm59.m59Libs.reference.Reference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.VERSION, acceptableRemoteVersions = "*")
public class m59Libs
{
	@Mod.Instance(Reference.MODID)
	public static m59Libs instance;

	@SidedProxy(clientSide = Reference.CPROXY, serverSide = Reference.SPROXY)
	public static IProxy proxy;
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new M59EventHandler());;
	}
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
		M59data.load();
		LogHelper.info(proxy.getSaveFolder());
	}
}
