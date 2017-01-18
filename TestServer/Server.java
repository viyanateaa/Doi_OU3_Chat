package TestServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by kristoffer and Viyan on 2017-01-18.
 */
public abstract class Server {
    protected ServerSocket serverSocket;
    protected Socket socket;
    protected InputStream serverInputStream;
    protected OutputStream serverOutputStream;

    public Server(int port) throws IOException {

        serverSocket = new ServerSocket(port);
        socket = serverSocket.accept();
        serverInputStream = socket.getInputStream();
        serverOutputStream = socket.getOutputStream();

        Thread serverThread = new Thread(){
            public void run(){
                while(socket.isConnected()){
                    try {
                        int byteArraySize = serverInputStream
                                .available();
                        byte byteArray[] = new byte[byteArraySize];
                        if(byteArraySize != 0){
                            serverInputStream.read(byteArray,0,
                                    byteArraySize);
                            returnInfo();
                            sleep(500);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };serverThread.start();
    }

    public abstract void returnInfo() throws IOException;
}
