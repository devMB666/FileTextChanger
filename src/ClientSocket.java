import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket {
    private static final int PORT = 8080;

    public static void main(String[] args){
        ReaderThread readerThread = new ReaderThread();
        readerThread.start();
//        try {
//            Socket socket = new Socket(InetAddress.getLocalHost(), PORT);
//            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//            Scanner reader = new Scanner(socket.getInputStream());
//            Scanner writer = new Scanner(System.in);
//
//            while (reader.hasNextLine()){
//                String line = reader.nextLine();
//                System.out.println(line);
//            }
//
//            while (writer.hasNextLine()) {
//                String message = writer.nextLine();
//                if (message.equals("END")) break;
//                else out.println(message);
//            }
//
//            writer.close();
//            out.close();
//            socket.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    public static class WriterThread extends Thread{
        Socket socket;
        PrintWriter out;
        Scanner writer = new Scanner(System.in);
        
        {
            try {
                socket = new Socket(InetAddress.getLocalHost(), PORT);
                out = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void run() {

        }

        public void write(String msg){

        }
    }

    public static class ReaderThread extends Thread{
        private final Scanner reader;
        {
            try{
                Socket socket = new Socket(InetAddress.getLocalHost(), PORT);
                reader = new Scanner(socket.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void run() {
            readSocket(reader);
        }

        public void readSocket(Scanner reader){
            while(reader.hasNextLine()){
                System.out.println(reader.nextLine());
            }
        }

    }
}
