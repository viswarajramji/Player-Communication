package com.example.process.single.players;


import com.example.message.Message;

import java.lang.management.ManagementFactory;

public class Initiator implements Runnable {
    private final Object lock;
    private final Message message;

    public Initiator(Object lock, Message message) {
        this.lock = lock;
        this.message = message;
    }

    @Override
    public void run() {
        String processId = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
        System.out.println("Running on process id : "+processId);
        for (int i = 1; i <= 10; i++) {
            synchronized (lock) {
                try {
                    // Set the initial message or update it if not the first message
                    message.setContent(message.getContent() == null ? "hello " : message.getContent());
                    System.out.println("Initiator sent to receiver (processId:"+processId+") - " + message.getContent());
                    // Notify the receiver to process the message
                    lock.notify();
                    // Wait for the receiver to send a response
                    lock.wait();
                    System.out.println("Initiator Received to receiver (processId:"+processId+") - " + message.getContent());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        System.out.println("Initiator finished communication.");

    }

}