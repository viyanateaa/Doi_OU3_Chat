package Pdu;

import java.net.InetAddress;
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

    public Client(String[] input) throws UnknownHostException {
        ID = input[0];
        String serverType = input[1];
        ipAddress = InetAddress.getByName(input[2]);
        port = Integer.parseInt(input[3]);
        scanner = new Scanner(System.in);



    }
}
