
# Communication Process Example

This project demonstrates two examples of inter-process communication:
1. **Same Process Communication**: Communication between threads in the same process.
2. **Separate Process Communication**: Communication between two separate processes using socket-based communication.

## Prerequisites

Before running the project, ensure you have the following installed:

- **Java 8 or later**
- **Maven** for project build and dependency management.

## Installation

1. Clone the repository:

   ```bash
   git clone [https://github.com/your-username/your-repository](https://github.com/viswarajramji/Player-Communication).git
   cd Player-Communication
   ```
2. Run the script for triggering the execution.
   ```bash
   ./run.sh
   ```

## Script Details

- **`run.sh`**: 
  This script compiles the project using Maven and runs both the **Same Process** and **Separate Process** examples sequentially. The `run.sh` script performs the following:
  
  1. Compiles the project using Maven.
  2. Runs the **Same Process Communication Example** (`SingleProcessCommunication`).
  3. Starts the **Receiver Process** in the background and runs the **Initiator Process** for the **Separate Process Communication Example**.
  4. Waits for the **Receiver Process** to finish before exiting the script.

### Example:

```bash
# Execute the script
./run.sh
```

# Same-Process Communication Example

This project demonstrates inter-thread communication within a **single process** using two threads: **Initiator** and **Receiver**. The two threads communicate by passing messages back and forth using synchronization mechanisms (`wait()` and `notify()`).

## Approach

The approach uses **two threads**, `Initiator` and `Receiver`, within the same process to exchange messages. The communication flow is controlled using a **shared lock object**, which is used to synchronize the threads. Here’s how the communication works:

1. **Initiator Thread (Client)**:
    - The `Initiator` sends a message to the `Receiver` using the shared `lock` object.
    - It waits for the `Receiver` to process the message, using `lock.wait()`.
    - Once the `Receiver` responds, the `Initiator` receives the updated message and prints it.

2. **Receiver Thread (Server)**:
    - The `Receiver` waits for a message from the `Initiator` using `lock.wait()`.
    - Once the message is received, the `Receiver` appends a counter to the message and sends it back to the `Initiator` using `lock.notify()`.
    - The `Receiver` repeats this process for a set number of iterations (10 in this case).

3. **Synchronization Mechanism**:
    - Both threads are synchronized using the shared **`lock` object**. This ensures that the threads alternate between sending and receiving messages, and the communication happens in the correct order.
    - The **`wait()`** and **`notify()`** methods are used to control the communication flow. The `Initiator` waits for a response from the `Receiver` and vice versa.

## Why This Approach?

1. **Simplicity of Same-Process Communication**:
    - This approach is ideal for scenarios where the threads need to communicate in the same process. Using **shared memory** (through the shared `lock` object and `message`) and synchronization ensures that the threads interact in a controlled manner without needing external resources like network sockets.

2. **Using `wait()` and `notify()` for Synchronization**:
    - **`wait()`** and **`notify()`** are built-in Java methods that allow threads to **wait for a signal** or **notify another thread** when specific conditions are met. This is a standard pattern for inter-thread communication and is useful for scenarios where threads need to alternate or work in sync (as in this example, where the `Initiator` and `Receiver` take turns).

3. **Thread Coordination**:
    - This approach ensures that the `Initiator` and `Receiver` operate in a synchronized manner, making sure that one thread does not run out of order or miss its turn to execute.

4. **Process ID Logging for Debugging**:
    - Each thread logs its **process ID** for debugging purposes. This is useful for tracking execution when both threads run in the same process and helps identify the process responsible for each action.

5. **Scalability in Multi-threaded Applications**:
    - Though this example uses only two threads, the same approach can be expanded to manage more threads, enabling more complex communication patterns between them.

## Flow of Execution

1. **Initiator Process**:
    - The `Initiator` sends an initial message to the `Receiver`.
    - The message is updated by the `Receiver` and sent back to the `Initiator`.
    - This message-passing continues for a set number of iterations (10).

2. **Receiver Process**:
    - The `Receiver` waits for messages from the `Initiator`, processes them (appending a counter), and sends the response back.


# Communication Process Example - Seperate Process

This project demonstrates **client-server communication** between two processes:
1. **Initiator Process** (Client)
2. **Receiver Process** (Server)

The communication occurs over **TCP sockets**.

## Key Concepts

- **TCP Socket Communication**: The **Initiator** sends a message to the **Receiver**, and the **Receiver** appends a counter to the message and sends it back.
- **Process ID Logging**: Each process logs its **process ID** for debugging and identification.
- **Synchronous Communication**: The **Initiator** and **Receiver** communicate synchronously in a loop for 10 iterations.

## Why This Approach?

1. **Separation of Concerns (Client-Server Model)**:
    - The project follows a **client-server architecture**, where the **Initiator** (client) and **Receiver** (server) have distinct roles. This ensures clear separation of logic:
        - **Initiator**: Sends requests to the **Receiver** and processes responses.
        - **Receiver**: Listens for requests and responds with the updated message.
    - This approach enhances maintainability, scalability, and debugging by keeping the roles of each process distinct.

2. **TCP Socket Communication**:
    - **Reliability and Order**: TCP sockets ensure that the messages between the **Initiator** and **Receiver** are delivered in order and without loss, providing reliable communication between processes.
    - **Scalability**: The use of TCP sockets allows for future extensions, such as running these processes on separate machines, or connecting multiple clients to a single server.

3. **Ease of Debugging with Process IDs**:
    - Logging the **process ID** (`processId`) for both the **Initiator** and **Receiver** helps track and identify each process in a multi-process environment. This is particularly useful for debugging and logging purposes.

## Flow of Execution

1. **Initiator Process (Client)**:
    - Connects to the **Receiver** via a TCP socket on port `12345`.
    - Sends an initial message (`"hello "`).
    - Waits for a response from the **Receiver** and updates the message with the response.
    - Repeats for 10 iterations.

2. **Receiver Process (Server)**:
    - Listens on port `12345` for incoming connections.
    - Receives the message from the **Initiator**, appends a counter to the message.
    - Sends the updated message back to the **Initiator**.
   
## Contributing

Feel free to fork the repository and submit pull requests with improvements or fixes. If you encounter any issues or have suggestions, feel free to open an issue.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
