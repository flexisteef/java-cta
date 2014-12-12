package com.ibb.seven.rcp.ticklers.CheckTicklerApi;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;

public class CheckTicklerApiTest
	{

		CheckTicklerApi checkTicklerApi;

		  @Before
		  public void setup()
		  {
			  checkTicklerApi = new CheckTicklerApi();
		  }
		
		
		  @Test
		  public void testCheckTicklerScan() {
			  
			 
			  
			  String tempStr;
			  tempStr= checkTicklerApi.check("hallo **0d", null);
			  String newDateStr = checkTicklerApi.check("asdafa asfascas **1d", tempStr);
			  DateTime time = checkTicklerApi.toDate("12-12-2014");
			  if(newDateStr != null)
		        {
		        DateTime ticklerDateTime = checkTicklerApi.toDate(newDateStr);
		        System.out.println("HIER IS IN APP de nieuwe datetime: " + ticklerDateTime);
		        }
			  
			  DateTime expectedDateTime = DateTime.now().withTimeAtStartOfDay();
			  assertEquals(expectedDateTime, time);
		  }
	
@Test
public void testCheckTicklerScanSemiEmpty() {

	  
	  String tempStr;
	  tempStr= checkTicklerApi.check("hallo ", null);
	  String newDateStr = checkTicklerApi.check("asdafa asfascas **1d", tempStr);
	  DateTime time = checkTicklerApi.toDate("11-12-2014");

      DateTime ticklerDateTime = checkTicklerApi.toDate(newDateStr);
      System.out.println("HIER IS IN APP de nieuwe datetime: " + ticklerDateTime);
      
	  
	  DateTime expectedDateTime = DateTime.now().plusDays(1).withTimeAtStartOfDay();
	  assertEquals(expectedDateTime, ticklerDateTime);
}
@Test
public void testCheckTicklerScanEmpty() {
	
	  
	  String tempStr;
	  tempStr= checkTicklerApi.check("hallo ", null);
	  String newDateStr = checkTicklerApi.check("asdafa asfascas ", tempStr);
	  //DateTime time = checkTicklerApi.toDate("11-12-2014");

      DateTime ticklerDateTime = checkTicklerApi.toDate(newDateStr);
      System.out.println("HIER IS IN APP de nieuwe datetime: " + ticklerDateTime);
      
	  
	  DateTime expectedDateTime = DateTime.now().plusDays(1).withTimeAtStartOfDay();
	  assertEquals(null, ticklerDateTime);
}
@Test
public void testCheckTicklerScanSameTickler() {
	
	  
	  String tempStr;
	  tempStr= checkTicklerApi.check("hallo **2d", null);
	  String newDateStr = checkTicklerApi.check("asdafa asfascas **2d", tempStr);
	  //DateTime time = checkTicklerApi.toDate("11-12-2014");

      DateTime ticklerDateTime = checkTicklerApi.toDate(newDateStr);
      System.out.println("HIER IS IN APP de nieuwe datetime: " + ticklerDateTime);
      
	  
	  DateTime expectedDateTime = DateTime.now().plusDays(2).withTimeAtStartOfDay();
	  assertEquals(expectedDateTime, ticklerDateTime);
}
@Test
public void testCheckTicklerScanSameTicklerDateStrNull() {
	  
	  String tempStr;
	  tempStr= checkTicklerApi.check("hallo **2d", null);
	  String newDateStr = checkTicklerApi.check("asdafa asfascas **2d", null);
	  //DateTime time = checkTicklerApi.toDate("11-12-2014");

      DateTime ticklerDateTime = checkTicklerApi.toDate(newDateStr);
      System.out.println("HIER IS IN APP de nieuwe datetime: " + ticklerDateTime);
      
	  
	  DateTime expectedDateTime = DateTime.now().plusDays(2).withTimeAtStartOfDay();
	  assertEquals(expectedDateTime, ticklerDateTime);
}
@Test
public void testCheckTicklerScanAbsoluteDate() {
	
	  
	  String tempStr;
	  tempStr= checkTicklerApi.check("hallo **2d", null);
	  String newDateStr = checkTicklerApi.check("asdafa asfascas **10-11-2015", tempStr);
	  //DateTime time = checkTicklerApi.toDate("11-12-2014");

      DateTime ticklerDateTime = checkTicklerApi.toDate(newDateStr);
      System.out.println("HIER IS IN APP de nieuwe datetime: " + ticklerDateTime);
      
      DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy");
		DateTime expectedDateTime = formatter.parseDateTime("10-11-2015");
	 
	  assertEquals(expectedDateTime, ticklerDateTime);
}

@Test
public void testCheckTicklerScanSameAbsoluteDate() {
	
	  
	  String tempStr;
	  tempStr= checkTicklerApi.check("hallo **7-8-15", null);
	  String newDateStr = checkTicklerApi.check("asdafa asfascas **8/8/15", tempStr);
	  //DateTime time = checkTicklerApi.toDate("11-12-2014");

      DateTime ticklerDateTime = checkTicklerApi.toDate(newDateStr);
      System.out.println("HIER IS IN APP de nieuwe datetime: " + ticklerDateTime);
      
      DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy");
		DateTime expectedDateTime = formatter.parseDateTime("8-8-2015");
	 
	  assertEquals(expectedDateTime, ticklerDateTime);
}

}

