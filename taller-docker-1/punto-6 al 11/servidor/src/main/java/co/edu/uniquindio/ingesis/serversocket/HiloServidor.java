package co.edu.uniquindio.ingesis.serversocket;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public class HiloServidor extends Thread{
    private final Socket socket;
    private static final Logger LOGGER = Logger.getLogger(HiloServidor.class.getName());

    public HiloServidor(Socket socket) {
        this.socket = socket;

    }

    @Override
    public void run() {
        super.run();
        String root = getEnv("ROOT","root");
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(
                socket.getInputStream())) ; PrintStream salida = new PrintStream(socket.getOutputStream()) ){
            String mensajeRecibido = entrada.readLine();
            LOGGER.info("Mensaje entrante: "+mensajeRecibido);
            int i = mensajeRecibido.indexOf("CLIENTE ") ;
            String respuesta;
            if( i != 0 ){
                respuesta="ERROR: No se ha podido obtener el mensaje.";
            } else {
                String cliente = mensajeRecibido.substring(8);
                if(root.equals(cliente)){
                    cliente = "HOLA ADMINISTRADOR";
                }
                respuesta ="HOLA "+cliente;
            }
            LOGGER.info("Mensaje de salienda: "+respuesta);
            salida.println(respuesta);
            LOGGER.info("La conexion se ha cerrado...");
        } catch (IOException e) {
            LOGGER.warning("Error al leer o entregar mensaje: "  + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                LOGGER.warning("Error: no se ha podido cerrar el socket del cliente. "+e.getMessage());
            }
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
