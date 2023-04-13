package co.edu.microservicios.sockets;

public class Main {

    public static void main(String[] args) {
        int puerto = 2000;
        Servidor servidor = new Servidor(puerto);
        servidor.iniciar();
    }
}