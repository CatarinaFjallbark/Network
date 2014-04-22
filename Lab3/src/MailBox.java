
public class MailBox {
	
	private String instance = null;
	
	public MailBox() {

	}
	
	public synchronized String withdraw() {
		while (instance == null) {
			try {
				this.wait();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		String s = instance;
		instance = null;
		this.notifyAll();
		return s;
	}


	public synchronized void deposit(String s){
		try {
			while(instance!=null){
				this.wait();
			}
			instance=s;
			notifyAll();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}




}
