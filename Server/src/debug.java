
public class debug implements NetworkClient, NetworkServer{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NetworkInterface ni = new NetworkInterface(8527, new debug());
	}

	public void clientConnected(Client arg0) {
		// TODO Auto-generated method stub
		
	}

	public void clientDisconnected(Client arg0) {
		// TODO Auto-generated method stub
		
	}

	public void receivedData(Client arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

}
