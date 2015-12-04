package ProgramaChat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import java.util.Scanner;

class Conversacion {
	
	private boolean conversacionTerminada = false;
	private String mensajeRecivido;
	private String mensajeEnviado;
	private DatagramSocket datagramSocket;
	private InetAddress addr;
	private InetSocketAddress addr2;
	private String ip;
	private Scanner sc;
	/**
	 * 
	 * @param ipServidor
	 */
	public Conversacion (String ipServidor){
		try {
			mensajeEnviado ="";
			mensajeRecivido = "";
			ip = ipServidor;
			System.out.println("Creando el socket datagram");
			addr2 = new InetSocketAddress(ip, 5555);
			datagramSocket = new DatagramSocket(addr2);
			System.out.println("Enviando mensaje");
			addr = InetAddress.getByName(ip);
			sc = new Scanner(System.in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 */
	public void cerrarConexion(){
		
		System.out.println("Cerrando el socket datagrama");
		datagramSocket.close();
		System.out.println("Terminado");
		
		
	}
	/**
	 * 
	 */
	public void setConversacionTerminada(){
		conversacionTerminada = true;
		
	}
	/**
	 * 
	 * @return
	 */
	public boolean getConversacionTerminada(){
		return conversacionTerminada;
	}
	/**
	 * 
	 */
	public void enviarMensaje (){
		
		try {
			
			mensajeEnviado = sc.nextLine();
			DatagramPacket datagrama = new DatagramPacket(mensajeEnviado.getBytes(),
					mensajeEnviado.getBytes().length, addr, 5556);
				datagramSocket.send(datagrama);
			System.out.println("Mensaje enviado");
		
			if (mensajeEnviado == "adios"){
				setConversacionTerminada();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 */
	public void recivirMensaje(){
		try {
			
			System.out.println("Recibiendo mensaje");
			byte[] mensaje = new byte[25];
			DatagramPacket datagrama1 = new DatagramPacket(mensaje, 25);
				datagramSocket.receive(datagrama1);
				mensajeRecivido = new String (mensaje);
			System.out.println("Mensaje recibido: " + new String(mensaje));
		
			if (mensajeRecivido == "adios"){
				setConversacionTerminada();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	
}
class MensajeRecibido extends Thread {
	
	private Conversacion con;
	
	/**
	 * 
	 * @param c
	 */
	public MensajeRecibido(Conversacion c){
		con = c;
	}
	/**
	 * 
	 */
	public void run() {
		while (con.getConversacionTerminada()!=true){
			con.recivirMensaje();
		}
		con.cerrarConexion();
		
	}
}

class MensajeEnviado extends Thread {
	
	private Conversacion con;
	
	/**
	 * 
	 * @param c
	 */
	public MensajeEnviado(Conversacion c) {
		// TODO Auto-generated constructor stub
		con = c;
	}

	/**
	 * 
	 */
	public void run() {
		while (con.getConversacionTerminada()!=true){
			con.enviarMensaje();
		}
		con.cerrarConexion();
	}
	
}

public class Cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String ip = "localhost";
		//ip fernado -- 10.4.110.23
		Conversacion conversacion = new Conversacion(ip);
			
		MensajeRecibido mR = new MensajeRecibido(conversacion);
		MensajeEnviado mE = new MensajeEnviado(conversacion);
		
		mR.start();
		mE.start();
		
		
	}

}

