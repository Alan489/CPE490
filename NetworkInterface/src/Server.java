//***********************************************************************
//Alan Decowski
//S.I.T. 2020F
//CPE-490 Final Project
//***********************************************************************
//Server.Java

//The bread and butter of the NetworkInterface. The server, when activated will create an internal thread object to allow
//for constant monitoring of the server socket. 

//My network code is not great- This solution will definitely have a high CPU overhead. 
//This could eventually be fixed, but for now, it will do.

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server 
{
	private ServerSocket ss;
	protected out D = new out ("Serv");
	public boolean ready = false;
	public ArrayList<Client> cls;
	private internalThread iT;
	private NetworkInterface parent;
	
	public Server(String port, NetworkInterface p)
	{
		parent = p;
		int pt = -1;
		try
		{
			pt = Integer.parseInt(port);
			init(pt);
		}
		catch(Exception c)
		{
			D.warn("Unable to parse the string for an int");
		}
	}
	
	public Server(int port, NetworkInterface p)
	{
		parent = p;
		init(port);
	}
	
	private void init(int port)
	{
		D.debug("Attempting to bind to port " + port + "...");
		cls = new ArrayList<>();
		try
		{
			ss = new ServerSocket(port);
			D.debug("Success!");
			D.debug("Setting up Server thread to accept new connections...");
			iT = new internalThread(ss, this);
			iT.start();
			D.debug("Success!");
			ready = true;
		}
		catch(Exception c)
		{
			D.warn("Unable to bind to port. Is something already running on port " + port + "?");
		}
	}
	
	private class internalThread extends Thread
	{
		private ServerSocket ss;
		private Server S;
		private out D;
		public internalThread(ServerSocket A, Server jh)
		{
			S = jh;
			ss = A;
			D = new out("Thread");
		}
		public void run()
		{
			while(true)
			{
				yield();
				try {
					Socket temp = ss.accept();
					Client j = new Client(temp,parent);
					D.info("New client connection from " + temp.getInetAddress().toString());
					if (j.ready)
					{
						S.cls.add(j);
						S.parent.server.clientConnected(j);
					}
					else
						D.warn("Client did not instatiate correctly. Rejecting.");
				} catch (IOException e) {
					D.warn("An error occured attempting to create a Client object.");
				}
			}
		}
	}
}
