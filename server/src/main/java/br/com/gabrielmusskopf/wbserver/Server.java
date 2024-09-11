package br.com.gabrielmusskopf.wbserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {

	private static final int PORT = 12345;

	private final Whiteboard whiteboard = new Whiteboard();
	private final Map<String, Client> connectedClients = new HashMap<>();

	public void start() throws IOException {
		// blocking
		try (var server = new ServerSocket(PORT)) {
			System.out.println("Server is running on " + PORT);
			while (true) {
				final var clientSocket = server.accept();
				final var client = connect(clientSocket);
				sendCompleteBoard(client);
				disconnect(client);
			}
		}
	}

	private Client connect(Socket socket) {
		final var client = new Client(socket);
		connectedClients.put(client.getIp(), client);

		System.out.println("Client connected: " + client.getIp());
		return client;
	}

	private void disconnect(Client client) throws IOException {
		final var removed = connectedClients.remove(client.getIp());
		if (removed != null) {
			removed.disconnect();
			System.out.println("Client disconnected: " + client.getIp());
		}
	}

	private void sendCompleteBoard(Client client) throws IOException {
		final var currentBoard = whiteboard.getCurrentBoard();
		byte[] serializedBoard = new byte[(whiteboard.getWidth() * whiteboard.getHeight()) + whiteboard.getHeight()];
		int p = 0;

		for (int[] line : currentBoard) {
			for (int value : line) {
				serializedBoard[p++] = (byte) value;
			}
			serializedBoard[p++] = (byte) '\n';
		}

		final var message = new BoardMessage(serializedBoard);
		client.write(message.serialize());
	}

	public void broadcast() {
	}

}
