
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


public class UrlDownloader {


	public static void main(String[] args) {
		System.out.print("What webbsite do you want to read? (Do not forget http://)\n");
		Scanner scan = new Scanner(System.in);
		String webbsite = scan.next();

		try {				
			UrlReader r = new UrlReader(new URL(webbsite), "/Users/catarina/Desktop/test.html/");
			r.read();
			
			String destination = "/Users/catarina/Desktop/Pdfer/";
			ArrayList <String> pdfs = new ArrayList<String>();
			pdfs = r.getPdfs();

			for(int i=0; i<pdfs.size(); i++){
				StringBuilder s = new StringBuilder();
				s.append(destination);
				s.append(String.valueOf(i+1));
				s.append(".pdf");
				r.readPdf(pdfs.get(i), s.toString());
				//UrlReader p = new UrlReader(new URL(pdfs.get(i)), s.toString());
				//p.read();
			}
			System.out.print("\n"+ pdfs.size());


		} catch (MalformedURLException e) {
			e.printStackTrace();
		} 


	}
}


