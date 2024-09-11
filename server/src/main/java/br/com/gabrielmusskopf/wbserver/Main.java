package br.com.gabrielmusskopf.wbserver;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("Server is running!");
		final var server = new Server();
		server.start();
	}

}