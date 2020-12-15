import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

//***********************************************************************
//Alan Decowski
//S.I.T. 2020F
//CPE-490 Final Project
//***********************************************************************
//debug1.Java

//A class to test the NetworkInterface :)


public class debug1 implements NetworkClient
{
	public static void main(String[] args) throws UnknownHostException, IOException
	{
		NetworkInterface ni = new NetworkInterface(8527, "127.0.0.1", new debug1());
		Client j = ni.getClient();
		System.out.println(j.pushLine("TEsting!"));
	}

	@Override
	public void receivedData(Client j, String s) {
		// TODO Auto-generated method stub
		System.out.println(s);
	}
}
