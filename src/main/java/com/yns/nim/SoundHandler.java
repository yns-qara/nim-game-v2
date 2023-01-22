package com.yns.nim;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URISyntaxException;
import java.util.Objects;


public class SoundHandler {

    public static void play(String path) throws URISyntaxException {
        Media media = new Media(Objects.requireNonNull(SoundHandler.class.getResource(path)).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.seek(mediaPlayer.getStartTime());
    }
}
