package io.github.danielm59.m59Libs.data;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

public class ScoreboardData extends GlobalPlayerData
{
	public ScoreboardData(String filePath, int defaultValue)
	{
		super(filePath, defaultValue);
	}

	private Map<UUID, Integer> getSortedScores()
	{
		LinkedList<Entry<UUID, Integer>> list = new LinkedList<Entry<UUID, Integer>>(data.entrySet());
		Collections.sort(list, new Comparator<Entry<UUID, Integer>>()
		{
			public int compare(Entry<UUID, Integer> o1, Entry<UUID, Integer> o2)
			{
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		Map<UUID, Integer> sortedMap = new LinkedHashMap<UUID, Integer>();
		for (Entry<UUID, Integer> entry : list)
		{
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	public UUID getPos(int p)
	{
		LinkedHashMap<UUID, Integer> sortedData = (LinkedHashMap<UUID, Integer>) getSortedScores();
		UUID playerID = (UUID) sortedData.keySet().toArray()[p];
		return playerID;
	}

	public int getSize()
	{
		return data.size();
	}
}