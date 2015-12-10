package ProgramaChat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * CLASE ENCARGADA DE LA EJECUCION DEL SERVIDOR DEL NUESTRO "CHAT"
 * 
 * @author fernando.sanchez El servidor de nuestra app de chat básica es el
 *         encargado de ser el primero en ejecutar el programa y se queda
 *         esperando que el cliente le envie un mensaje
 *
 */
public class ServidorChat {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String fin = "";
		while (!fin.equals("FIN")) {
			/**
			 * SE RECIBEN MENSAJES:
			 */
			try {
				// se crea el socketDatagram y se recibe el mensaje del cliente
				System.out.println("Creando el socket datagram");
				InetSocketAddress addr = new InetSocketAddress("localhost",
						5556);// ponemos nuestra ip en vez de localhost(ip de la
								// maquina servidor)
				DatagramSocket datagramSocket = new DatagramSocket(addr);
				System.out.println("Recibiendo mensaje....");
				byte[] mensaje = new byte[25];
				DatagramPacket datagrama1 = new DatagramPacket(mensaje, 25);
				datagramSocket.receive(datagrama1);
				fin = new String(mensaje);
				System.out.println("Mensaje recibido: " + fin);

				datagramSocket.close();//se finaliza el socket
				System.out.println("Terminado");
				if (fin.equals("FIN")) {//si la palabra es fin, se para
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			/**
			 * SE ENVIAN MENSAJES:
			 */
			try {
				System.out.println("Creando el socket datagram");
				DatagramSocket datagramSocket = new DatagramSocket();
				System.out.println("Enviando mensaje");
				String mensaje = sc.nextLine();
				fin = mensaje;
				// Se pondria la ip del cliente
				InetAddress addr = InetAddress.getByName("localhost");
				DatagramPacket datagrama = new DatagramPacket(
						mensaje.getBytes(), mensaje.getBytes().length, addr,
						5555);
				datagramSocket.send(datagrama);// se envia el mensaje
				System.out.println("Mensaje enviado");
				System.out.println("Cerrando el socket datagrama");
				datagramSocket.close();// se cierra el socket
				System.out.println("Terminado");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
