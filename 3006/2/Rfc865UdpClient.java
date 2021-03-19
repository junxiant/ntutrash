import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Rfc865UdpClient {

    static int QOTD_p = 17;
    static String SERVER_NAME = "HWLab1";

    public static void main(String[] args) {

        /* Open UDP socket with server */
        DatagramSocket socket = null;
        try {
            InetAddress serverAddress = InetAddress.getByName(SERVER_NAME);
			//InetAddress serverAddress = InetAddress.getLocalHost();
            socket = new DatagramSocket();
            socket.connect(serverAddress, QOTD_p);
            System.out.println("UDP Client connected on port " + QOTD_p + " to server: " + serverAddress.getCanonicalHostName());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        try {
            /* Prepare request content */
            String content = "naam, group, ip";
            byte[] buf = content.getBytes("UTF-8");
            System.out.println("Content to send: " + content);

            /* Send UDP request to server */
            DatagramPacket request = new DatagramPacket(buf, buf.length);
            System.out.println("Sending request...");
            socket.send(request);
            System.out.println("Request sent to server");

            /* Receive UDP reply from server */
            byte[] replyBuf = new byte[512];
            DatagramPacket reply = new DatagramPacket(replyBuf, replyBuf.length);
            System.out.println("Waiting for reply...");
            socket.receive(reply);

            /* Process the reply */
            String replyContent = new String(replyBuf);
            System.out.println("Received reply: " + replyContent);
			
			//qotd: haha

        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}