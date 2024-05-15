import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static final int PORT = 8080;
    private static final String END_CONNECTION = "Client has turned off the connection";
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            while(true) {
                Socket socket = serverSocket.accept(); // принимает подключение
                System.out.println("Client connected");
                Scanner reader = new Scanner(socket.getInputStream());
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                Scanner input = new Scanner(System.in);
                while(input.hasNextLine()) {
                    String line = input.nextLine();
                    writer.println(line);
                }
//                while (reader.hasNextLine()) {
//                    String line = reader.nextLine();
//                    if (line.equals("END")){
//                        System.out.println(END_CONNECTION);
//                        break;
//                    }
//                    System.out.println(line);
//                }
                reader.close();
                writer.close();
                socket.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
