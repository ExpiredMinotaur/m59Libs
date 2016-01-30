package io.github.danielm59.m59Libs;

import io.github.danielm59.m59Libs.proxy.IProxy;
import io.github.danielm59.m59Libs.reference.Reference;
import io.github.danielm59.m59Libs.utility.LogHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.VERSION)
public class m59Libs
{
	@Mod.Instance(Reference.MODID)
	public static m59Libs instance;

	@SidedProxy(clientSide = Reference.CPROXY, serverSide = Reference.SPROXY)
	public static IProxy proxy;
	
	@EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
		LogHelper.info(proxy.getSaveFolder());
	}
}
