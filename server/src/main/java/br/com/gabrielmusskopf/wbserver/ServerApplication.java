package br.com.gabrielmusskopf.wbserver;

import java.io.IOException;

public class ServerApplication {

	public static void main(String[] args) throws IOException {
		final var server = new Server();
		server.start();
	}

}