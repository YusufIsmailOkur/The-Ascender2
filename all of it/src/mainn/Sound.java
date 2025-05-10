package mainn;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
// 13 13 13 13 13 13 13 13 13 13 13 13 13 13 13 13 13 13 13 
//14 14 14 14 14 14 14 14 14 14 14 14 14 14 14 14 14 14 14 
// 17 16 16 15 16 17 17 16 15 15 17 17 15 16 16 15 16 15 16


public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/res/sound/titleMusic.wav");
        soundURL[1] = getClass().getResource("/res/sound/inGameMusic.wav");
        soundURL[2] = getClass().getResource("/res/sound/BossFightMusic.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    public void setVolume(float value) {
        if (clip != null) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(value * 10.0f - 80.0f);
        }
    }

}
