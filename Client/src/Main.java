import java.util.Scanner;

//***********************************************************************
//Alan Decowski
//S.I.T. 2020F
//CPE-490 Final Project
//***********************************************************************
//Main.Java

//Main thread for client.
public class Main implements NetworkClient
{
	static char[] j = new char[2];
	static NetworkInterface ni;
	static Scanner in = new Scanner(System.in);
	static out D = new out("Main");
	DataModel dm = new DataModel();
	public static void main(String[] args) {
		D.info("Starting...");
		D.info("No Command-Line Interaction");
		new Main();
	}
	public Main()
	{
		DataModel dd = new DataModel();
		dd.update('J', 5, 5);
		DataModel dd1 = new DataModel();
		dd1.update('A', 6, 6);
		System.out.println(dd.getDiff(dd1));
		new ChooseNetwork(this).frame.setVisible(true);; //This will bring back into connect(String, String, ChooseNetwork)
	}
	
	public void connect(String IP, String port, ChooseNetwork cn)
	{
		cn.frame.dispose();
		ni = new NetworkInterface(Integer.parseInt(port), IP, this);
		if (!ni.ready)
		{
			new err().frame.setVisible(true);
			return;
		}
		pause(100);
		ni.c.pushLine("B"+(char)207+"0"+(char)207+"0"+(char)207+"N"+(char)207+"1"+(char)207+"1"+(char)207);
	}
	
	public static void pause(long ms)
	{
		long end = System.currentTimeMillis() + ms;
		while (System.currentTimeMillis() < end);
	}
	
	public void receivedData(Client arg0, String arg1) {
		String[] tt = arg1.split(""+ (char)207);
		if (tt.length %3 != 0)
		{
			D.warn("Bad data received from server.");
			return;
		}
		for (int i = 0; i < tt.length; i+=3)
		{
			dm.update(tt[i].charAt(0), Integer.parseInt(tt[i+1]), Integer.parseInt(tt[i+2]));
		}
	}

}
