import java.io.File;
import java.util.Scanner;
//***********************************************************************
//Alan Decowski
//S.I.T. 2020F
//CPE-490 Final Project
//***********************************************************************
//Main.Java

//Main thread for server. 
//Technically NetworkInterface.jar is required, but other than that the only 2 classes needed for the server to run
//is Main.java and DataModel.java

public class Main implements NetworkClient, NetworkServer{

	public static void main(String[] args) {
		new Main();
	}
	public static Scanner in = new Scanner(System.in);
	public static out D = new out("Main");
	File f;
	DataModel dm;
	NetworkInterface ni;
	public Main()
	{
		
		D.info("\nAlan Decowski // CPE-490 // Final Project\n2020 Fall - Offline multi-person document editor");
		pause(500);
		D.info("Server-Side");
		pause(1000);
		D.warn("Terminal interface only.");
		pause(1000);
		
		String port;
		int pt = -1;
		
		boolean selected = false;
		
		do
		{
			pause(500);
			D.info("Please enter the folder path you would like to use. You must have read/write access to the folder.");
			System.out.print("> ");
			port = in.nextLine();
			f = new File(port);
			if (f.exists())
			{
				if (!(f.canRead() && f.canWrite()))
				{
					D.warn("No Read/Write access. Please try again.");
				}
				else
				{
					if (f.isDirectory())
						selected = true;
					else
						D.warn("Please input a directory.");
				}
			}
			else
			{
				D.info("Folder does not exist. Would you like to create a new Folder? [y/n]");
				System.out.print(">");
				String response = in.nextLine();
				if (response.equalsIgnoreCase("y"))
				{
					try {
						selected = f.mkdir();
					} catch (Exception e) {
					}
					if (!selected)
						D.warn("An error occured attempting to create the folder. Check if you have read/write access to the parent directory.");
				}
				
			}
		
		}while(!selected);
		
		
		//Okay, File has been selected. Now we have to set up a FIOS object to create an interface for the file we want to edit with.
		
		//FIOS fi = new FIOS(f);
		//fi.getAllLines();
		
		dm = new DataModel(f);
		if (!dm.ready)
		{
			D.warn("Failed to create DataModel object. Unable to continue.");
			System.exit(-1);
		}
		
		do
		{
			D.info("Please enter the port number you wish to bind to. (0-65353)");
			System.out.print("> ");
			port = in.nextLine();
			try
			{
				pt = -1;
				pt = Integer.parseInt(port);
			}
			catch(Exception c)
			{
				D.warn("Please enter a valid number.");
			}
			
		}
		while(pt == -1);
		
		D.info("Okay! Will attempt to start server on 127.0.0.1:"+pt);
		ni = new NetworkInterface(pt, this);
		if (!ni.ready)
		{
			D.warn("NetworkInterface did not report a ready state at the conclusion of creation. Please view log for error.");
			System.exit(-1);
		}
		D.info("Successfully created Server.");
		
		//Okay, server has been created. Now we are able to handle requests, no additional calls from Main thread.
		
		D.info("Ready to service clients! ");
		
		in.close();
	}

	public static void pause(long ms)
	{
		long end = System.currentTimeMillis() + ms;
		while (System.currentTimeMillis() < end);
	}

	public void clientConnected(Client arg0) {
		arg0.pushLine(dm.toString());
	}

	public void clientDisconnected(Client arg0) {
		// TODO Auto-generated method stub
		
	}

	public void receivedData(Client arg0, String arg1) {
		ni.postAll(arg1);
		String[] tt = arg1.split("/");
		if (tt.length %3 != 0)
		{
			D.warn("Bad data received from client.");
			return;
		}
		for (int i = 0; i < tt.length; i+=3)
		{
			dm.update(tt[i].charAt(0), Integer.parseInt(tt[i+1]), Integer.parseInt(tt[i+2]));
		}
		dm.write();
		
	}
	
}
