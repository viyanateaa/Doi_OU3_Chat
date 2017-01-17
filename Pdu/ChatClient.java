package Pdu;

/**
 * Created by kristoffer on 2017-01-17.
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
