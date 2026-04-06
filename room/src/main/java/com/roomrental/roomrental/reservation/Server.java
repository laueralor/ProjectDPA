package reservation;

import java.util.AbstractMap.SimpleEntry;
import java.util.TreeMap;
import java.util.concurrent.BlockingQueue;


public class Server implements Runnable {
    protected TreeMap<Integer, Boolean> reservations;
    protected BlockingQueue<Request> requests;
    protected TreeMap<Integer, BlockingQueue<Response>> responses;

    public Server(BlockingQueue<Request> requests) {
        this.requests = requests;
        this.responses = new TreeMap<Integer, BlockingQueue<Response>>();
        this.reservations = new TreeMap<Integer, Boolean>();

        for (int i = 0; i < 10; i++) {
            reservations.put(i, (i < 2)); 
        }
    }

    public void connect(int terminalId, BlockingQueue<Response> responseQueue) {
        this.responses.put(terminalId, responseQueue);
    }

}