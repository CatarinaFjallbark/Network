import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
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

	public ArrayList<String> getPDF() {
		return urls;
	}

	public void handlePDF(String urlText) {
		String url = urlText;
		Pattern p = Pattern
				.compile("<a href=(?:\"([^\"]+.pdf)\"|'([^']+.pdf)').*?>");
		Matcher m = p.matcher(url);
		while (m.find()) {
			System.out.println(m.group(1));
			urls.add(m.group(1));
		}
	}


	public void readPDF(String link, String destination){
		try {
			URL url = new URL(link);

			InputStream is = url.openStream();

			File file = new File(destination);
			if (!file.exists()) {
				file.createNewFile();
			}

			FileOutputStream fos = new FileOutputStream(file);

			System.out.println("Reading file..");
			int lenght = -1;
			byte[]buffer = new byte[1024];
			while((lenght = is.read(buffer))>-1){
				fos.write(buffer, 0, lenght);
			}
			fos.close();
			is.close();
			System.out.println("Download complete");


		} catch (Exception e) { // TODO Auto-generated catch block
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

			while ((inputLine = br.readLine()) != null) {
				bw.write(inputLine);
				handlePDF(inputLine);
			}

			bw.close();
			br.close();


		}catch(IOException e){

		}

	}

}
