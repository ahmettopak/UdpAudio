import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;

public class AudioPlayer {

    public void playAudio(byte[] audioData) {

        // Set up audio format
        AudioFormat format = new AudioFormat(44100, 16, 2, true, false);

        try {
            // Set up audio input stream
            ByteArrayInputStream bais = new ByteArrayInputStream(audioData);
            AudioInputStream audioInputStream = new AudioInputStream(bais, format, audioData.length / format.getFrameSize());

            // Set up audio player
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceDataLine.open(format);
            sourceDataLine.start();

            // Play audio
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = audioInputStream.read(buffer)) != -1) {
                sourceDataLine.write(buffer, 0, bytesRead);
            }

            // Clean up
            sourceDataLine.drain();
            sourceDataLine.stop();
            sourceDataLine.close();
            audioInputStream.close();
            bais.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
