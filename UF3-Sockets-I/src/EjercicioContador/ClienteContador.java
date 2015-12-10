package EjercicioContador;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClienteContador {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Creando socket cliente");
			Socket clientSocket = new Socket();
			System.out.println("Estableciendo la conexión");
			InetSocketAddress addr = new InetSocketAddress("10.4.110.23", 5555);
			clientSocket.connect(addr);
			InputStream is = clientSocket.getInputStream();
			OutputStream os = clientSocket.getOutputStream();
			String mensaje="";
			while (!mensaje.equals("fin")){
				
				System.out.println("Enviando mensaje");
				mensaje = sc.nextLine();
				os.write(mensaje.getBytes());
				System.out.println("Mensaje enviado");
				
				byte[] cadena = new byte[25];
				is.read(cadena);
				System.out.println("Mensaje recibido: " + new String(cadena));
				
			}
			
			System.out.println("Cerrando el socket cliente");
			clientSocket.close();
			System.out.println("Terminado");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
