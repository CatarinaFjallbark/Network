import java.util.Random;


public class WriteThread extends Thread{
	private String name;
	
	public WriteThread(String name){
		this.name=name;
	}
	
	public void run(){
		Random rand = new Random();
		int delay = rand.nextInt(5);
		for(int i=0;i<5;i++){
			System.out.println(name);
			try {
				this.sleep(delay);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	

}
