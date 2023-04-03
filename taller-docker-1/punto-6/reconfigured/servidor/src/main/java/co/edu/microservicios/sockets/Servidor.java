package co.edu.microservicios.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Logger;

public class Servidor {
    private static final Logger LOGGER = Logger.getLogger(Servidor.class.getName());
    private final int puerto;

    public Servidor(int puerto){
        this.puerto = puerto;
    }

    public void iniciar() {
        try(ServerSocket serverSocket = new ServerSocket(puerto)){
            LOGGER.info("Esperando una conexión");
            while(true){
                ServerThread serverThread = new ServerThread(serverSocket.accept());
                LOGGER.info("Un Cliente se ha conectado");
                serverThread.start();
            }
        }catch (IOException e){
            LOGGER.warning("Error de entrada/salida" + e.getMessage());
        }finally {
            LOGGER.info("Cerrando Conexión");
        }
    }
}
