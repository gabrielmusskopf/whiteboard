package br.com.gabrielmusskopf.wbclient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientApplication {

	public static void main(String[] args) throws IOException {
		final var client = new Socket("localhost", 12345);

		final var input = new ObjectInputStream(client.getInputStream());

		byte type = input.readByte();
		int payloadSize = input.readInt();
		byte[] payload = input.readNBytes(payloadSize);

		System.out.println("Client said: ");
		System.out.print(type);
		for (byte b : payload) {
			System.out.print(b);
		}
		System.out.println();

		input.close();

		//var saida = new ObjectOutputStream(cliente.getOutputStream());
		//saida.flush();
		//saida.write("hello".getBytes());
		//saida.close();

		// ObjectInputStream input = new ObjectInputStream(cliente.getInputStream());
		// input.close();

		System.out.println("Conexão encerrada");
	}

}