package Audio;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

public class AudioLoader{
    
    public static Music bgMusic,bgTalkMomentSpaceInvaders,bgMusicSpaceInvaders;
    public static Sound shot,upMenu;
    
    public static void init(){
        bgMusic = TinySound.loadMusic("/Music/menuMusic.wav");
        bgTalkMomentSpaceInvaders = TinySound.loadMusic("/Music/spaceMusicIntro.wav");
        shot = TinySound.loadSound("/SFX/spaceShot.wav");
        upMenu = TinySound.loadSound("/SFX/menuSelection.wav");
        bgMusicSpaceInvaders = TinySound.loadMusic("/Music/spaceInvadersRace.wav");
    }
}
