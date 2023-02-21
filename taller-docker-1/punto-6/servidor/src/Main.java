import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class Main {

    private static Socket socket;
    public static void main(String[] args) {
        try{
            int port = 25000;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started in port: " + port);

            while(true){
                //Reading Message from the client
                socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String message = bufferedReader.readLine();
                System.out.println("Message received from client is "+ message);
                String returnMessage = "Hello from Server";
                String sendreturnMessage = returnMessage + "\n";
                //Sending the response back to the client.

                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write(sendreturnMessage);
                bw.flush();
                System.out.println("Message sent to the client is "+returnMessage);
                System.out.println("\nProcess Complete\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally
        {
            try
            {
                socket.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}