package Audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioLoader {
    
    private Clip clip;
    
    public static AudioInputStream loadAudio(String path){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(AudioLoader.class.getResource(path));
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false
            );
            AudioInputStream dais = AudioSystem.getAudioInputStream(
                    decodeFormat,ais);
            return dais;
        } catch (Exception e) {
            e.printStackTrace();
       }
       return null;
    }
}
