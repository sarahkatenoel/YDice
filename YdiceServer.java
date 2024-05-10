import java.util.LinkedList;

public class YdiceServer {
        private LinkedList<YdiceServerSocketThread> clientList;
        private int port;
        private YdiceServerListenerThread listenerThread;

        public static void main(String[] args) {
                int port = Integer.parseInt(args[0]);
                YdiceServer ydiceServer = new YdiceServer(port);
        }

        private YdiceServer (int port) {
                this.port = port;
                clientList = new LinkedList<>();
                listenerThread = new YdiceServerListenerThread(port, this);
                listenerThread.start();
        }

        public void addClient(YdiceServerSocketThread socketThread) {
                clientList.add(socketThread);
                System.out.println("adding client to the client list");
        }
}