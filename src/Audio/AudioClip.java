package Audio;

import javax.sound.sampled.AudioInputStream;

public class AudioClip {
    
    public static AudioInputStream bgMusicTalk, bgMusic, movementMenu,movementMenuDown, spaceShot;
    
    public static void init(){
        
        bgMusicTalk = AudioLoader.loadAudio("/Music/spaceMusicIntro.mp3");
        bgMusic = AudioLoader.loadAudio("/Music/menuMusic.mp3");
        movementMenu = AudioLoader.loadAudio("/SFX/menuSelection.mp3");
        movementMenuDown = AudioLoader.loadAudio("/SFX/menuSelectionDown.mp3");
        spaceShot = AudioLoader.loadAudio("/SFX/spaceShot.mp3");
    }
}
