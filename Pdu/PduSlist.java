 package Pdu;

import java.io.InputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
 /**
  * Created by kristoffer & Viyan on 2016-10-11.
  */


public class PduSlist extends Pdu {

    private int nrOfServers;
    private List<Server> serverList;

    public PduSlist(InputStream inputStream) throws IOException {

        byte[] byteArray;
        sequenceBuilder = new ByteSequenceBuilder((byte)4);
        sequenceBuilder.append((byte)inputStream.read());

        //Getting nr of Servers.
        byteArray = new byte[2];
        byteArray[0] = (byte)inputStream.read();
        byteArray[1] = (byte)inputStream.read();
        nrOfServers = ((byteArray[0] & 0xff) << 8) | (byteArray[1] &
                0xff);
        sequenceBuilder.append(byteArray);

        //Collects information
        serverList = new LinkedList<>();
        for(int i = 0;i < nrOfServers;i++){

            //gets adress of server.
            byteArray = new byte[4];
            byteArray[0] = (byte)inputStream.read();
            byteArray[1] = (byte)inputStream.read();
            byteArray[2] = (byte)inputStream.read();
            byteArray[3] = (byte)inputStream.read();

            sequenceBuilder.append(byteArray);
            InetAddress temp = InetAddress.getByAddress(byteArray);
            String ipAdress = temp.getHostAddress();

            //gets port of server.
            byteArray = new byte[2];
            byteArray[0] = (byte)inputStream.read();
            byteArray[1] = (byte)inputStream.read();

            sequenceBuilder.append(byteArray);
            int portNr = ((byteArray[0] & 0xff) << 8) | (byteArray[1] &
                    0xff);

            //gets number of clients on the server.
            int nrOfClients = inputStream.read();
            sequenceBuilder.append((byte)nrOfClients);

            //gets server name length.
            int serverNameLength = inputStream.read();
            sequenceBuilder.append((byte)serverNameLength);

            //Gets servername.
            byteArray = new byte[serverNameLength];
            inputStream.read(byteArray,0,serverNameLength);
            sequenceBuilder.append(byteArray);
            String serverName = new String(byteArray,
                    StandardCharsets.UTF_8);

            //takes care of remaining padding
            if(serverNameLength%4 !=0){
                for(int j = 0;j < (4 - (serverNameLength%4));j++){
                    byte testByte = (byte)inputStream.read();
                    if(testByte != 0){
                        throw new IllegalArgumentException("The format " +
                                "of the PDU is wrong.");
                    }
                    sequenceBuilder.append(testByte);
                }
            }
            Server server = new Server(ipAdress,portNr,nrOfClients,
                    serverName);
            serverList.add(server);
        }
        bytes = sequenceBuilder.toByteArray();

    }

    public void printInfo(){
        for (int i = 0; i < nrOfServers; i++){
            Server currentServer = serverList.get(i);

            System.out.println("Server nr: " + (i+1));
            System.out.println("Server Name: "+ currentServer
                    .getServerName());
            System.out.println("Server IP: "+ currentServer.getAddress());
            System.out.println("Server Port: "+currentServer
                    .getPort());
            System.out.println("Nr of Clients: "+ currentServer
                    .getNrOfClients() +"\n");
        }
    }

    private class Server{
        private String address;
        private int port;
        private int nrOfClients;
        private String serverName;

        public Server(String address, int port, int nrOfClients,
                      String serverName){

            this.address = address;
            this.port = port;
            this.nrOfClients = nrOfClients;
            this.serverName = serverName;
        }

        public String getAddress() {
            return address;
        }

        public int getPort() {
            return port;
        }

        public int getNrOfClients() {
            return nrOfClients;
        }

        public String getServerName() {
            return serverName;
        }

    }
}
