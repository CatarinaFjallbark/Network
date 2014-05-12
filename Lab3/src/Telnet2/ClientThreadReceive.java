package Telnet2;

import java.io.InputStream;
import java.net.Socket;

public class ClientThreadReceive extends Thread{
	private Socket s;
	private InputStream is;

	public ClientThreadReceive(Socket s){
		this.s=s;
		try{
			is = s.getInputStream();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void run(){
		int b;
		while (true) {
			try {
				String s = "";
				b = is.read();
				while (b != -1 && b != '\n') {
					s += (char) b;
					b = is.read();
				}
				System.out.println(s);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
