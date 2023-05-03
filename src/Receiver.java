import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Receiver {
    public static void main(String[] args) throws Exception {

        // Alıcı adresi ve bağlantı noktası belirleniyor.
        InetAddress IPAddress = InetAddress.getByName("127.0.0.1");
        int port = 5000;

        // Datagram soketi oluşturuluyor.
        DatagramSocket socket = new DatagramSocket(port, IPAddress);

        // Ses verileri okunacak boyutta bir byte dizisi oluşturuluyor.
        byte[] buffer = new byte[1024];

        // Datagram paketi oluşturuluyor.
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        // Ses formatını belirleme: 44.1 kHz örnekleme hızı, 16 bit çözünürlük, çift kanallı
        AudioFormat format = new AudioFormat(44100, 16, 2, true, false);

        // SourceDataLine oluşturma: ses verilerini çalmak için kullanılır
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
        line.open(format);
        line.start();

        // Sonsuz döngü içinde, veri paketi alınır ve ses verileri çalınır
        while (true) {
            socket.receive(packet);  // Veri paketini al
            System.out.println(packet.getData()); // Gelen veriyi ekrana yazdır (opsiyonel)
            line.write(packet.getData(), 0, packet.getLength()); // Ses verilerini çal
        }
    }
}
