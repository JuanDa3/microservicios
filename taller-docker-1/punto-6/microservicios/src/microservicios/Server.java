package microservicios;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    //static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 9876;

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        //create the socket server object
        server = new ServerSocket(port);
        boolean i = true;
        String nombre = "";
        //keep listens indefinitely until receives 'exit' call or program terminates
        while (i) {
            System.out.println("Waiting for the client request");
            //creating socket and waiting for client connection
            Socket socket = server.accept();
            //read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //convert ObjectInputStream object to String
            String message = (String) ois.readObject();
            //System.out.println("Message Received: " + message);
            //create ObjectOutputStream object
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            if (message.equalsIgnoreCase("exit")) {
                //write object to Socket
                oos.writeObject("Adios " + nombre);
                i=false;
            } else {
                //write object to Socket
                oos.writeObject("Hola " + message);
                nombre = message;
            }
            //close resources
            ois.close();
            oos.close();
            socket.close();
            //terminate the server if client sends exit request

        }
        //System.out.println("Shutting down Socket server!!");
        //close the ServerSocket object
        server.close();
    }

}
