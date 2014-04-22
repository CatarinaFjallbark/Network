import java.util.Random;


public class ClearThread extends Thread{
	private MailBox mb;
	
	public ClearThread(MailBox mb){
		this.mb=mb;
	}
	
	public void run(){
		while(true){
			String temp =  mb.withdraw();
			if(temp!=null){
				System.out.println(temp);	
			}
			Random rand = new Random();
			try {
				sleep((long) rand.nextInt(1));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
