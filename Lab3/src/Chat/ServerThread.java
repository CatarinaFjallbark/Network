package Chat;

import java.util.Vector;

public class ServerThread extends Thread{

	private ServerBox sb;
	private char command;
	private Vector<Chatter> vector;

	public ServerThread(ServerBox sb){
		this.sb=sb;
		vector = sb.getVect();
	}

	public void run(){
		while(true){
			String s = sb.withdraw();
			Chatter c = sb.getChatter();
			System.out.println(c);
			if(s!=null){
				command = s.charAt(0);
				String[] split = s.split(":");
				switch(command){
				case 'M':
					for(Chatter chatter: vector){
						chatter.outType(split[1] + "\n");
					}
					break;
				case 'E':
					System.out.println("echo");
					c.outType(split[1] + "\n");
					break;
				case 'Q':
					System.out.println("quit");
					c.quit();
					vector.remove(c);
					c.stop();
					break;
				default:
					c.outType("The command is wrong, type M:, E: or Q. M for sending to all participants. E for echoing a message. Q for ending the chat");
					break;
				} 
			}
		}
	}
}
