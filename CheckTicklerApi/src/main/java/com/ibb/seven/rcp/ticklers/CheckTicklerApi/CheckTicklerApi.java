package com.ibb.seven.rcp.ticklers.CheckTicklerApi;

import java.util.Scanner;
import com.ibb.seven.rcp.ticklers.TicklerScanApi.*;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
//import java.awt.*; 
//import javax.swing.*;



public class CheckTicklerApi
	{
		private String	RegexTickler		= "\\*\\*(([0-99])+([dD]|[wW]|[mM]|[yY])(([0-99]){1,}([wW]|[mM]|[yY]))?(([0-99]){1,}([mM]|[yY]))?(([0-99]){1,}[yY])?)";
		private String	RegexAbsoluteDate	= "(\\*\\*[0-99]{1,}([-]|[\\/])[0-99]{1,}([-]|[\\/])\\d{4})";
		private String	TicklerScanned, AbsoluteDateStr = null;
		
		private DateTime	newDate;

		public String check(String note, String dateStr)
			{
				Logger logger = (Logger) LoggerFactory.getLogger("com.ibb.seven.rcp.ticklers.CheckTicklerApi.check");
				if (dateStr != null)
					{
						TicklerScanApi updateTickler = new TicklerScanApi();
						scanNote(note);
						if(getAbsoluteDateStr() == null)
							{
						if (dateStr.equals(getTicklerScanned()))
							{
								logger.info("Do you want to change the ticklerdate?\n");
								
								newDate = updateTickler.getTicklerDate(note);
								// ask user to change tickler date.
							}
						else
							{
								logger.info("Changed the ticklerdate\n");
								newDate =  updateTickler.getTicklerDate(note);
							}
							}
						else
							{
								logger.info("Changed the absolutedate\n");
								newDate =  updateTickler.getTicklerDate(note);
							}
						return newDate.toString("dd-MM-yyyy");
					}
				else
					{
						scanNote(note);
					}
				return (dateStr != null) ? newDate.toString("dd-MM-yyyy") : getAbsoluteDateStr(); 
			}
		private void scanNote(String Note)
			{
				Logger logger = (Logger) LoggerFactory.getLogger("com.ibb.seven.rcp.ticklers.CheckTicklerApi.scanNote");
				String result = null;
				String currentLine = null;
				String[] regexArray =
					{ RegexTickler, RegexAbsoluteDate };
				for (int i = 0; i < regexArray.length;)
					{
						Scanner sc = new Scanner(Note);
						while (sc.hasNext())
							{
								/*
								 * check if currentline meets the requirements for the
								 * regular expression
								 */
								currentLine = sc.next();
								if (currentLine.matches(regexArray[i]))
									{
										result = currentLine;
									}
							}
						i++;
						sc.close();
						if (result != null)
							{
								if (i == 0)
									{
										setTicklerScanned(result);
										logger.debug("Tickler is: {}",getTicklerScanned());
										result = null;
									}
								else
									{
										setAbsoluteDateStr(result);
										logger.debug("absolutedate is: {}", getAbsoluteDateStr());
										result = null;
									}
							}
					}		
			}
		public DateTime toDate(String date)
			{
				DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy");
				DateTime ticklerDateTime = formatter.parseDateTime(date);		
				return ticklerDateTime;
			}

		public String getAbsoluteDateStr()
			{
				return AbsoluteDateStr;
			}

		public void setAbsoluteDateStr(String absoluteDateStr)
			{
				AbsoluteDateStr = absoluteDateStr;
			}

		public String getTicklerScanned()
			{
				return TicklerScanned;
			}

		public void setTicklerScanned(String ticklerScanned)
			{
				TicklerScanned = ticklerScanned;
			}
	}
