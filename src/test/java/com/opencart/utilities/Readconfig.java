package com.opencart.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class Readconfig
{
	public  Properties pro;

	public Readconfig()
	{
		String path = ".//configuration//config.properties";
		try
		{
			FileInputStream inputstream = new FileInputStream(path);
			pro = new Properties();
			pro.load(inputstream);
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	public String getApplicationUrl()
	{
		return pro.getProperty("baseurl");
	}

	public String getEmail()
	{
		return pro.getProperty("userEmail");
	}

	public String getpassword()
	{
		return pro.getProperty("userpassword");
	}

}
