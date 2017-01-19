package Pdu;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Course: Datakommutikation och internet 5DV167
 * Assignment: OU3
 * Written by: Kristoffer & Viyan
 * Version: 19/1 -17.
 */

/**
 * Client class that represents a chat client. Receives and and
 * sends messages to a chat server or name server.
 */
public class Client {

    private int port;
    private InetAddress ipAddress;
    private Scanner scanner;
    private String idString;
    private Socket socket;
    private int chatPort;
    private InetAddress chatIP;
    private LinkedBlockingDeque <Pdu> inQueue;


    public Client(String[] input)  {
        /*Sets the correct values, an initializes variables for the
         client */
        idString = input[0];
        String serverType = input[1];

        try {
            ipAddress = InetAddress.getByName(input[2]);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println("the entered host is not known " +
                    "please restart the application");
            System.exit(1);
        }
        port = Integer.parseInt(input[3]);
        inQueue = new LinkedBlockingDeque<>();
        scanner = new Scanner(System.in);


        //tests so the user has entered the right input.
        testInput(idString,serverType,port);

        //if client connects to name server first
        if(serverType.equals("ns")){

            try {
                connectToNameServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                chooseChatServer();
            } catch (UnknownHostException e) {
                e.printStackTrace();
                System.out.println("the chosen server is not known," +
                        " please start over");
                System.exit(1);
            }
            // connect to ChatServer
            try {
                socket = new Socket(chatIP,chatPort);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("were unable to connect to " +
                        "server");
            }
            //starts the chat
            chatOutput(socket);
            chatInput(socket);
            chatPrint();

        //if client connects to chat server directly
        }else if(serverType.equals("cs")){

            chatPort = port;
            chatIP = ipAddress;
            //connect to chatServer
            try {
                socket = new Socket(chatIP,chatPort);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("were unable to connect to " +
                        "server");
            }
            //starts the chat
            chatOutput(socket);
            chatInput(socket);
            chatPrint();
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
     * Method that connects to nameServer, sends a getList and
     * receives a sList.
     * @throws IOException if socket cannot connect.
     */
    private void connectToNameServer() throws IOException {

        socket = new Socket(ipAddress,port);
        PduInputStream inputStream = new PduInputStream(socket
                .getInputStream());
        PduOutputStream outputStream = new PduOutputStream(socket
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
                if(!(incomingPdu.getClass().equals(PduSlist.class))){
                    throw new IllegalArgumentException("Wrong pdu " +
                            "received!");
                }else{
                    incomingPdu.printInfo();
                    waiting = false;
                }
            }
        }
        socket.close();
    }

    /**
     * Method that gets the ip and portNr to a chat server
     * from the user.
     * @throws UnknownHostException if IP address entered is not
     * correct format.
     */
    private void chooseChatServer() throws UnknownHostException {
        String userInput = "zero";

        while (!userInput.equals("join")){
            System.out.println("Type 'join' to connect to a chat" +
                    " server or 'quit' to exit.");
            userInput = scanner.nextLine();
            if (userInput.equals("quit")){
                System.out.println("user has chosen to exit, client" +
                        " shutting down");
                System.exit(1);
            }
        }
        //gets the ip entered by user
        System.out.println("Enter the Ip address for chatserver in " +
                "format <192.168.0.1>");
        userInput = scanner.nextLine();
        if(userInput.equals("quit")){
            System.out.println("You have choosen to exit the " +
                    "application, goodbye.");
            System.exit(0);
        }else{
            chatIP = InetAddress.getByName(userInput);
        }

        //gets the port-nr entered by user
        System.out.println("Enter the port number for the " +
                "chatserver: ");
        userInput = scanner.nextLine();
        if(userInput.equals("quit")){
            System.out.println("You have choosen to exit the " +
                    "application, goodbye.");
            System.exit(0);
        }else{
            chatPort = Integer.parseInt(userInput);
        }
    }

    /**
     * Method that handles the output of the ChatClient, uses a thread.
     * @param socket that the client will send pdu to.
     */
    private void chatOutput(Socket socket){

        Thread outputThread = new Thread(){
            public void run(){
                try {
                    PduOutputStream outputStream = new PduOutputStream(socket
                            .getOutputStream());
                    PduJoin joinRequest = new PduJoin(idString);
                    outputStream.sendPDU(joinRequest);
                    String messageString;
                    boolean sending = true;
                    while(sending){
                        messageString = scanner.nextLine();

                        if(messageString.equals("quit")){
                            PduQuit quitMessage = new PduQuit();
                            outputStream.sendPDU(quitMessage);
                            System.out.println("you have exit the " +
                                    "chat, Goodbye!");
                            sending = false;
                        }

                        PduMess messagePdu = new PduMess(idString,
                                messageString);
                        outputStream.sendPDU(messagePdu);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Lost contact with server.");
                    System.exit(1);
                }
            }
        };outputThread.start();
    }

    /**
     * Method that handles the input of the ChatClient, uses a thread.
     * @param socket that the client will receive pdu from.
     */
    private void chatInput(Socket socket){

        Thread inputThread = new Thread(){

            public void run(){
                try {
                    PduInputStream inputStream = new PduInputStream
                            (socket.getInputStream());
                    boolean receiving = true;
                    Pdu receivedPdu;

                    while (receiving){
                        if(inputStream.hasPdu()){
                            receivedPdu = inputStream.readPdu();
                            if(receivedPdu.getClass().equals
                                    (PduCorrupt.class) || receivedPdu
                                    .getClass().equals(PduQuit.class)){
                                receiving = false;
                            }
                            inQueue.add(receivedPdu);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("the client has been shut " +
                            "down.");
                    System.exit(1);
                }
            }
        };inputThread.start();
    }

    /**
     * Method that prints the info from the received pdu to the user.
     */
    private void chatPrint(){

        boolean chatRunning = true;
        Pdu PduToPrint;

        while (chatRunning){
            if(!inQueue.isEmpty()){
                try {
                   PduToPrint = inQueue.take();
                    if(PduToPrint.getClass().equals(PduQuit.class) ||
                            PduToPrint.getClass().equals(PduCorrupt.class)){
                        PduToPrint.printInfo();
                        chatRunning = false;
                        System.exit(0);
                    }
                    PduToPrint.printInfo();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}