package com.example.process.single;

import com.example.message.Message;
import com.example.process.single.players.Initiator;
import com.example.process.single.players.Receiver;
public class SingleProcessCommunication {
    public static void main(String[] args) {

        Object lock = new Object();
        Message message = new Message();

        // Create and start both threads
        Thread initiator = new Thread(new Initiator(lock, message));
        Thread receiver = new Thread(new Receiver(lock, message));

        receiver.start();
        initiator.start();

        try {
            // Wait for the initiator to finish before interrupting receiver
            initiator.join();
            receiver.interrupt();  // Interrupt the receiver once initiator finishes
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
