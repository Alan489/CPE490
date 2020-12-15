import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

//***********************************************************************
//Alan Decowski
//S.I.T. 2020F
//CPE-490 Final Project
//***********************************************************************
//debug.Java

//A class to test the NetworkInterface :)


public class debug implements NetworkServer, NetworkClient
{
	static NetworkInterface ni;
	public static void main(String[] args) throws UnknownHostException, IOException
	{
		ni = new NetworkInterface(8527, new debug());
	}

	@Override
	public void clientConnected(Client j) {
		System.out.println("Connected!!!!!!!");
	}

	@Override
	public void clientDisconnected(Client j) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receivedData(Client j, String s) {
		System.out.println(s);
		if (s.equals("TEsting!"))
		{
			ni.postAll("Got it!");
		}
	}
}
