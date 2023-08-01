package auxiliary;

import Content.PlayerStateEnum;

public class AudioChecker {
    static int a = 0 ;
    public static void checkAudio(PlayerStateEnum playerStateEnum){
        if(!Audio.BGM.isPlaying())
            Audio.BGM.play();
        if(playerStateEnum == PlayerStateEnum.HURT){
            if(a == 0){
                Audio.Hurt.play();
                a++;
            }
        }else a = 0;
        if(playerStateEnum == PlayerStateEnum.RUN){
            if(!Audio.Run.isPlaying()){
                Audio.Fly.stop();
                Audio.Run.play();
            }
        } else if (playerStateEnum == PlayerStateEnum.FLY) {
            if(!Audio.Fly.isPlaying()){
                Audio.Run.stop();
                Audio.Fly.play();
            }
        }else if (playerStateEnum == PlayerStateEnum.CALL_BACK){
            if(!Audio.Callback.isPlaying()){
                Audio.Run.stop();
                Audio.Fly.stop();
                Audio.Fall.stop();
                Audio.Hurt.stop();
                Audio.Callback.play();
            }
        }

    }
}
