package Chat;

import java.util.Vector;

public class ServerBox {
	
	private String instance = null;
	private Vector <Chatter> vect;
	private Chatter c;

	
	public ServerBox(Vector<Chatter> vect){
		this.vect=vect;
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


	public synchronized void deposit(String s, Chatter c){
		try {
			while(instance!=null){
				this.wait();
			}
			instance=s;
			this.c=c;
			notifyAll();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	public Vector<Chatter> getVect(){
		return vect;
	}
	
	public Chatter getChatter(){
		return c;
	}

}
