package Audio;

import tinysound.Music;
import tinysound.Sound;
import tinysound.TinySound;

public class AudioLoader implements Runnable{

    public static Music damageAsteroid, damageEnemyShip, bgMusic, bgTalkMomentSpaceInvaders, bgMusicSpaceInvaders;
    public static Sound shot, upMenu;

    public static void init() {
        bgMusic = TinySound.loadMusic("/Music/menuMusic.wav");
        bgTalkMomentSpaceInvaders = TinySound.loadMusic("/Music/spaceMusicIntro.wav");
        shot = TinySound.loadSound("/SFX/spaceShot.wav");
        upMenu = TinySound.loadSound("/SFX/menuSelection.wav");
        bgMusicSpaceInvaders = TinySound.loadMusic("/Music/spaceInvadersRace.wav");
        damageAsteroid = TinySound.loadMusic("/SFX/explosionAsteroid.wav");
        damageEnemyShip = TinySound.loadMusic("/SFX/explosionEnemy.wav");
    }

    @Override
    public void run() {
        init();
    }

}
