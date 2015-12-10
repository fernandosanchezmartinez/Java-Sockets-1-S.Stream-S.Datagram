package ProgramaChat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * Clase cliente
 * 
 * @author Carlos.Ortiz
 *
 */
public class Cliente {

	public static void main(String[] args) throws Exception {
		
		Scanner sc = new Scanner(System.in);
		String ip = "10.4.110.23";
		
		DatagramSocket datagramSocket = new DatagramSocket(5555);
		byte[] buffer = new byte[25];
		DatagramPacket datagrama1 = new DatagramPacket(buffer, buffer.length);
		
		InetAddress addr = InetAddress.getByName(ip);
		
		String fin = "";
		/**
		 * Creacion de bucle, el cual se ejecuta mientras no se escriba adios.
		 */
		while(!fin.equals("adios")){
		try {
			System.out.println("Enviando mensaje");
			String mensaje = sc.nextLine();
			
			buffer = mensaje.getBytes();
			
			fin = mensaje;
			
			DatagramPacket datagrama = new DatagramPacket(buffer, buffer.length, addr, 5556);
			
			datagramSocket.send(datagrama);
			
			System.out.println("Mensaje enviado");
			
			/**
			 * Comprueba si se ha escrito adios
			 */
			if (fin.equals("adios")){
				break;
			}
			/**
			 * Si no se ha terminado la conversacion pasa a el estado de recibir el mensaje
			 */
			System.out.println("Recibiendo mensaje");
			
			datagramSocket.receive(datagrama1);
			
			
			fin = new String (datagrama1.getData(),0,datagrama1.getLength());
			
			System.out.println("Mensaje del Servidor: " + fin);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		}
		datagramSocket.close();
		System.out.println("Terminado");
		
	}
}

