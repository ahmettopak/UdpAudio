import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import java.io.IOException;
import java.net.*;

public class Main {
    public static void main(String[] args) throws LineUnavailableException, IOException {

        AudioFormat format = new AudioFormat(44100, 16, 2, true, false);
        TargetDataLine microphone = AudioSystem.getTargetDataLine(format);
        microphone.open(format);
        microphone.start();

        byte[] audioData = new byte[4096];
        int bytesRead = microphone.read(audioData, 0, audioData.length);


        InetAddress address = InetAddress.getByName("192.168.1.6");
        int port = 5000;
        DatagramSocket socket = new DatagramSocket();

        DatagramPacket packet = new DatagramPacket(audioData, bytesRead, address, port);
        socket.send(packet);

        while (true) {
            bytesRead = microphone.read(audioData, 0, audioData.length);
            packet.setData(audioData);
            packet.setLength(bytesRead);
            socket.send(packet);

        }
    }
}