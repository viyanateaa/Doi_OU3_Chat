package Pdu;

/**
 * Course: Datakommutikation och internet 5DV167
 * Assignment: OU3
 * Written by: Kristoffer & Viyan
 * Version: 19/1 -17.
 */

/**
 * Class that contains main function that starts Chatclient program.
 * @throws IllegalArgumentException if wrong nr of arguments are used.
 */
public class ChatClient {

    public static void main(String[] args){

        if(args.length == 4){
            Client client = new Client(args);
        }else
            throw new IllegalArgumentException("Wrong nr of " +
                    "arguments for program \n it should be " +
                    "'identity' 'cs/ns' 'server name' 'port nr'");
    }
}
