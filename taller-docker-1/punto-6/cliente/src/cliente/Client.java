package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        boolean i = true;
        String msg = "";
        while (i) {
            //establish socket connection to server
            socket = new Socket(host.getHostName(), 9876);
            //write to socket using ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending request to Socket Server");
            msg = JOptionPane.showInputDialog("Ingrese su mensaje");
            if (msg.equalsIgnoreCase("exit") || msg.equalsIgnoreCase("salir")
                     || msg.equalsIgnoreCase("out") || msg.equalsIgnoreCase("salida")
                     || msg.equalsIgnoreCase("o") || msg.equalsIgnoreCase("s")
                     || msg.equalsIgnoreCase("e") || msg.equalsIgnoreCase("x")) {
                oos.writeObject("exit");
                i=false;
            } else {
                oos.writeObject(msg);
            }
            //read the server response message
            ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            JOptionPane.showMessageDialog(null, message);
            //System.out.println("Message: " + message);
            //close resources
            ois.close();
            oos.close();
            Thread.sleep(400);
        }
    }
}
