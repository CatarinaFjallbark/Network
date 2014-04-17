import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Downloader {
	public static void download(String url){
		System.out.println("Started downloading: " + url);
		Random random = new Random();
		try {
			Thread.sleep(2000 + random.nextInt(5000));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Finished downloading: " + url);
	}
}

class DownloaderThread extends Thread {
	private String url;

	public DownloaderThread(String url){
		this.url = url;
	}

	public void run(){
		Downloader.download(url);
	}
}

class DownloaderThreadList extends Thread{
	public void run(){
		String url = Main.getUrl();
		if(url!=null){
			Downloader.download(url);
		}else{
			System.out.print("There are more threads than urls");
		}	}
}

class DownloaderRunnable implements Runnable{

	public DownloaderRunnable(){
	}

	@Override
	public void run() {
		String url = Main.getUrl();
		if(url!=null){
			Downloader.download(url);
		}else{
			System.out.print("\nThere are more threads than urls\n\n");
		}

	}

}

public class Main {
	static ArrayList <String>list;
	static int index = -1;

	public Main(){
		list = new ArrayList<String>();
		list.add("www.facebook.se");
		list.add("www.instagram.se");
		list.add("www.google.se");
	}

	public static void main(String args[]){
		new Main();
		System.out.print("How many threads?\n");
		Scanner scan = new Scanner(System.in);
		int nbrOfthreads = scan.nextInt();

		for(int i = 0; i<nbrOfthreads; i++){
			new Thread(new DownloaderRunnable()).start();
		}
	}
	
	public static synchronized String getUrl() {
		index++;
		if(index < list.size()){
		return list.get(index);
		}
		return null;
	}
}
