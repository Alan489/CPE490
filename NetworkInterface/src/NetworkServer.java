//***********************************************************************
//Alan Decowski
//S.I.T. 2020F
//CPE-490 Final Project
//***********************************************************************
//NetworkServer.Java

//Network handler for server.
//When NI is instantiated as a Server, before it begins listening, it will require
//A object to be passed to it that implements the NetworkClient interface.
//This is where it will inform the server thread that a client has data that was put onto the wire.

interface NetworkServer {
	
	public void clientConnected(Client j);
	public void clientDisconnected(Client j);
	public void receivedData(Client j, String s);
}