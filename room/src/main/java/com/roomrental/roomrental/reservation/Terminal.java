package reservation;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.BlockingQueue;


public class Terminal implements Runnable {
    protected Server server;
    protected int terminalId;
    protected BlockingQueue<Request> requests;
    protected BlockingQueue<Response> responses;

    public Terminal(int terminalId, BlockingQueue<Request> requests, 
                    BlockingQueue<Response> responses, Server server) {
        this.terminalId = terminalId;
        this.requests = requests;
        this.responses = responses;
        this.server = server;
        this.server.connect(this.terminalId, this.responses);
    }

    @Override
    public void run() {
        try {
            requests.put(new Request(this.terminalId, "getReservationStatus", null));
            requests.put(new Request(this.terminalId, "book", 2)); 
            requests.put(new Request(this.terminalId, "book", 3)); 
            requests.put(new Request(this.terminalId, "getReservationStatus", null));

            while (true) {
                Response res = responses.take();
                
                switch (res.function) {
                    case "getReservationStatus":
                        TreeMap<Integer, Boolean> reservations = (TreeMap<Integer, Boolean>) res.data;
                        System.out.println("Terminal " + terminalId + " - Status updated.");
                        break;
                        
                    case "book":
                        SimpleEntry<Integer, Boolean> data = (SimpleEntry<Integer, Boolean>) res.data;
                        String result = data.getValue() ? "SUCCESSFUL" : "FAILED";
                        System.out.println("Terminal " + terminalId + ": Booking room " + data.getKey() + " -> " + result);
                        break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}