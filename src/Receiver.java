import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Receiver {
    public static void main(String[] args) throws Exception {
        InetAddress IPAddress = InetAddress.getByName("127.0.0.1");
        int port = 5000;
        DatagramSocket socket = new DatagramSocket(port, IPAddress);
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        // Ses formatı oluşturma
        AudioFormat format = new AudioFormat(44100, 16, 2, true, false);

        // SourceDataLine oluşturma
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
        line.open(format);
        line.start();

        while (true) {
            socket.receive(packet);
            System.out.println(packet.getData());
            line.write(packet.getData(), 0, packet.getLength());
        }
    }
}
