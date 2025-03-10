package com.example.process.multiple;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.net.*;
public class ReceiverProcessCommunication {
    public static void main(String[] args) {
        String processId = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
        System.out.println("Running Recevier on process id : "+processId);
        try (ServerSocket serverSocket = new ServerSocket(12345);  // Listen on port 12345
             Socket clientSocket = serverSocket.accept();  // Accept connection from Initiator
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String message;
            int receiverCounter = 1;

            while ((message = in.readLine()) != null) {
                System.out.println("Receiver received from initiator (processId:"+processId+") -  " + message);

                // Append receiver's counter to the message and send it back
                String response = message + " | Counter: " + receiverCounter++;
                out.println(response);
                System.out.println("Receiver sent to initiator (processId:"+processId+") - " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Receiver finished communication.");
    }
}
