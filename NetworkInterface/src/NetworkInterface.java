//***********************************************************************
//Alan Decowski
//S.I.T. 2020F
//CPE-490 Final Project
//***********************************************************************
//NetworkInterface.Java


//Creates a way of communicating back and forth between client and server.
//When this Interface is configured with a NULL for its IP, the interface will instantiate as a server.
//All communications are very basic. Server will take in requests from clients and then pass it on immediately to all
//Connected Clients. 

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class NetworkInterface 
{
	
	private String IP;
	private int port;
	public NetworkServer server;
	public NetworkClient client;
	private out D;
	
	private Server hnd;
	public boolean ready = false;
	
	public Client c = null;
	
	
	//Create interface. If ipro is not null, set up as client, otherwise set up as a server.
	public NetworkInterface(int pt, String ipro, NetworkClient nc)
	{
		D = new out("Net");
		D.info("Entering client mode...");
		try {
			Socket temp = new Socket(ipro, pt);
			c = new Client(temp, this);
			client = nc;
			ready = true;
		} catch (Exception e)
		{
			
		}
	}
	public NetworkInterface(int pt, NetworkServer ns)
	{
		D = new out("Net");
		D.info("Entering server mode...");
		port = pt;
		hnd = new Server(pt, this);
		server = ns;
		client = (NetworkClient) ns;
		ready = hnd.ready;
	}
	
	public void postAll(String s)
	{
		if (hnd == null)
		{
			D.warn("You really just tried to postAll from a Client instance? Really? Your funeral.");
			return;
		}
		for (Object j:  hnd.cls.toArray())
		{
			Client k = (Client) j;
			boolean t = k.pushLine(s);
			if (!t)
				hnd.cls.remove((Object) j);
		}
			
	}
	
	public Client getClient()
	{
		return c;
	}
	
}
