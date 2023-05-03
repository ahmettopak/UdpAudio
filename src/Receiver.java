////import java.io.ByteArrayInputStream;
////import java.io.IOException;
////import java.net.*;
////import javax.sound.sampled.*;
////
////public class Receiver {
////    public static void main(String[] args) throws IOException, LineUnavailableException {
////        // Ses özellikleri
////        AudioFormat format = new AudioFormat(44100, 16, 2, true, false);
////        DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
////
////        // Ses çalma cihazı oluşturma
////        SourceDataLine speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
////        speakers.open(format);
////        speakers.start();
////
//////        byte [] ip = {192.168.1.5};
////        // Server soketi oluşturma
////        //InetAddress ipAdr = InetAddress.getByAddress(ip);
////        InetAddress address = InetAddress.getByName("192.168.1.5");
////        DatagramSocket serverSocket = new DatagramSocket(5000);
////
////        // Sonsuz döngü içerisinde datagramları alarak ses çalmaya başlama
////        byte[] buffer = new byte[4096];
////
////        System.out.println(serverSocket.getInetAddress());
////        while (true) {
////            System.out.println("girdi");
////            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
////
////            serverSocket.receive(packet);
////            System.out.println(packet.getAddress());
////            byte[] data = packet.getData();
////
////            AudioInputStream ais = new AudioInputStream(new ByteArrayInputStream(data), format, data.length);
////            byte[] audioBuffer = new byte[4096];
////            try {
////                int bytesRead = 0;
////                while ((bytesRead = ais.read(audioBuffer, 0, audioBuffer.length)) != -1) {
////                    speakers.write(audioBuffer, 0, bytesRead);
////                }
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////        }
////    }
////}
//
////
////import java.io.ByteArrayInputStream;
////import java.io.IOException;
////import java.net.DatagramPacket;
////import java.net.DatagramSocket;
////import java.net.SocketException;
////import javax.sound.sampled.*;
////
////public class Receiver {
////    public static void main(String[] args) throws  IOException, LineUnavailableException {
////        // Ses özellikleri
////        AudioFormat format = new AudioFormat(44100, 16, 1, true, false);
////        DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
////
////        // Ses çalma cihazı oluşturma
////        SourceDataLine speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
////        speakers.open(format);
////        speakers.start();
////
////        // Server soketi oluşturma
////        DatagramSocket serverSocket = new DatagramSocket(5000);
////
////        // Sonsuz döngü içerisinde datagramları alarak ses çalmaya başlama
////        byte[] buffer = new byte[4096];
////        DatagramPacket packet = new DatagramPacket(buffer,4096);
////        while (true) {
////            System.out.println("sdfsf");
////            serverSocket.receive(packet);
////            byte[] data = packet.getData();
////
////            System.out.println(packet.getData());
////            AudioInputStream ais = new AudioInputStream(new ByteArrayInputStream(data), format, 4096);
////            byte[] audioBuffer = new byte[4096];
////            try {
////                int bytesRead = 0;
////                while ((bytesRead = ais.read(audioBuffer, 0, audioBuffer.length)) != -1) {
////                    speakers.write(audioBuffer, 0, bytesRead);
////
////                }
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////        }
////    }
////}
//
//
////
////import java.io.ByteArrayInputStream;
////import java.net.DatagramPacket;
////import java.net.DatagramSocket;
////import java.net.SocketException;
////import javax.sound.sampled.AudioFormat;
////import javax.sound.sampled.AudioInputStream;
////import javax.sound.sampled.AudioSystem;
////import javax.sound.sampled.DataLine;
////import javax.sound.sampled.SourceDataLine;
////
////public class Receiver {
////    public static void main(String[] args) throws SocketException {
////        // Ses özellikleri
////        AudioFormat format = new AudioFormat(44100, 16, 2, true, false);
////        DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
////
////        // Ses çalma cihazı oluşturma
////        SourceDataLine speakers = null;
////        try {
////            speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
////            speakers.open(format);
////            speakers.start();
////        } catch (Exception e) {
////            System.err.println("Hata: " + e.getMessage());
////            System.exit(1);
////        }
////
////        // Client soketi oluşturma
////        DatagramSocket clientSocket = null;
////        try {
////            clientSocket = new DatagramSocket();
////        } catch (Exception e) {
////            System.err.println("Hata: " + e.getMessage());
////            System.exit(1);
////        }
////
////        // UDP paketi oluşturma
////        byte[] sendData = new byte[4096];
////
////        // Sonsuz döngü içerisinde ses verilerini okuyarak UDP paketi oluşturma ve gönderme
////        int numBytesRead;
////        byte[] data = new byte[4096];
////        DatagramPacket receivePacket = new DatagramPacket(data, data.length);
////        try {
////            DatagramSocket serverSocket = new DatagramSocket(5000);
////            while (true) {
////                serverSocket.receive(receivePacket);
////                numBytesRead = receivePacket.getLength();
////                System.arraycopy(data, 0, sendData, 0, numBytesRead);
////                clientSocket.send(new DatagramPacket(sendData, numBytesRead, receivePacket.getAddress(), receivePacket.getPort()));
////                AudioInputStream ais = new AudioInputStream(new ByteArrayInputStream(sendData), format, numBytesRead);
////                byte[] audioBuffer = new byte[4096];
////                try {
////                    int bytesRead = 0;
////                    while ((bytesRead = ais.read(audioBuffer, 0, audioBuffer.length)) != -1) {
////                        speakers.write(audioBuffer, 0, bytesRead);
////                    }
////                } catch (Exception e) {
////                    System.err.println("Hata: " + e.getMessage());
////                    System.exit(1);
////                }
////            }
////        } catch (Exception e) {
////            System.err.println("Hata: " + e.getMessage());
////            System.exit(1);
////        }
////    }
////}
//
//
//import java.io.ByteArrayInputStream;
//import java.net.DatagramPacket;
//import java.net.DatagramSocket;
//import java.net.InetAddress;
//import javax.sound.sampled.AudioFormat;
//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.DataLine;
//import javax.sound.sampled.SourceDataLine;
//
//public class Receiver {
//    public static void main(String[] args) throws Exception {
//        int port = 5000;
//        int sampleRate = 44100;
//        int channels = 2;
//        AudioFormat format = new AudioFormat(sampleRate, 16, channels, true, false);
//        DataLine.Info speakerInfo = new DataLine.Info(SourceDataLine.class, format);
//
//        DatagramSocket serverSocket = new DatagramSocket(port);
//        byte[] receiveData = new byte[4096];
//
//        SourceDataLine speaker = (SourceDataLine) AudioSystem.getLine(speakerInfo);
//        speaker.open(format);
//        speaker.start();
//
//
//        while (true) {
//            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
//            System.out.println(speaker.isOpen());
//
//            serverSocket.receive(receivePacket);
//            System.out.println(receivePacket);
//            byte[] audioData = receivePacket.getData();
//            AudioInputStream audioStream = new AudioInputStream(new ByteArrayInputStream(audioData), format,
//                    audioData.length / format.getFrameSize());
//            byte[] buffer = new byte[4096];
//            int count;
//            while ((count = audioStream.read(buffer, 0, buffer.length)) != -1) {
//                speaker.write(buffer, 0, count);
//            }
//          //  audioStream.close();
//        }
//    }
//}

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Receiver {

    public static void main(String[] args) {

        // Set up UDP socket
        int UDP_PORT = 5000; // Port number to use
        DatagramSocket socket;
        try {
            socket = new DatagramSocket(UDP_PORT);

            // Set up byte buffer
            byte[] buffer = new byte[1024];

            // Set up audio player
            AudioPlayer player = new AudioPlayer();

            while (true) {
                // Receive UDP packet
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                // Play audio data
                player.playAudio(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
