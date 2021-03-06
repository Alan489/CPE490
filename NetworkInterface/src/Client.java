import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
//***********************************************************************
//Alan Decowski
//S.I.T. 2020F
//CPE-490 Final Project
//***********************************************************************
//Client.Java

//Client class for a server to communicate with clients.

public class Client 
{
	private PrintWriter out;
	private BufferedReader in;
	private Socket Sock;
	protected boolean ready = false;
	public boolean disconnected = false;
	protected static out D = new out("ClientX");
	public internalThread iT;
	public NetworkInterface handler;
	
	public Client(Socket s, NetworkInterface hnd)
	{
		handler = hnd;
		Sock = s;
		try
		{
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(s.getOutputStream(), true);
			iT = new internalThread(in, this);
			iT.start();
			ready = true;
		}
		catch(Exception c)
		{
			D.warn("An error occured that prevented a client for instatiating. Unable to process this thread.");
		}
	}
	
	public String getLine()
	{
		if (!ready)
		{
			D.warn("Attempted to read a line from a Client without having a \"Ready\" status");
			return null;
		}
		try {
			String j = in.readLine();
			return j;
		} catch (IOException e) {
			D.warn("An error occured trying to read a line.");
		}
		return null;
	}
	
	public boolean pushLine(String d)
	{
		if (!ready)
		{
			D.warn("Attempted to push a line to a Client without having a \"Ready\" status");
			return false;
		}
		try {
			out.println(d);
			out.flush();
			return true;
		}
		catch(Exception c)
		{
			D.warn("An error occured attempting to push a line to a client.");
		}
		
		return false;
	}
	
	class internalThread extends Thread
	{
		private BufferedReader br;
		public Client parent = null;
		private int errorCount = 0;
		public internalThread(BufferedReader b, Client p)
		{
			br = b;
			parent = p;
		}
		
		public void run()
		{
			while(true)
			{
				if (parent.Sock.isClosed())
				{
					break;
				}
				try {
					parent.handler.client.receivedData(parent, br.readLine());
					
				} catch (IOException e) {
					Client.D.warn("internalThread: Unable to pull data off the line?");
					errorCount++;
					if(errorCount >= 10)
					{
						Client.D.info("A client has disconnected.");
						parent.disconnected = true;
						parent.ready = false;
						try {
							if (parent.handler.server != null)
								parent.handler.server.clientDisconnected(parent);
							Client.D.debug("Attempting to close Socket...");
							parent.Sock.close();
							Client.D.debug("Success!");
							Client.D.debug("Attempting to close PW...");
							parent.out.close();
							Client.D.debug("Success!");
							Client.D.debug("Attempting to close BR...");
							parent.in.close();
							Client.D.debug("Success!");
						} catch (IOException e1) {
							Client.D.debug("Fail!");
						}
					}
				}
			}
		}
		
	}
	
}
