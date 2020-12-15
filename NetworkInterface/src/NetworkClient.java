//***********************************************************************
//Alan Decowski
//S.I.T. 2020F
//CPE-490 Final Project
//***********************************************************************
//NetworkClient.Java

//Network handler for Client.
//When NI is instantiated as a Client, before it begins listening, it will require
//A object to be passed to it that implements the NetworkClient interface.
//This is where it will inform the client thread that the server has data that was put onto the wire.

interface NetworkClient {
	
	public void receivedData(Client j, String s);
	

}
