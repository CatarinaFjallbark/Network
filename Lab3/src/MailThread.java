import java.util.Random;


public class MailThread extends Thread {
	private MailBox mb;

	public MailThread(MailBox mb, String name){
		this.mb = mb;
		this.setName(name);
	}

	public void run() {
		try {
			Random rand = new Random();
			for(int i=0;i<5;i++){
				mb.deposit(this.getName());
				sleep((long)rand.nextInt(1000));

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
