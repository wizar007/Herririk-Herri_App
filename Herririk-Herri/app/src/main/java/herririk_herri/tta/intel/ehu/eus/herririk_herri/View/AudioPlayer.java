package herririk_herri.tta.intel.ehu.eus.herririk_herri.View;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.View;
import android.widget.MediaController;;import java.io.IOException;

/**
 * Created by mikel on 2/01/17.
 */
public class AudioPlayer implements MediaController.MediaPlayerControl, MediaPlayer.OnPreparedListener  {
    private View view;
    private MediaPlayer player;
    private MediaController controller;

    public AudioPlayer(View view, final Runnable onExit)
    {
        this.view=view;
        player = new MediaPlayer();
        player.setOnPreparedListener(this);
        controller= new MediaController(view.getContext()) {
            @Override
            public boolean dispatchKeyEvent(KeyEvent event)
            {
                if (event.getKeyCode()== KeyEvent.KEYCODE_BACK)
                {
                    release();
                    onExit.run();
                }
                return super.dispatchKeyEvent(event);
            }
        };

    }



    @Override
    public void onPrepared(MediaPlayer mp)
    {
        controller.setMediaPlayer(this);
        controller.setAnchorView(view);
        controller.show(0);//siempre visible
    }

    @Override
    public void start()
    {
        player.start();
    }

    @Override
    public void pause()
    {
        player.pause();
    }

    public void setAudioUri(Uri uri) throws IOException
    {
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setDataSource(view.getContext(),uri);
        player.prepare();
        player.start();

    }

    public void release()
    {
        if(player != null)
        {
            player.stop();
            player.release();
            player=null;
        }
    }


    public int getDuration() {
        return 0;
    }


    public int getCurrentPosition() {
        return 0;
    }


    public void seekTo(int i) {

    }


    public boolean isPlaying() {
        return true;
    }


    public int getBufferPercentage() {
        return 0;
    }


    public boolean canPause() {
        return true;
    }


    public boolean canSeekBackward() {
        return true;
    }

    public boolean canSeekForward() {
        return true;
    }

    public int getAudioSessionId() {
        return 0;
    }
}