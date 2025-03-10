package com.example.process.multiple;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.net.*;
public class InitiatorProcessCommunication {
    public static void main(String[] args) {
        String processId = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
        System.out.println("Running Initiator on process id : "+processId);
        try (Socket socket = new Socket("localhost", 12345); // Connect to the Receiver process
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String message = "hello ";  // Initial message

            for (int i = 1; i <= 10; i++) {
                out.println(message);  // Send the message to the receiver
                System.out.println("Initiator sent to receiver (processId:"+processId+") - " + message);

                // Receive the response from the Receiver and append the counter
                String response = in.readLine();
                message = response;  // Update message with the receiver's response
                System.out.println("Initiator Received to receiver (processId:"+processId+") - " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Initiator finished communication.");
    }
}
