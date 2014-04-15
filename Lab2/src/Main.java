import java.util.Random;

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
		Downloader.download(Main.getUrl());
	}
}

public class Main {
	static String [] list = {"www.facebook.se", "www.instagram.se", "www.google.se"};
	static int nbr = -1;
	
	public static void main(String args[]){
		
		DownloaderThreadList t1 = new DownloaderThreadList();
		DownloaderThreadList t2 = new DownloaderThreadList();
		DownloaderThreadList t3 = new DownloaderThreadList();

		t1.start();
		t2.start();
		t3.start();
		
	}

	public static synchronized String  getUrl() {
		nbr++;
		return list[nbr];
	}
}
