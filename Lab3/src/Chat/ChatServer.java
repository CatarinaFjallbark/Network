package Chat;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChatServer {
	
	public static void main(String[] args) {
		int port = 30000;
		System.out.println("Server started");
		Vector<Chatter> vector = new Vector<Chatter>();
		ServerBox box = new ServerBox(vector);
		try {
			ServerSocket ss = new ServerSocket(port);
			ServerThread st = new ServerThread(box);
			st.start();
			while (true) {
				try {
					Socket s = ss.accept();
					Chatter c = new Chatter(s,box);
					vector.add(c);
					c.start();
					System.out.println("client conenct:" + s.getInetAddress().toString());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

}
