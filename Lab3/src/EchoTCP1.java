import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class EchoTCP1 {

	
	public static void main(String args[]){
		System.out.println("Bšrjar");
		try {
			ServerSocket server = new ServerSocket();
		    server.bind(new java.net.InetSocketAddress(30001));
		    while(true){
		    	Socket s = server.accept();
		    	System.out.println(s.getInetAddress());
		    	InputStream is= s.getInputStream();
		    	OutputStream os = s.getOutputStream();
		    	int lenght = -1;
				byte[]buffer = new byte[1024];
				while((lenght = is.read(buffer))>-1){
					os.write(buffer, 0, lenght);
				}
				os.close();
				is.close();		    	
		    }
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Slutar");

	}

}
