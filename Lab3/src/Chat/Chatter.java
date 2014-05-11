package Chat;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Chatter extends Thread{
	private InputStream is;
	private OutputStream os;
	private Socket s;
	private ServerBox sb;

	public Chatter(Socket s, ServerBox sb){

		try {
			this.s = s;
			this.sb = sb;	
			is = s.getInputStream();
			os = s.getOutputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void drain(Socket in){
		
		try{
			String s = "";
			int b = is.read();
			while(b !=-1 && b != '\n'){
				s += (char) b;
				b = is.read();
			}
			sb.deposit(s, this);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(true){
			drain(s);			
		}
	}
	
	public void quit(){
		try {
			s.close();
			os.close();
			is.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void outType(String message){
		try {
			os.write(message.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}