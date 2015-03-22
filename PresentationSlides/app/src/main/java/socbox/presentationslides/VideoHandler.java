package socbox.presentationslides;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.content.pm.ActivityInfo;
        import android.media.AudioManager;
        import android.media.MediaPlayer;
        import android.os.Handler;
        import android.util.DisplayMetrics;
        import android.util.Log;
        import android.view.SurfaceHolder;
        import android.view.SurfaceView;
        import android.widget.ImageButton;
        import android.widget.RelativeLayout;
        import android.widget.SeekBar;
        import android.widget.TextView;

        import java.io.IOException;

/**
 * Created by zharif20 on 3/20/15.
 */
public class VideoHandler implements AudioManager.OnAudioFocusChangeListener,MediaPlayer.OnPreparedListener, SurfaceHolder.Callback{

    SurfaceHolder surfaceHolder;
    SurfaceView surfaceView;
    Activity activity;

    public Utilities utilities;
    public AudioManager audioManager;
    public String vidUrl = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

    String TAG = "VideoPlayerActivityTAG";


    public ImageButton playMedia;
    public ImageButton muteMedia;
    public ImageButton stopMedia;
    public ImageButton fullscreenMedia;
    public SeekBar seekBar;
    public TextView currentTimeLabel;
    public TextView totalTimeLabel;
    boolean mFirst = true;
    public int position = 0;

    boolean AudioReady;
    public Handler mHandler = new Handler();

    boolean mFullScreen = false;

    public MediaPlayer mPlayer;
    RelativeLayout container;

    VideoHandler(Activity calledFrom) {

        activity = calledFrom;

        surfaceView = (SurfaceView) this.activity.findViewById(R.id.surface);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        loadVideoSource();
        setSize();
        setAudioFocus();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
//        mPlayer.setDisplay(null);
        mHandler.removeCallbacks(mUpdateTimeTask);

//        if (mPlayer  != null) {
//            mPlayer.stop();
        mPlayer.release();
//            mPlayer = null;
//        }
        position = 0;
    }

    public void loadVideoSource() {

        try {
            videoController();
            setMediaPlayer();
            utilities = new Utilities();
            mPlayer.setDataSource(vidUrl);
            mPlayer.setDisplay(surfaceHolder);
            AudioReady = false;
//            setAudioFocus();
            mPlayer.prepareAsync();

        } catch (IOException e) {
            Log.v(TAG,"Video source failed to load.");
            return;
        }
    }

    /**
     * Update timer on seekbar
     * */
    public void updateProgressBar() {
        mHandler.postDelayed(mUpdateTimeTask, 100);
    }

    /**
     * Background Runnable thread
     * */
    public Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            long totalDuration = mPlayer.getDuration();
            long currentDuration = mPlayer.getCurrentPosition();

            // Displaying Total Duration time
            totalTimeLabel.setText(""+utilities.milliSecondsToTimer(totalDuration));
            // Displaying time completed playing
            currentTimeLabel.setText(""+utilities.milliSecondsToTimer(currentDuration));

            // Updating progress bar
            int progress = (int)(utilities.getProgressPercentage(currentDuration, totalDuration));
            //Log.d("Progress", ""+progress);
            seekBar.setProgress(progress);

            // Running this thread after 100 milliseconds
            mHandler.postDelayed(this, 100);
        }
    };

    /**
     * When user starts moving the progress handler
     * */
    public void onStartTrackingTouch() {
        // remove message Handler from updating progress bar
        mHandler.removeCallbacks(mUpdateTimeTask);
    }

    public void setAudioFocus(){
        audioManager = (AudioManager)this.activity.getSystemService(Context.AUDIO_SERVICE);
        audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
    }

    public void setMediaPlayer(){

        mPlayer = new MediaPlayer();

        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        mPlayer.setOnPreparedListener(this);

    }

    public void setSize() {
        surfaceHolder.setFixedSize(mPlayer.getVideoWidth(), mPlayer.getVideoHeight());
    }

    public void videoController(){
        playMedia = (ImageButton)this.activity.findViewById(R.id.buttonPlay);
        playMedia.setImageResource(R.drawable.ic_media_pause);

        stopMedia = (ImageButton)this.activity.findViewById(R.id.buttonStop);
        stopMedia.setImageResource(R.drawable.ic_media_stop);


        muteMedia = (ImageButton)this.activity.findViewById(R.id.buttonMute);
        muteMedia.setImageResource(R.drawable.ic_media_unmute);


        fullscreenMedia = (ImageButton)this.activity.findViewById(R.id.buttonFullScreen);
        fullscreenMedia.setImageResource(R.drawable.ic_media_fullscreen_stretch);



        container = (RelativeLayout)this.activity.findViewById(R.id.mainRelativeLayout);
        surfaceView = (SurfaceView)this.activity.findViewById(R.id.surface);

//        forwardMedia = (ImageButton)findViewById(R.id.buttonForward);
//        backwardMedia = (ImageButton)findViewById(R.id.buttonBackward);
        seekBar = (SeekBar)this.activity.findViewById(R.id.seekBarMedia);
        currentTimeLabel = (TextView)this.activity.findViewById(R.id.currentTime);
        totalTimeLabel = (TextView)this.activity.findViewById(R.id.totalTime);

        // Listeners
        seekBar.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) this.activity); // Important
//        mPlayer.setOnCompletionListener(this);

    }

    public static class Utilities {
        /**
         * Function to convert milliseconds time to
         * Timer Format
         * Hours:Minutes:Seconds
         */
        public String milliSecondsToTimer(long milliseconds) {
            String finalTimerString = "";
            String secondsString = "";

            // Convert total duration into time
            int hours = (int) (milliseconds / (1000 * 60 * 60));
            int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
            int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
            // Add hours if there
            if (hours > 0) {
                finalTimerString = hours + ":";
            }

            // Prepending 0 to seconds if it is one digit
            if (seconds < 10) {
                secondsString = "0" + seconds;
            } else {
                secondsString = "" + seconds;
            }

            finalTimerString = finalTimerString + minutes + ":" + secondsString;

            // return timer string
            return finalTimerString;
        }

        /**
         * Function to get Progress percentage
         *
         * @param currentDuration
         * @param totalDuration
         */
        public int getProgressPercentage(long currentDuration, long totalDuration) {
            Double percentage = (double) 0;

            long currentSeconds = (int) (currentDuration / 1000);
            long totalSeconds = (int) (totalDuration / 1000);

            // calculating percentage
            percentage = (((double) currentSeconds) / totalSeconds) * 100;

            // return percentage
            return percentage.intValue();
        }

        /**
         * Function to change progress to timer
         *
         * @param progress      -
         * @param totalDuration returns current duration in milliseconds
         */
        public int progressToTimer(int progress, int totalDuration) {
//            int currentDuration = 0;
//            totalDuration = (int) (totalDuration / 1000);
//            currentDuration = (int) ((((double) progress) / 100) * totalDuration);

            // return current duration in milliseconds
//            return currentDuration * 1000;
            return (int) ((((double) progress) / 100) * totalDuration / 1000) * 1000;
        }
    }

    //Mute update
    public void setMute() {
        audioManager = (AudioManager)this.activity.getSystemService(Context.AUDIO_SERVICE);
        if (audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) == 0) {
            audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
            muteMedia.setImageResource(R.drawable.ic_media_unmute);
            Log.v(TAG, "Player unmute");
        } else {
            audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
            muteMedia.setImageResource(R.drawable.ic_media_mute);
            Log.v(TAG, "Player mute");
        }
        //mPlayer = null;
    }
    //End Mute

    //Play and pause update
    public void setPlayPause(){
        if(mPlayer == null) {
            return;
        }

        if (mPlayer.isPlaying()) {
            mPlayer.pause();
            playMedia.setImageResource(R.drawable.ic_media_play);
            Log.v(TAG, "Player pause");
        } else {
            mPlayer.start();
            playMedia.setImageResource(R.drawable.ic_media_pause);
            Log.v(TAG, "Player play");
        }
    }
    //End play and pause

    public void onPrepared(MediaPlayer mp) {
//        if( mFirst ) {
//            mFirst = false;

        // set Progress bar values
        seekBar.setProgress(0);
        seekBar.setMax(100);

        // Updating progress bar
        updateProgressBar();

//            if(isRestore){
        mPlayer.seekTo(position);
//            } else {
//                mPlayer.reset();
//                }
        mPlayer.start();
        AudioReady = true;
    }

    public void onAudioFocusChange(int focusChange) {
        switch (focusChange) {

            //if the app gains audio focus
            case AudioManager.AUDIOFOCUS_GAIN:
                if (mPlayer == null) {
                    loadVideoSource();
                } else if (!mPlayer.isPlaying()) {
                    if (AudioReady) {
                        mPlayer.start();
                        mPlayer.setVolume(1.0f, 1.0f);
                    }
                }
                break;
            //if the app loses audio focus
            case AudioManager.AUDIOFOCUS_LOSS:
//                if (mPlayer.isPlaying())
//                    deletePlayer();
                break;

            //if the app temporarily loses audio focus
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:

                if (mPlayer.isPlaying())
                    mPlayer.pause();
                break;

            //if the app is ducked
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:

                if (mPlayer.isPlaying())
                    mPlayer.setVolume(0.1f, 0.1f);
                break;
        }
    }

    public void deletePlayer() {
        if (mPlayer != null) {
            stopMedia.setImageResource(R.drawable.ic_media_stop);
            mPlayer.stop();
            mHandler.removeCallbacks(mUpdateTimeTask);
            Log.v(TAG, "Player stop");
            mPlayer.reset();
            mPlayer.release();
            Log.v(TAG, "Player release");
            mPlayer = null;
        }
    }

    public void resetPlayer(){
        Intent intent = this.activity.getIntent();
        this.activity.finish();

        //opening your activity
        this.activity.startActivity(intent);

        //removing the screen transition
        this.activity.overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//        mHandler.removeCallbacks(mUpdateTimeTask);
//        mPlayer.reset();
    }

    //Fullscreen
    public void toggleFullScreen() {
        if(mPlayer == null) {
            return;
        }
        if(isFullScreen()) {
            fullscreenMedia.setImageResource(R.drawable.ic_media_fullscreen_stretch);
            Log.v("FullScreen", "-----------------click toggleFullScreen-----------");
            updateFullScreen(mFullScreen);

        }else{
            fullscreenMedia.setImageResource(R.drawable.ic_media_fullscreen_shrink);
            Log.v("FullScreen", "-----------------click toggleFullScreen-----------");
            updateFullScreen(!mFullScreen);
        }

    }

    private void updateFullScreen(boolean fullScreen) {
        if (isFullScreen())
        {
            Log.v(TAG, "Set full screen SCREEN_ORIENTATION_PORTRAIT");
            this.activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            setUnFullscreen(true);

            mFullScreen = !fullScreen;
        }
        else {
            Log.v(TAG, "Set full screen SCREEN_ORIENTATION_LANDSCAPE");
            this.activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

            setFullScreen(false);

            mFullScreen = fullScreen;

        }
    }

    public boolean isFullScreen() {
        if (mFullScreen) {
            Log.v("FullScreen", "--set icon full screen--");
            mFullScreen = false;
            return false;
        } else {
            Log.v("FullScreen", "--set icon small full screen--");
            mFullScreen = true;
            return true;
        }
    }

    private void setFullScreen(boolean fullScreen) {
        // Load parent display dimensions
        Log.v(TAG, "Set full screen");
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        // Apply parent display dimensions to videoSurface
        android.widget.FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) surfaceView.getLayoutParams();
        params.width = width;
        params.height = height;
        params.setMargins(0, 0, 0, 0);
        surfaceView.setLayoutParams(params);

    }

    private void setUnFullscreen(boolean fullscreen) {

        Log.v(TAG, "Set Un full screen");
        surfaceHolder.setFixedSize(mPlayer.getVideoWidth(), mPlayer.getVideoHeight());

        // Apply parent display dimensions to videoSurface
        android.widget.FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) surfaceView.getLayoutParams();
        params.setMargins(0, 0, 0, 0);
        surfaceView.setLayoutParams(params);

    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            mPlayer.seekTo(progress);
            seekBar.setProgress(progress);
        }
    }

    public void onStopTrackingTouch() {
        mHandler.removeCallbacks(mUpdateTimeTask);
        int totalDuration = mPlayer.getDuration();
        int currentPosition = utilities.progressToTimer(seekBar.getProgress(), totalDuration);

        // forward or backward to certain seconds
        mPlayer.seekTo(currentPosition);

        // update timer progress again
        updateProgressBar();
    }
}
