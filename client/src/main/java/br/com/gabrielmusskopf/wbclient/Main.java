package br.com.gabrielmusskopf.wbclient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Main {

	public static void main(String[] args) throws IOException {
		final var client = new Socket("localhost", 12345);

		final var input = new ObjectInputStream(client.getInputStream());

		final var sb = new StringBuilder();
		int current, previous = -1;

		while ((current = input.read()) != -1) {
			sb.append(current);
			if (previous == '\n' && current == '\n') {
				break;
			}
			previous = current;
		}

		System.out.println("Client said: \n" + sb);

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