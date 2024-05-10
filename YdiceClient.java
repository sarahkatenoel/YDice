import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import java.util.Scanner;

public class YdiceClient {
        private Socket socket;
        private InetAddress serverAddress;
        private int serverPort;

        public static void main(String[] args) throws IOException, UnknownHostException {
                InetAddress serverAddress = InetAddress.getByName(args[0]);
                int serverPort = Integer.parseInt(args[1]);
                YdiceClient ydiceClient = new YdiceClient(serverAddress, serverPort);
                ydiceClient.connectClient();
        }

        private YdiceClient(InetAddress serverAddress, int serverPort) {
                this.serverAddress = serverAddress;
                this.serverPort = serverPort;
        }

        private void connectClient() throws IOException {
                socket = new Socket(serverAddress, serverPort);
                
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                Scanner scanner = new Scanner(inputStream);
                PrintWriter printWriter = new PrintWriter(outputStream);

                while (socket.isConnected()) {
                        int packetID = scanner.nextInt();
                        if (packetID == YdicePacket.PLAYER_DICE) {
                                System.out.println("You rolled:");
                                System.out.printf("%d %d %d %d %d%n", scanner.nextInt(),
                                        scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
                        }
                }
        }
}