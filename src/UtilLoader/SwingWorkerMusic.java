package UtilLoader;

import javax.swing.SwingWorker;
import tinysound.Music;

public class SwingWorkerMusic extends SwingWorker<Void, Void> {

    private Music music;

    public SwingWorkerMusic(Music music) {
        this.music = music;
    }

    @Override
    protected Void doInBackground() throws Exception {
        music.play(true,0.5f);
        return null;
    }
}
