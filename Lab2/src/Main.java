import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Downloader {
	private static String destination = "/Users/catarina/Desktop/Pdfer/"; 
	public static void download(String url){
		try{
			System.out.println("Started downloading: " + url);
			UrlReaderClass ur = new UrlReaderClass();
			StringBuilder sb = new StringBuilder();
			sb.append(destination);
			String name = url.substring(url.lastIndexOf('/') + 1);
			sb.append(name);
			ur.readPDF(url, sb.toString());
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

class DownloadExecutors{

	public void run(int nbrOfthreads){
		ExecutorService executorService = Executors.newFixedThreadPool(nbrOfthreads);

		for(int i = 0; i< nbrOfthreads; i++){
			executorService.execute(new DownloaderRunnable());
		}
		executorService.shutdown();

	}
}

public class Main {
	static ArrayList <String>list;
	static int index = -1;

	public Main(){
		list = new ArrayList<String>();
		list.add("http://fileadmin.cs.lth.se/cs/Education/EDA095/2014/lectures/f2-1x1.pdf");
		list.add("http://fileadmin.cs.lth.se/cs/Education/EDA095/2014/lectures/f2-2x2.pdf");
		list.add("http://fileadmin.cs.lth.se/cs/Education/EDA095/2014/lectures/URL_2014-1x1.pdf");
	}

	public static void main(String args[]){
		new Main();
		System.out.print("How many threads?\n");
		Scanner scan = new Scanner(System.in);
		int nbrOfthreads = scan.nextInt();

		//new Thread(new DownloaderRunnable()).start();
		new DownloadExecutors().run(nbrOfthreads);

	}

	public static synchronized String getUrl() {
		index++;
		if(index < list.size()){
			return list.get(index);
		}
		return null;
	}
}
