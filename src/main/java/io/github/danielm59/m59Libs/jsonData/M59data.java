package io.github.danielm59.m59Libs.jsonData;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.UUID;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import io.github.danielm59.m59Libs.m59Libs;
import io.github.danielm59.m59Libs.log.LogHelper;

public class M59data
{
	private static Map<String, ModData> data = Maps.newHashMap();

	private static final Charset charset = Charsets.UTF_8;

	private static final File saveFile = new File(m59Libs.proxy.getSaveFolder(), "test.json");
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public static void updateValue(String modID, UUID playerID, String dataName, Integer dataValue)
	{
		ModData mData;
		if (data.containsKey(modID))
		{
			mData = data.get(modID);
		} else
		{
			mData = new ModData();
		}
		PlayerData pData;
		if (mData.containsKey(playerID))
		{
			pData = mData.get(playerID);
		} else
		{
			pData = new PlayerData();
		}
		pData.put(dataName, dataValue);
		mData.put(playerID, pData);
		data.put(modID, mData);
	}

	public static int getValue(String modID, UUID playerID, String dataName)
	{
		if (containsData(modID, playerID, dataName))
		{
			return data.get(modID).get(playerID).get(dataName);
		} else
		{
			return -1;
		}
	}

	public static boolean containsData(String modID, UUID playerID, String dataName)
	{
		if (data.containsKey(modID))
		{
			if (data.get(modID).containsKey(playerID))
			{
				if (data.get(modID).get(playerID).containsKey(dataName))
				{
					return true;
				}
			}
		}
		return false;
	}

	public static void load()
	{
		if (!saveFile.exists())
			return;

		try
		{
			String json = Files.toString(saveFile, charset);
			Type type = new TypeToken<Map<String, ModData>>()
			{
				private static final long serialVersionUID = 1L;
			}.getType();

			data = gson.fromJson(json, type);
		} catch (JsonSyntaxException e)
		{
			LogHelper.error("Could not parse data file as valid json, deleting file");
			e.printStackTrace();
			saveFile.delete();
		} catch (IOException e)
		{
			LogHelper.error("Failed to read data file from disk, deleting file");
			e.printStackTrace();
			saveFile.delete();
		} finally
		{
			if (data == null)
			{
				data = Maps.newHashMap();
			}
		}
	}

	public static void save()
	{
		new SaveThread(gson.toJson(data)).start();
	}

	private static class SaveThread extends Thread
	{

		private final String data;

		public SaveThread(String data)
		{
			this.data = data;
		}

		@Override
		public void run()
		{
			try
			{
				synchronized (saveFile)
				{
					Files.write(data, saveFile, charset);
				}
			} catch (IOException e)
			{
				LogHelper.error("Failed to save data to file!");
				e.printStackTrace();
			}
		}
	}

}
