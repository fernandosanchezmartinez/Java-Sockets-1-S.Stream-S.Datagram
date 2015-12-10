package EjercicioContador;

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

public class ServidorContador {

	public static void main(String[] args) {
/*
		Scanner sc = new Scanner(System.in);
		String txt = "";
		
		while (!txt.equals("FIN")) {
		
		*/
			/**
			 * SE RECIBEN MENSAJES:
			 */
		
		/*
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
				txt = new String(mensaje);
				System.out.println("Mensaje recibido: " + txt);

				datagramSocket.close();// se finaliza el socket
				System.out.println("Terminado");
				if (txt.equals("FIN")) {// si la palabra es fin, se para
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			*/
			
			/* recivimiento de mensajes mediante sockets stream
			try {
				System.out.println("Creando el socket servidor");
				ServerSocket serverSocket = new ServerSocket();
				System.out.println("Realizando el bind");
				//10.4.110.25   cliente carlos
				//localhost
				InetSocketAddress addr = new InetSocketAddress("10.4.110.23", 5555);
				serverSocket.bind(addr);
				System.out.println("Aceptando conexiones");
				Socket newSocket = serverSocket.accept();
				System.out.println("Conexión recibida");
				InputStream is = newSocket.getInputStream();
				OutputStream os = newSocket.getOutputStream();
				byte[] mensaje = new byte[25];
				is.read(mensaje);
				System.out.println("Mensaje recibido: " + new String(mensaje));
				System.out.println("Cerrando el nuevo socket");
				newSocket.close();
				System.out.println("Cerrando el socket servidor");
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			*/

			/**
			 * SE ENVIAN MENSAJES Se recoge la informacion del mensaje enviado
			 * por el cliente y con la función lenght() obtenemos el número de
			 * caracteres que contiene y procedemos a enviarlo como en el
			 * ejercicio del chat
			 * 
			 */
			
			/*
			try {
				System.out.println("Creando el socket datagram");
				DatagramSocket datagramSocket = new DatagramSocket();
				System.out.println("Enviando mensaje");
				String mensaje = "hay  " + txt.length() + "caractertes";
				// txt = mensaje;
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
		*/

		try {
			
			
			System.out.println("Creando socket cliente");
			Socket clientSocket = new Socket();
			System.out.println("Estableciendo la conexión");
			InetSocketAddress addr = new InetSocketAddress("10.4.110.23", 5555);
			clientSocket.connect(addr);
			InputStream is = clientSocket.getInputStream();
			OutputStream os = clientSocket.getOutputStream();
			int letrasTotales= 0;
			String mensaje = "";
			while (!mensaje.equals("fin")){
				
				
				
				byte[] cadena = new byte[25];
				is.read(cadena);
				System.out.println("Mensaje recibido: " + new String(cadena));
				mensaje = new String (cadena.toString());
				for (int i = 0; i < cadena.length; i++) {
					if(cadena[i]=='a'){
						letrasTotales++;
					}
				}
				System.out.println("Enviando mensaje");
				
				os.write(letrasTotales);
				System.out.println("Mensaje enviado");
				
			}
			
			System.out.println("Cerrando el socket cliente");
			clientSocket.close();
			System.out.println("Terminado");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
