import java.net.*;
import java.util.*;

class RPCServer {

    private static Set<Integer> reservedRooms = new HashSet<>();

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(1200);
            byte[] buffer = new byte[4096];

            System.out.println("Server running...");

            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                String input = new String(request.getData(), 0, request.getLength());
                System.out.println("Request: " + input);

                StringTokenizer st = new StringTokenizer(input, " ");
                String command = st.nextToken();

                String response = "";

                if (command.equalsIgnoreCase("reserve")) {
                    int room = Integer.parseInt(st.nextToken());

                    synchronized (reservedRooms) {
                        if (!reservedRooms.contains(room)) {
                            reservedRooms.add(room);
                            response = "Room reserved successfully";
                        } else {
                            response = "Room already reserved";
                        }
                    }
                }

                byte[] res = response.getBytes();

                DatagramPacket reply = new DatagramPacket(
                        res, res.length,
                        request.getAddress(),
                        request.getPort()
                );

                socket.send(reply);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
