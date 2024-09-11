package br.com.gabrielmusskopf.wbserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {

	private final Whiteboard whiteboard = new Whiteboard();
	private final Map<String, Client> connectedClients = new HashMap<>();

	public void start() throws IOException {
		// blocking
		try (var server = new ServerSocket(12345)) {
			while (true) {
				final var clientSocket = server.accept();
				final var client = connect(clientSocket);
				System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

				sendCompleteBoad(client);

				System.out.println("Conexão encerrada");
				disconnect(client);
			}
		}
	}

	private Client connect(Socket socket) {
		final var client = new Client(socket);
		connectedClients.put(client.getIp(), client);
		return client;
	}

	private void disconnect(Client client) throws IOException {
		final var removed = connectedClients.remove(client.getIp());
		if (removed != null) {
			removed.disconnect();
		}
	}

	private void sendCompleteBoad(Client client) throws IOException {
		final var currentBoard = whiteboard.getCurrentBoard();
		byte[] serializedBoard = new byte[(whiteboard.getWidth() * whiteboard.getHeight()) + whiteboard.getHeight()];
		int p = 0;

		for (int i = 0; i < whiteboard.getWidth() - 1; i++) {
			int[] line = currentBoard[i];
			for (int j = 0; j < line.length; j++) {
				serializedBoard[p++] = (byte) line[j];
			}
			serializedBoard[p++] = (byte) '\n';
		}

		serializedBoard[p++] = (byte) '\n';
		serializedBoard[p] = (byte) '\n';

		client.write(serializedBoard);
	}

	public void broadcast() {
	}

}
