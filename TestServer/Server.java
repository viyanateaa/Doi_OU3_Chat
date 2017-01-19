package TestServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Course: Datakommutikation och internet 5DV167
 * Assignment: OU3
 * Written by: Kristoffer & Viyan
 * Version: 19/1 -17.
 */

/**
 * Abstract server for testing a ChatClient.
 */
public abstract class Server {
    protected Socket socket;
    protected ServerSocket serverSocket;
    protected OutputStream serverOutputStream;
    protected InputStream serverInputStream;

    public Server(int port) throws IOException {

        serverSocket = new ServerSocket(port);
        socket = serverSocket.accept();
        serverOutputStream = socket.getOutputStream();
        serverInputStream = socket.getInputStream();

        Thread serverThread = new Thread(){
            public void run(){
                boolean isConnected = true;
                if(!socket.isConnected()){
                    isConnected = false;
                }
                while(isConnected){
                    try {
                        int byteArraySize = serverInputStream
                                .available();
                        byte byteArray[] = new byte[byteArraySize];

                        if(byteArraySize != 0){
                            serverInputStream.read(byteArray,0,
                                    byteArraySize);
                            serverMessage();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };serverThread.start();
    }

    public abstract void serverMessage() throws IOException;
}
