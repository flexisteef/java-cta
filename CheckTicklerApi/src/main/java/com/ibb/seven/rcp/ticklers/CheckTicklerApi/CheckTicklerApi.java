package com.ibb.seven.rcp.ticklers.CheckTicklerApi;

import java.util.Scanner;

import com.ibb.seven.rcp.ticklers.TicklerScanApi.*;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.eclipse.jface.dialogs.MessageDialog;
//import java.awt.*; 
//import javax.swing.*;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Shell;




public class CheckTicklerApi
	{
		private String	RegexTickler		= "\\*\\*(([0-99])+([dD]|[wW]|[mM]|[yY])(([0-99]){1,}([wW]|[mM]|[yY]))?(([0-99]){1,}([mM]|[yY]))?(([0-99]){1,}[yY])?)";
		private String	RegexAbsoluteDate	= "(\\*\\*[0-99]{1,}([-]|[\\/])[0-99]{1,}([-]|[\\/])\\d{2,4})";
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
								
								Shell shell = new Shell();
								// ask user to change tickler date.
								MessageDialog dialog = new MessageDialog(shell, "Warning!", null,
									    "Do you want to change the ticklerdate?", MessageDialog.QUESTION, new String[] { "Yes",
									  "No"}, 0);
									int result = dialog.open();
									System.out.println(result); 
								if(result == 0)
								{
								newDate = updateTickler.getTicklerDate(note);
								}
								else
								{
									newDate = updateTickler.getTicklerDate("**0d");	
								}
							
							
								
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
				if(getAbsoluteDateStr() != null)
					{
				return (dateStr != null) ? newDate.toString("dd-MM-yyyy") : getAbsoluteDateStr();
					}
				else 
					{
						return (dateStr != null) ? newDate.toString("dd-MM-yyyy") : getTicklerScanned();		
					}
				
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
						i++;
					}		
			}
		public DateTime toDate(String date)
			{
			if(date != null)
			{
			if(date.matches(RegexTickler))
			{
				TicklerScanApi updateTickler = new TicklerScanApi();
				DateTime alternativeDate = updateTickler.getTicklerDate(date); 
				return alternativeDate;
			}
				DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy");
				DateTime ticklerDateTime = formatter.parseDateTime(date);		
				return ticklerDateTime;
			}
			return null;
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
