package UtilLoader;

import javax.swing.SwingWorker;
import tinysound.Music;

public class SwingWorkerMusic extends SwingWorker<Void, Void> {

    private static Music music;
    private boolean loop;
    
    public SwingWorkerMusic(Music music) {
        SwingWorkerMusic.music = music;
        this.loop = false;
    }
    
    public SwingWorkerMusic(Music music, boolean loop){
        SwingWorkerMusic.music = music;
        this.loop = loop;
    }

    @Override
    protected Void doInBackground() throws Exception {
        while (!isCancelled()) {
            SwingWorkerMusic.music.play(loop, 0.5f);
        }
        music = null;
        return null;
    }

}
