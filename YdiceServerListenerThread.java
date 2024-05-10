import java.net.ServerSocket;
import java.net.Socket;

public class YdiceServerListenerThread extends Thread {
        private int port;
        private boolean running;
        private YdiceServer ydiceServer;

        public YdiceServerListenerThread(int port, YdiceServer ydiceServer) {
                this.port = port;
                this.ydiceServer = ydiceServer;
        }

        public void run() {
                running = true;
                try {
                        ServerSocket serverSocket = new ServerSocket(this.port);
                        while (running) {
                                Socket socket = serverSocket.accept();
                                YdiceServerSocketThread socketThread = new YdiceServerSocketThread(socket);
                                System.out.println("Socket thread created in listener thread");
                                ydiceServer.addClient(socketThread);
                                System.out.println("Added client to socket thread");
                                socketThread.start();
                        }
                }
                catch (Exception e) {
                        e.printStackTrace();
                }
        }
}