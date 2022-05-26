package com.opencart.utilities;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




public class XlUtil
{
	
	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook wb;
	public static XSSFSheet sh;
	public static XSSFRow rw;
	public static XSSFCell cl;
	
	public static int getRowCount(String xlfilepath,String sheetname ) throws IOException
	{
		fis=new FileInputStream(xlfilepath);
		wb=new XSSFWorkbook(fis);
		sh=wb.getSheet(sheetname);
		int rowcount=sh.getLastRowNum();
		wb.close();
		fis.close();
		return rowcount;
		
		
	}
	public static int getCellcount(String xlfilepath,String sheetname,int rownum) throws IOException
	{
		
		fis=new FileInputStream(xlfilepath);
		wb=new XSSFWorkbook(fis);
		sh=wb.getSheet(sheetname);
		rw=sh.getRow(rownum);
		int cellnum=rw.getLastCellNum();
		wb.close();
		fis.close();
		return cellnum;
		
	}
	
	public static String getCellData(String xlfilepath,String sheetname,int rownum,int cellnum) throws IOException
	{
		fis=new FileInputStream(xlfilepath);
		wb=new XSSFWorkbook(fis);
		sh=wb.getSheet(sheetname);
		rw=sh.getRow(rownum);
		cl=rw.getCell(cellnum);
		String data;
		
		try
		{
			data=cl.getStringCellValue();
		} catch (Exception e)
		{
			data="";
		}
		
		wb.close();
		fis.close();
		return data;
		
	}

}
