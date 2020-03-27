package Audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class AudioPlayer implements Runnable {

    public Clip clip;
    public FloatControl gainControl;
    
    public AudioPlayer(AudioInputStream audio, int volume){
        try{
            this.clip = AudioSystem.getClip();
            clip.open(audio);
        }catch(Exception e){
            e.printStackTrace();
        }
        gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(volume);
    }
    
    public void play(){
        if(clip == null){
            return;
        }
        clip.setFramePosition(0);
        clip.start();
    }
    
    public void stop(){
        if(clip.isRunning()) clip.stop();
    }
    
    public void close(){
        stop();
        clip.close();
    }

    @Override
    public void run() {
        play();
    }

}
