package com.epam.pdp.app;

import com.epam.pdp.client.Client;
import com.epam.pdp.server.Server;

public class App {

	private static final long CONVERSATION_PERIOD = 2000; // ms

	public static void main(String[] args) {

		// TODO: Create server and start listeting
		Server server = new Server();
		Client client = new Client(1234);
		Thread serverThread = new Thread(server);
		try {
			serverThread.join();
			serverThread.start();
			client.connect();
			Thread.currentThread().sleep(CONVERSATION_PERIOD / 2);
			client.sendMessage("Hello World!!!");
			Thread.currentThread().sleep(CONVERSATION_PERIOD);
			server.stop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
