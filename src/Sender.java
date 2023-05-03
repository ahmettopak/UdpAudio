import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import java.io.IOException;
import java.net.*;

public class Sender {
    public static void main(String[] args) throws LineUnavailableException, IOException {

        // AudioFormat nesnesi oluşturuluyor. Bu, mikrofondan alınacak sesin özelliklerini tanımlar.
        AudioFormat format = new AudioFormat(44100, 16, 2, true, false);

        // AudioSystem.getTargetDataLine() metodu, sistemdeki varsayılan kaynak veri hattını döndürür.
        TargetDataLine microphone = AudioSystem.getTargetDataLine(format);

        // Mikrofon veri hattı açılıyor.
        microphone.open(format);

        // Mikrofon veri akışı başlatılıyor.
        microphone.start();

        // Ses verileri okunacak boyutta bir byte dizisi oluşturuluyor.
        byte[] audioData = new byte[4096];

        // Mikrofondan ilk okuma yapılıyor.
        int bytesRead = microphone.read(audioData, 0, audioData.length);

        // Alıcı adresi ve bağlantı noktası belirleniyor.
        InetAddress address = InetAddress.getByName("192.168.1.6");
        int port = 5000;

        // Datagram soketi oluşturuluyor.
        DatagramSocket socket = new DatagramSocket();

        // Veri paketi oluşturuluyor. İlk olarak, okunan ses verileri, alıcı adresi ve bağlantı noktası parametreleriyle pakete konuluyor.
        DatagramPacket packet = new DatagramPacket(audioData, bytesRead, address, port);

        // Veri paketi soket aracılığıyla gönderiliyor.
        socket.send(packet);

        // Sonsuz bir döngü başlatılıyor.
        while (true) {
            // Ses verileri tekrar okunuyor.
            bytesRead = microphone.read(audioData, 0, audioData.length);

            // Paketin verileri güncelleniyor.
            packet.setData(audioData);
            packet.setLength(bytesRead);

            // Paket soket aracılığıyla gönderiliyor.
            socket.send(packet);
        }
    }
}