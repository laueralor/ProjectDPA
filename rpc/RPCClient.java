import java.io.*;
import java.net.*;

class RPCClient {

    public static void main(String[] args) {
        try {
            // Socket SIN puerto fijo (muy importante)
            DatagramSocket socket = new DatagramSocket();

            InetAddress address = InetAddress.getLocalHost();

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter: reserve <room_number>");

            while (true) {
                String input = br.readLine();

                byte[] data = input.getBytes();

                // enviar al servidor (puerto 1200)
                DatagramPacket request = new DatagramPacket(data, data.length, address, 1200);
                socket.send(request);

                // recibir respuesta
                byte[] buffer = new byte[4096];
                DatagramPacket response = new DatagramPacket(buffer, buffer.length);

                socket.receive(response);

                String result = new String(response.getData(), 0, response.getLength());
                System.out.println("Result: " + result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
