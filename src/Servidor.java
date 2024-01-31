import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {

    private static Integer stockOrdenadores;

    public static void main(String[] args) throws Exception {
        System.out.println(
                "******************************************\r\n" + //
                        "\r\n" + //
                        "*PSP - Tarea Individual 4 - Cliente / Servidor*\r\n" + //
                        "\r\n" + //
                        "******************************************\r\n" + //
                        "\r\n" + //
                        "* Miriam Gallardo González-Amor *\r\n" + //
                        "\r\n" + //
                        "******************************************\r\n" + //
                        "\r\n" + //
                        "* 53772609N     *                     \r\n" + //
                        "");

        System.out.println("Introducir número de puerto: ");
        Scanner scanner = new Scanner(System.in);
        Integer puertoSeleccionado = scanner.nextInt();
        scanner.close();
        stockOrdenadores = 20;
        try {
            // Creamos un objeto de tipo Serversocket pasra arrancar un servidor
            ServerSocket serverSocket = new ServerSocket(puertoSeleccionado);

            while (true) {
                // Espera la conexión hasta que un cliente se conecta (siempre está escuchando)
                Socket socketCliente = serverSocket.accept();
                System.out.println("cliente conectado");
                ConexionCliente conexionCliente = new ConexionCliente(socketCliente);
                conexionCliente.start();
            }

        } catch (Exception exception) {
            System.out.println("No se ha podido arrancar el servidor");
        }

    }

    public static void aumentarStock(Integer ordenadoresAñadidos) {
        stockOrdenadores = stockOrdenadores + ordenadoresAñadidos;
    }

    public static void disminuirStock(Integer ordenadoresDisminuidos) {
        stockOrdenadores = stockOrdenadores - ordenadoresDisminuidos;
    }

    public static Integer consultarStock() {
        return stockOrdenadores;

    }
}
