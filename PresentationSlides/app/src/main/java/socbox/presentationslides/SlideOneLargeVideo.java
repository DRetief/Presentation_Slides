package socbox.presentationslides;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.util.Log;

/**
 * Created by davidretief on 10/03/2015.
 */
public class SlideOneLargeVideo extends PresentationSlides implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private String TAG = "SlideOneLargeVideoTAG";

    // Variables for video
//    SurfaceView videoSurface;
    public VideoHandler videoHandler;
    boolean isChecked = true;
    MediaPlayer mPlayer;

    // Variables for Text Header
    public TextHandler textHandler;
    int headingSize = 30;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_one_large_video);

        // Video Stuff

        videoHandler = new VideoHandler(this);

        // Text variables
        String headingText = null;

        // Extract data from parent activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Obtain Text info from Parent
            headingText = extras.getString("slide1Heading");
        }
        else {
            Log.v(TAG, "ERROR: Slide not populated");
        }

//        videoSurface = (SurfaceView)findViewById(R.id.slide_1_video_surface);
        int getOrientation = getResources().getConfiguration().orientation;
        if (getOrientation == 1) {
            // Set TextView in which to embed text
            TextView slide1TextView = (TextView) findViewById(R.id.slide_1_heading);
            addText(slide1TextView, headingText, headingSize);
        }
    }

    public void addText(TextView textView, String text, int textSize) {

        //call the text module with the relevant information
        textHandler = new TextHandler(this);

        textHandler.setText(text, textView);
        textHandler.setTextSize(textSize, textView);
    }

    public void onClick(View v) {
        switch( v.getId() ) {
            case R.id.buttonPlay :
                videoHandler.setPlayPause();
                break;
            case R.id.buttonStop :
                videoHandler.deletePlayer();
                videoHandler.loadVideoSource();
                videoHandler.position = 0;
//                videoHandler.resetPlayer();
                Log.v(TAG, "Player start");
                break;
            case R.id.buttonMute :
                videoHandler.setMute();
                break;
            case R.id.buttonFullScreen :
                videoHandler.toggleFullScreen();
                Log.v(TAG, "Player fullscreen");
                break;
            case R.id.seekBarMedia :
                Log.v(TAG, "Player seekBar");
                break;
            case R.id.surface :
                if (isChecked){
                    /**
                     * Remove the controller from the screen.
                     */
                    videoHandler.container.setVisibility(View.GONE);
                    isChecked = false;
                } else{
                    /**
                     * Show the controller on screen.
                     */
                    videoHandler.container.setVisibility(View.VISIBLE);
                    isChecked=true;
                }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        videoHandler.onStartTrackingTouch();
    }

    /**
     *
     * */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        videoHandler.onProgressChanged(seekBar, progress, fromUser);
    }

    /**
     * When user stops moving the progress handler
     * */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        videoHandler.onStopTrackingTouch();
    }

    // Save state when destroying the frame (e.g. on screen orientation change)
    @Override
    protected void onSaveInstanceState (Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        //use onSaveInstanceState in order to store the video playback position for orientation change
        savedInstanceState.putInt("position", videoHandler.mPlayer.getCurrentPosition());

        Log.v(TAG, ": Saving State ------" + videoHandler.mFullScreen);
        videoHandler.mPlayer.pause();
        savedInstanceState.putBoolean("fullScreen", videoHandler.mFullScreen);
    }

    // Reload previous state when restoring the frame (e.g. on screen orientation change)
    @Override
    protected void onRestoreInstanceState (@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        // Reload order to play the video playback from the stored position
        videoHandler.mFullScreen = savedInstanceState.getBoolean("fullScreen");
        Log.v(TAG, ": Loading Previous State. Fullscreen = " + videoHandler.mFullScreen);

        // Reload order to play the video playback from the stored position
        videoHandler.position = savedInstanceState.getInt("position");
        Log.v(TAG, ": Loading Previous State. Position = " + videoHandler.position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_presentation_slides, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}