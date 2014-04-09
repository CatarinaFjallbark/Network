import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UrlDownloader {
	static URL url;


	public UrlDownloader(String text){
		try {
			url = new URL(text);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public URL getURL(){
		return url;
	}
	
	public void decideIfPdf(String urlText){
		String url = urlText;
		Pattern p = Pattern.compile("<a href=(?:\"([^\"]+.pdf)\"|'([^']+.pdf)').*?>");
		Matcher m = p.matcher(url);
		while(m.find()) {
			System.out.println(m.group(1));
		}
	}

	public void deleteHtmlTags(String regexHtml){
		String html = regexHtml;
		Pattern p = Pattern.compile("<a href=(?:\"([^\"]+)\"|'([^']+)').*?>");
		Matcher m = p.matcher(html);
		while(m.find()) {
			System.out.println(m.group(1));
		}

	}


	public static void main(String[] args) {
		System.out.print("What webbsite do you want to read? (Do not forget http://)\n");
		Scanner scan = new Scanner(System.in);
		String webbsite = scan.next();

		try {

			UrlDownloader mainurl = new UrlDownloader(webbsite);

			// get URL content
			url = mainurl.getURL();
			URLConnection conn = url.openConnection();

			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));

			String inputLine;

			//save to this filename
			String fileName = "/Users/catarina/Desktop/test.html";
			File file = new File(fileName);

			if (!file.exists()) {
				file.createNewFile();
			}

			//use FileWriter to write file
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			while ((inputLine = br.readLine()) != null) {
				bw.write(inputLine);
				mainurl.decideIfPdf(inputLine);

			}

			bw.close();
			br.close();

			System.out.println("Done");


		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}


