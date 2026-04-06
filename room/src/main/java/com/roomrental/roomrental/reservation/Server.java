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
            reservations.put(i, (i < 2));         }
    }

    public void connect(int terminalId, BlockingQueue<Response> responseQueue) {
        this.responses.put(terminalId, responseQueue);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Request req = requests.take(); 
                
                switch (req.function) {
                    case "getReservationStatus":
                        responses.get(req.terminalId).put(new Response(req.function, this.getReservationStatus()));
                        break;
                        
                    case "reserveRoom": 
                        int roomId = (int) req.args; 
                        SimpleEntry<Integer, Boolean> entry = new SimpleEntry<>(roomId, reserveRoom(roomId)); 
                        responses.get(req.terminalId).put(new Response(req.function, entry)); 
                        break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace(); 
        }
    }

    public TreeMap<Integer, Boolean> getReservationStatus() {
        TreeMap<Integer, Boolean> copy = new TreeMap<>();
        copy.putAll(reservations);
        return copy; 
    }

    public synchronized boolean reserveRoom(int roomId) {
        if (reservations.containsKey(roomId) && !reservations.get(roomId)) { 
            reservations.put(roomId, true);
            return true; 
        }
        return false; 
    }
}