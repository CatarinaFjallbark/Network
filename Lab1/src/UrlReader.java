import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlReader {
	private URL url;
	private String fileName;
	private ArrayList<String> urls;

	public UrlReader(URL url, String fileName) {
		urls = new ArrayList<String>();
		this.url = url;
		this.fileName = fileName;
	}

	public ArrayList<String> getPdfs() {
		return urls;
	}

	public void decideIfPdf(String urlText) {
		String url = urlText;
		Pattern p = Pattern
				.compile("<a href=(?:\"([^\"]+.pdf)\"|'([^']+.pdf)').*?>");
		Matcher m = p.matcher(url);
		while (m.find()) {
			System.out.println(m.group(1));
			urls.add(m.group(1));
		}
	}


	public void readPdf(String link, String destination){
		try {
			URL url = new URL(link);
			URLConnection conn;
			conn = url.openConnection();

			// open the stream and put it into BufferedReader
			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(in);
			File file = new File(destination);
			if (!file.exists()) {
				file.createNewFile();
			}

			//use FileWriter to write file
			FileWriter filew = new FileWriter(file.getAbsoluteFile());
			BufferedWriter buffer = new BufferedWriter(filew);

			/*		char[] skopa = new char [1024];
			int result;
			while((result = br.read(skopa)) >= 0) {
				buffer.write(skopa, 0, result);
			}*/

			char[] input = new char [1024];
			int bytesRead = 0;
			int bytesToRead = input.length;

			while(bytesRead<bytesToRead){
				int results = in.read(input, bytesRead, bytesToRead-bytesRead);
				if(results == -1){
					break;
				}
				bytesRead += results;
				buffer.write(input);
				/*			String inputLine;

			while ((inputLine = br.readLine()) != null) {
				buffer.write(inputLine);

			}
				 */
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public void read(){
		try{
			// get URL content
			URLConnection conn = url.openConnection();

			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));

			String inputLine;

			//save to this filename

			File file = new File(fileName);

			if (!file.exists()) {
				file.createNewFile();
			}

			//use FileWriter to write file
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			/*			
			char[] skopa = new char [1024];
			int result;
			while((result = br.read(skopa)) != -1) {
				bw.write(skopa, 0, result);
				decideIfPdf(skopa);

			}*/

			/*
			char[] input = new char [10000];
			int bytesRead = 0;
			int bytesToRead = input.length;

			while(bytesRead<bytesToRead){
				int results = br.read(input, bytesRead, bytesToRead-bytesRead);
				if(results == -1){
					break;
				}
				bytesRead += results;
				bw.write(input);

			}
			 */

			while ((inputLine = br.readLine()) != null) {
				bw.write(inputLine);
				decideIfPdf(inputLine);
			}

			bw.close();
			br.close();


		}catch(IOException e){

		}

	}

}
