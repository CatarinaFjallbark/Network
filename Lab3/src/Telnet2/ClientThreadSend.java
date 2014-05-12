package Telnet2;

import java.io.OutputStream;
import java.net.Socket;

public class ClientThreadSend extends Thread{
	private Socket s;
	private OutputStream os;

	public ClientThreadSend(Socket s){
		this.s=s;
		try{
			os = s.getOutputStream();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void run(){
		int b;
		while (true) {
			try {
				String s = "";
				b = System.in.read();
				while(b != -1 && b != '\n'){
					s += (char) b;
					b = System.in.read();
				}
				os.write(s.getBytes());
				os.write('\n');
				os.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}


