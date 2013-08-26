package com.epam.pdp.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

	private final long SLEEP_PERIOD = 200; // ms
	private final int SERVER_PORT = 1234;

	private boolean stoped;

	@Override
	public void run() {
		System.out.println("S: Starting server");
		try {
			while (!stoped) {
				ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
				System.out.println("S: Waiting for client");
				Socket clientSocket = serverSocket.accept();
				System.out.println("S: Client connected");
				BufferedReader br = new BufferedReader(new InputStreamReader(
						clientSocket.getInputStream()));
				while (!stoped) {					
					String message = br.readLine();
					if (message != null && !message.isEmpty()) {
						System.out.println("S: Acepted message from client");
						System.out.println("S: Client message: " + message);
					}
					Thread.currentThread().sleep(SLEEP_PERIOD);
				}
				serverSocket.close();

			}
		} catch (IOException | InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	public void stop() {
		System.out.println("S: Stoping server");
		this.stoped = true;
	}

}
