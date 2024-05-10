
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class YdiceServerSocketThread extends Thread {

    private Socket socket;
    private Random rng;

    public YdiceServerSocketThread(Socket socket) {
        this.socket = socket;
        this.rng = new Random();
    }

    public void run() {
    	try {
	    	InputStream inputStream = socket.getInputStream();
	    	OutputStream outputStream = socket.getOutputStream();
	    	PrintWriter printWriter = new PrintWriter(outputStream);

	    	int firstDie = rng.nextInt(6) + 1;
	    	int secondDie = rng.nextInt(6) + 1;
	    	int thirdDie = rng.nextInt(6) + 1;
	    	int fourthDie = rng.nextInt(6) + 1;
	    	int fifthDie = rng.nextInt(6) + 1;

	    	System.out.printf("%d %d %d %d %d%n", firstDie, secondDie, thirdDie, fourthDie, fifthDie);

	    	printWriter.printf("%d %d %d %d %d %d%n", YahtzeePacket.PLAYER_DICE, firstDie, secondDie, thirdDie, fourthDie, fifthDie);
   		} catch (Exception e) {
   			e.printStackTrace();
   		}
    }
}