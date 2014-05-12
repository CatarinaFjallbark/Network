package Telnet2;

import java.net.Socket;

public class ChattClient {
	
	public static void main(String args []){
		
		try {
			Socket s = new Socket("localhost", 30000);
			ClientThreadReceive ctr = new ClientThreadReceive(s);
			ClientThreadSend cts = new ClientThreadSend(s);
			ctr.start();
			cts.start();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
