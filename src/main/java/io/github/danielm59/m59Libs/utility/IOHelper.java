package io.github.danielm59.m59Libs.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class IOHelper
{
	@SuppressWarnings("rawtypes")
	public static HashMap loadData(HashMap data, String filename)
	{
		try
		{
			data = (HashMap) readObject(filename);
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return data;
	}

	@SuppressWarnings("rawtypes")
	public static void saveData(HashMap data, String filename)
	{
		try
		{
			writeObject(data, filename);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private static Object readObject(String filename) throws IOException, ClassNotFoundException
	{

		FileInputStream fileIn = new FileInputStream(filename);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		Object data = in.readObject();
		in.close();
		fileIn.close();
		return data;

	}

	private static void writeObject( Object data, String filename) throws IOException
	{

		File file = new File(filename);
		if (!file.exists())
		{
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		FileOutputStream fileOut = new FileOutputStream(file);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(data);
		out.close();
		fileOut.close();

	}
}
