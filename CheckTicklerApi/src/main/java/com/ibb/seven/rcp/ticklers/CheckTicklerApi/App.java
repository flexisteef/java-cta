package com.ibb.seven.rcp.ticklers.CheckTicklerApi;

import org.joda.time.DateTime;


public class App 
{
    public static void main( String[] args )
    {
    	System.getProperty("sun.locale.formatasdefault", "true");
        CheckTicklerApi checktickler = new CheckTicklerApi();
        String tempDate = checktickler.check("hallo **1d", null);
        System.out.println("TEST: " + tempDate);
        String newDateStr = checktickler.check("asdafa asfascas **1d ", tempDate);
        System.out.println("HIER IS IN APP de nieuwe datetime in string: " + newDateStr);
        if(newDateStr != null)
        {
        DateTime ticklerDateTime = checktickler.toDate(newDateStr);
        System.out.println("HIER IS IN APP de nieuwe datetime: " + ticklerDateTime);
        }
    }
}
