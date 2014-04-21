import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class EchoThread extends Thread{
	private Socket s;

	public EchoThread(Socket s){
		this.s=s;
	}

	public void drain(Socket in){
		try{
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			int lenght = -1;
			byte[]buffer = new byte[1024];
			while((lenght = is.read(buffer))>-1){
				os.write(buffer, 0, lenght);
			}
			os.close();
			is.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run(){
		drain(s);
	}
}

class EchoExecutors{
	private Socket s;
	
	public EchoExecutors(Socket s){
		this.s=s;
	}
	
	public void run(){
		int nbrOfExecutors = 3;
		ExecutorService executorService = Executors.newFixedThreadPool(nbrOfExecutors);
		for(int i = 0; i<nbrOfExecutors; i++){
			executorService.execute(new EchoRunnable(s));
		}
	}

}

class EchoRunnable implements Runnable{
	private Socket s;

	public EchoRunnable(Socket s){
		this.s=s;
	}

	public void drain(Socket in){
		try{
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			int lenght = -1;
			byte[]buffer = new byte[1024];
			while((lenght = is.read(buffer))>-1){
				os.write(buffer, 0, lenght);
			}
			os.close();
			is.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		drain(s);
	}

}
public class EchoTCP2 {

	public static void main(String args[]){
		try {
			ServerSocket ss = new ServerSocket();
			ss.bind(new java.net.InetSocketAddress(33007));
			while(true){
				Socket s = ss.accept();
				//new Thread(new EchoRunnable(s)).start();
				new EchoExecutors(s).run();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
