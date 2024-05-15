import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 8080;
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        try{

            while(!serverSocket.isClosed()){

                Socket socket = serverSocket.accept();
                System.out.println("A new client has arrived.");
                ClientHandler clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();

            }

        } catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public void closeServerSocket(){
        try{
            if (serverSocket != null){
                serverSocket.close();
            }
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        Server server = new Server(serverSocket);
        server.startServer();
    }
}
