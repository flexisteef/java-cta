package com.ibb.seven.rcp.ticklers.CheckTicklerApi;

import org.joda.time.DateTime;


public class App 
{
    public static void main( String[] args )
    {
    	System.getProperty("sun.locale.formatasdefault", "true");
        CheckTicklerApi checktickler = new CheckTicklerApi();
        String tempDate = checktickler.check("asdafa asfascas **08-07-2014 ASd", null);
        String newDateStr = checktickler.check("asdafa asfascas **10-17-2015", tempDate);
        System.out.println("HIER IS IN APP de nieuwe datetime: " + newDateStr);
        DateTime ticklerDateTime = checktickler.toDate(newDateStr);
        System.out.println("HIER IS IN APP de nieuwe datetime: " + ticklerDateTime);
    }
}
