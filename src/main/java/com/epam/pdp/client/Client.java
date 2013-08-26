package com.epam.pdp.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private int serverPort = 1234;
	private String serverHost = "localhost";
	private Socket clientSocket;

	public Client(int port) {
		this.serverPort = port;
	}

	public void connect() {
		try {
			System.out.println("C: Connecting to server");
			clientSocket = new Socket(serverHost, serverPort);
			System.out.println("C: Connected to server");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendMessage(String message) {
		try {
			PrintStream printStream = new PrintStream(
					clientSocket.getOutputStream());
			System.out.println("C: Sending message: " + message);
			printStream.print(message);
			clientSocket.close();
			System.out.println("C: Message send");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
