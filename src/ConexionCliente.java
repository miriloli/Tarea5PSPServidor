import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConexionCliente extends Thread {
    Socket cliente;
    DataInputStream in;
    DataOutputStream out;

    public ConexionCliente(Socket puertoSeleccionado) throws IOException {
        this.cliente = puertoSeleccionado;

    }

    public void run() {

        try {

            // canal entrada
            in = new DataInputStream(cliente.getInputStream());

            // canal de salida
            out = new DataOutputStream(cliente.getOutputStream());

            // Recibimos la opción elegida por parte del cliente
            Integer opcionElegidaCliente = in.readInt();

            // Creamos un switch para programar cada opcion del menú
            switch (opcionElegidaCliente) {
                case 1:
                    // Le mostramos al cliente el stock actual de ordenadores
                    out.writeUTF("Stock de ordenadores actual: " + Servidor.consultarStock());
                    break;
                case 2:
                    // Le pedimos al cliente la cantidad de ordenadores que desea añadir
                    out.writeUTF("Elija la cantidad de ordenadores para añadir: ");

                    // el cliente va a responder con un numero
                    int cantidadAñadir = in.readInt();

                    // actualizo la cantidad de ordenadores
                    Servidor.aumentarStock(cantidadAñadir);

                    System.out.println(
                            "El cliente ha actualizado la cantidad de ordenadores, stock de ordenadores actual: "
                                    + Servidor.consultarStock());

                    // por UX le digo al cliente la nueva cantidad
                    out.writeUTF("la cantidad ha sido actualizada, stock de ordenadores actual: "
                            + Servidor.consultarStock());

                    break;
                case 3:
                    // Le pedimos al cliente la cantidad de ordenadores que desea quitar
                    out.writeUTF("Elija la cantidad de ordenadores a disminuir: ");

                    // el cliente va a responder con un numero
                    int cantidadEliminar = in.readInt();

                    // actualizo la cantidad de ordenadores
                    Servidor.disminuirStock(cantidadEliminar);

                    System.out.println(
                            "El cliente ha actualizado la cantidad de ordenadores, stock de ordenadores actual: "
                                    + Servidor.consultarStock());

                    // por UX le digo al cliente la nueva cantidad
                    out.writeUTF("la cantidad ha sido actualizada, stock de ordenadores actual: "
                            + Servidor.consultarStock());

                    break;
                case 4:
                    System.out.println("Cliente desconectado");

                    cliente.close();

                    break;

                default:
                    break;

            }

        } catch (Exception exception) {

        }

        System.out.println("Saliendo del hilo");
    }
}
