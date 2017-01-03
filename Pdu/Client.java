package Pdu;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by kristoffer on 2017-01-03.
 */
public class Client {

    private int port;
    private InetAddress ipAddress;
    private Scanner scanner;
    private String ID;
    private Socket socket;

    public Client(String[] input) throws IOException {
        ID = input[0];
        String serverType = input[1];
        ipAddress = InetAddress.getByName(input[2]);
        port = Integer.parseInt(input[3]);
        scanner = new Scanner(System.in);

        //tests so the user has entered the right input.
        testInput(ID,serverType,port);

        if(serverType.equals("ns")){

            connectToNameServer();
            chooseChatServer();

        }else if(serverType.equals("cs")){

        }




    }
    /**
     * Method that test the client input
     * @param identity variable to be tested.
     * @param serverType variable to be tested.
     * @param port variable to be tested.
     */
    private void testInput(String identity, String serverType, int
            port){
        if(identity.length() > 255){
            throw new IllegalArgumentException("Identity is larger " +
                    "then 255 characters.");
        }
        if(!serverType.equals("cs")&&!serverType.equals("ns")){
            throw new IllegalArgumentException("wrong type of " +
                    "serverInput, should be <cs> or <ns>");
        }
        if(port < 1 || port > 65535){
            throw new IllegalArgumentException("The port number is " +
                    "wrong, should be between 1 - 65535 ");
        }
    }

    /**
     * Method that connects to nameServer
     * @throws IOException if socket cannot connect.
     */
    private void connectToNameServer() throws IOException {

        socket = new Socket(ipAddress,port);
        PDUInputStream inputStream = new PDUInputStream(socket
                .getInputStream());
        PDUOutputStream outputStream = new PDUOutputStream(socket
                .getOutputStream());

        //send GetList to name server.
        outputStream.sendPDU(new GetList());

        boolean waiting = true;
        Pdu incomingPdu;
        //waiting for PduSlist
        while(waiting){
            if(inputStream.hasPdu()){
                incomingPdu = inputStream.readPdu();
                //test so a PduSlist has been returned.
                if(!(incomingPdu instanceof PduSlist)){
                    throw new IllegalArgumentException("Wrong pdu " +
                            "received!");
                }else{
                    incomingPdu.printInfo();
                }
                waiting = false;
            }
        }
        socket.close();
    }

    /**
     * Method that gets the ip and portNr to a chatserve
     * from the user.
     * @throws UnknownHostException
     */
    private void chooseChatServer() throws UnknownHostException {
        String userInput = null;

        while (!userInput.equals("connect")){
            System.out.println("Type 'connect' to connect to a chat" +
                    " server or quit to exit.");
            userInput = scanner.nextLine();
            if (userInput.equals("quit")){
                System.out.println("user has chosen to exit, client" +
                        " shutting down");
                System.exit(1);
            }
        }
        //gets the ip entered by user
        System.out.println("Enter the Ip address for chatserver: ");
        userInput = scanner.nextLine();
        ipAddress = InetAddress.getByName(userInput);

        //gets the port-nr entered by user
        System.out.println("Enter the port number for the " +
                "chatserver: ");
        userInput = scanner.nextLine();
        port = Integer.parseInt(userInput);
    }
}
