package io.github.danielm59.m59Libs.events;

import java.util.UUID;

import io.github.danielm59.m59Libs.jsonData.M59data;
import io.github.danielm59.m59Libs.reference.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class M59EventHandler
{
	@SubscribeEvent
	public void onEntityJoinWorldEvent(EntityJoinWorldEvent event)
	{
		if (!event.world.isRemote)
		{
			if (event.entity instanceof EntityPlayer)
			{
				EntityPlayer playerE = (EntityPlayer) event.entity;
				UUID playerID = playerE.getUniqueID();
				int last = M59data.getValue(Reference.MODID, playerID, "LOGINS");
				last = (last < 0) ? 0:last;
				M59data.updateValue(Reference.MODID, playerID, "LOGINS", last + 1);
				M59data.save();
			}
		}
	}
}
