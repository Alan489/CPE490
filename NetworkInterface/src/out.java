import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


//***********************************************************************
//Alan Decowski
//S.I.T. 2020F
//CPE-490 Final Project
//***********************************************************************
//out.Java

//Nonstandard output. Adds time/date and allows the parents object to set an ID to add as a prefix.
//Additionally, allows for marking output lines as INFO, DEBUG, and WARN
//												Level 1,  2,         3
public class out 
{
	private String ident;
	private static LocalDateTime now;
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
	public out(String ID)
	{
		ident = ID;
	}
	public out()
	{
		ident = "NULL";
	}
	private static String dt()
	{
		now = LocalDateTime.now();
		return dtf.format(now);
	}
	public void warn(String arg)
	{
		System.err.println("[WARN]\t" + dt() + "\t" + ident + ":\t" + arg);
	}
	public void info(String arg)
	{
		System.out.println("[INFO]\t" + dt() + "\t" + ident + ":\t" + arg);
	}
	public void debug(String arg)
	{
		System.out.println("[DEBUG]\t" + dt() + "\t" + ident + ":\t" + arg);
	}
}