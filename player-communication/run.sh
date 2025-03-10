#!/bin/bash

# Compile the Java project using Maven
mvn clean install

# Run the Same Process Example
echo "**************Running Same Process Example****************"
java -cp target/classes com.example.process.single.SingleProcessCommunication
echo "**************Completed Running Same Process Example****************"

# Run the Separate Process Example
echo "**************Running Separate Process Example****************"
echo "Starting Receiver process (Server) in background..."
java -cp target/classes com.example.process.multiple.ReceiverProcessCommunication &


sleep 2 # Give Receiver process time to start

echo "Starting Initiator process (Client)..."
java -cp target/classes com.example.process.multiple.InitiatorProcessCommunication

echo "**************Separate processes communication finished.****************"

# Exit script gracefully
exit 0