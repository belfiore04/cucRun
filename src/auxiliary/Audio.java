package auxiliary;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public enum Audio {
    BGM("bgm.wav"),
    GameOver(""),
    Callback("callback.wav"),
    Run("run.wav"),
    Fly("fly.wav"),
    Fall("fall.wav"),
    Hurt("hurt.wav"),
    Coin("coin.wav");
    private String name;
    private Clip clip;
    Audio(String name){
        this.name = name;
        try {
            File audioFile = new File(CommonConstants.RESOURCES_PATH+"sound/"+name);
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(audioFile));
        } catch (Exception e) {
            System.out.println("Error playing audio: " + e.getMessage());
        }
    }

    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }
    public void stop(){
        clip.stop();
    }
    public boolean isPlaying(){
        return clip != null && clip.isRunning();
    }
}
