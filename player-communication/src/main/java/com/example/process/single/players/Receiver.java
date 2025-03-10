package com.example.process.single.players;

import com.example.message.Message;

import java.lang.management.ManagementFactory;

public class Receiver implements Runnable {
    private final Object lock;
    private final Message message;
    private int messageCounter = 1;

    public Receiver(Object lock, Message message) {
        this.lock = lock;
        this.message = message;
    }

    @Override
    public void run() {
        String processId = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (lock) {
                try {
                    // Wait for the initiator to send a message
                    lock.wait();

                    String receivedMessage = message.getContent();
                    System.out.println("Receiver received from initiator (processId:"+processId+") -  "+ receivedMessage);

                    // Add counter to the message
                    String responseMessage = receivedMessage + " | Counter: " + messageCounter++;
                    message.setContent(responseMessage);

                    // Notify the initiator that the message has been processed
                    lock.notify();
                    System.out.println("Receiver sent to initiator (processId:"+processId+") - " + responseMessage);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        System.out.println("Receiver finished communication.");
    }
}