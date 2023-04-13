package co.edu.microservicios.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {
    private static int puerto = 2000;
    public static void main(String[] args) {
        String host = getEnv("SERVER", "localhost");
        String cliente = getEnv("CLIENTE", "cliente");
        try(Socket socket = new Socket(host, puerto);
            PrintStream mensaje = new PrintStream(socket.getOutputStream());
            BufferedReader entrada = new BufferedReader(new InputStreamReader(
                    socket.getInputStream())) ) {

            //Enviamos un mensaje
            mensaje.println("CLIENTE "+cliente+"\n");
            String respuesta = entrada.readLine();

            System.out.println(respuesta);

        } catch (UnknownHostException e) {
            System.out.println("El host no existe o no está activo.");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida.");
        }
    }
    private static String getEnv(String variable,String defaultValue){
        String host = System.getenv(variable);
        if( host == null ){
            host = defaultValue;
        }
        return host;
    }
}