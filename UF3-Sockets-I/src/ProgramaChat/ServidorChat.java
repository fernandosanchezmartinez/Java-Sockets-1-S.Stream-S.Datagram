package ProgramaChat;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
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

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		String ip = "10.4.110.25";
		DatagramSocket datagramSocket;
		byte[] buffer = new byte[25];
		DatagramPacket datagrama1 = new DatagramPacket(buffer, buffer.length);
		
		InetAddress addr = InetAddress.getByName(ip);
		
		datagramSocket = new DatagramSocket(5556);
		String fin = "";
		while(!fin.equals("adios")){
		try {
			System.out.println("Recibiendo mensaje");
			
			datagramSocket.receive(datagrama1);
			
			
			fin = new String (datagrama1.getData(),0,datagrama1.getLength());
			
			System.out.println("Mensaje del Cliente: " + fin);
			
			
			
			if (fin.equals("adios")){
				break;
			}
			System.out.println("Enviando mensaje");
			String mensaje = sc.nextLine();
			
			buffer = mensaje.getBytes();
			
			fin = mensaje;
			
			DatagramPacket datagrama = new DatagramPacket(buffer, buffer.length, addr, 5555);
			
			datagramSocket.send(datagrama);
			
			System.out.println("Mensaje enviado");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		
	
		datagramSocket.close();
		System.out.println("Terminado");
		
	}

}
