package M4.MCCS.Part1HW;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap; 

public class Server {
    private int port = 3000;
    // thread-safe map; multiple ServerThreads may call Server methods concurrently
    /*
    ggi 10-26-28
    I created the new handShuffleText method because the asignment said the server needed an exposed method for the logic. 
    Inside that method, I first called my private helper method, shuffleString(text), which takes the message and messes up the order of the characters.
    I converted the string to an array so i could mess with individual characters, since you cant reorder characters in a standard java string.
    I used a for loop and a random generator to shuffle the array by picking characters randomly and swapping them, which is a common trick. 
    I used string.format() to build the final message.
    */
   protected void handleShuffleText(ServerThread sender, String text) {
    String shuffledText = shuffleString(text);
    String message = String.format("Shuffled from User: " + sender.getClientId(), shuffledText);
   }private String shuffleString(String text) {
    char[] characters = text.toCharArray():
    java.util.Random random = new java.util.Random();
    for (int i = characters.length - 1; i > 0; i--) {
        int j = random.nextInt(i + 1);
        char temp = characters[i];
        characters[i] = characters[j];
        characters[j] = temp;
    }

    return new String(characters);

}

    private final ConcurrentHashMap<Long, ServerThread> connectedClients = new ConcurrentHashMap<>();
    private boolean isRunning = true;

    private void start(int port) {    
        this.port = port;
        System.out.println("Listening on port " + this.port);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (isRunning) {
                System.out.println("Waiting for next client");
                Socket incomingClient = serverSocket.accept(); // blocks until a client connects
                System.out.println("Client connected");
                // third arg is a callback; ServerThread calls it once streams are open and it
                // is ready
                ServerThread serverThread = new ServerThread(incomingClient, this, this::onServerThreadInitialized);
                serverThread.start();
                // not added to connectedClients here; that happens inside the callback after
                // setup   
            }
        } catch (IOException e) {
            System.err.println("Error accepting connection");
            e.printStackTrace();
        } finally {
            System.out.println("Server socket closed");
        }
    }

    /**
     * Callback from ServerThread once streams are open and it is ready to
     * send/receive.
     * Registers the client and announces their arrival.
     */
    private synchronized void onServerThreadInitialized(ServerThread serverThread) {
        connectedClients.put(serverThread.getClientId(), serverThread);
        broadcast(null, String.format("*User[%s] connected*", serverThread.getClientId()));
    }

    /**
     * Internal disconnect: stops the thread, removes it from the map, and
     * broadcasts a notice.
     * Used by handleDisconnect() and can be reused for server-side actions like
     * kicks or timeouts.
     */
    private synchronized void disconnect(ServerThread serverThread) {
        serverThread.disconnect();
        connectedClients.remove(serverThread.getClientId());
        broadcast(null, String.format("User[%s] disconnected", serverThread.getClientId()));
    }

    /**
     * Sends a message to all connected clients.
     * Any client whose send fails is removed from the map.
     */
    private synchronized void broadcast(ServerThread sender, String message) {
        String senderLabel = sender == null ? "Server" : String.format("User[%s]", sender.getClientId());
        final String formatted = String.format("%s: %s", senderLabel, message);
        connectedClients.values().removeIf(serverThread -> !serverThread.sendToClient(formatted));
    }

    // handle* methods are the interface ServerThread uses to trigger Server actions

    /**
     * Called when a client requests to disconnect. Delegates to disconnect().
     */
    protected synchronized void handleDisconnect(ServerThread sender) {
        disconnect(sender);
    }

    /**
     * Sends the connected user list only to the requesting client.
     * The requester's entry is tagged with "(you)".
     */
    protected synchronized void handleGetUserList(ServerThread serverThread) {
        StringBuilder sb = new StringBuilder("Connected users:\n");
        connectedClients.forEach((id, st) -> {
            if (id == serverThread.getClientId()) {
                sb.append(String.format("  User[%s] (you)\n", id));
            } else {
                sb.append(String.format("  User[%s]\n", id));
            }
        });
        serverThread.sendToClient(sb.toString().trim());
    }

    /** Reverses the text and broadcasts the result. */
    protected synchronized void handleReverseText(ServerThread sender, String text) {
        StringBuilder sb = new StringBuilder(text);
        sb.reverse();
        broadcast(sender, sb.toString());
    }

    /** Broadcasts a chat message from the sender to all clients. */
    protected synchronized void handleMessage(ServerThread sender, String text) {
        broadcast(sender, text);
    }

    public static void main(String[] args) {
        System.out.println("Server Starting");
        Server server = new Server();
        int port = 3000;
        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception e) {
            // use default port
        }
        server.start(port);
        System.out.println("Server Stopped");
    }
}
