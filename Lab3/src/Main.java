
public class Main {

	public static void main(String args[]){
		MailBox mb = new MailBox();
		ClearThread ct = new ClearThread(mb);
		
		MailThread [] vect = new MailThread [10];
		for(int i= 0; i<10; i++){
			vect[i] = new MailThread(mb, "Thread " + i);			
		}
		ct.start();
		for(int i = 0; i<10; i++){
			vect[i].start();
		}
	}
}
