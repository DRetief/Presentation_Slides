package socbox.presentationslides;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by davidretief on 10/03/2015.
 */
public class SlideFiveQuadImageVideo extends PresentationSlides{

    private String TAG = "SlideFiveQuadImageVideoTAG";

    // Variables for Text Header
    public TextHandler textHandler;
    TextView slide5TextView;
    String headingText = "Slide Heading";
    int headingSize = 30;
    Typeface font = Typeface.SANS_SERIF;

    // Image Variables
    ImageView imageTopLeftView;
    ImageView imageTopRightView;
    ImageView imageBottomLeftView;
    ImageView imageBottomRightView;
    ImageHandler imageHandler;
    SurfaceView videoSurface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_five_quad_image_video);

        slide5TextView = (TextView)findViewById(R.id.slide_5_text_heading);

        // Set TextView in which to embed text
        slide5TextView = (TextView)findViewById(R.id.slide_5_text_heading);
        addText(slide5TextView);

        embedImages();

        videoSurface = (SurfaceView)findViewById(R.id.slide_5_video_surface);
    }

    public void addText(TextView textView) {

        //call the text module with the relevant information
        textHandler = new TextHandler(this);

        //textHandler.setPos(textPosition[0], textPosition[1], textView);
        textHandler.setTextSize(headingSize, textView);
        textHandler.setText(headingText, textView);
        //textHandler.setColour(int Colour, textView);
        textHandler.setTypeFace(font , textView);
        //textHandler.setFormat(boolean underline, boolean bold, boolean italics, textView);
    }

    public void embedImages(){
        imageTopLeftView = (ImageView)findViewById(R.id.slide_5_image_top_left);
        imageTopRightView = (ImageView)findViewById(R.id.slide_5_image_top_right);
        imageBottomLeftView = (ImageView)findViewById(R.id.slide_5_image_bottom_left);
        imageBottomRightView = (ImageView)findViewById(R.id.slide_5_image_bottom_right);

        String imageTopLeft = "socbox_logo";
        String frameTopLeft = "slide_5_image_top_left";
        String imageTopRight = "socbox_logo";
        String frameTopRight = "slide_5_image_top_right";
        String imageBottomLeft = "socbox_logo";
        String frameBottomLeft = "slide_5_image_bottom_left";
        String imageBottomRight = "socbox_logo";
        String frameBottomRight = "slide_5_image_bottom_right";

        int imageIDTopLeft = getResources().getIdentifier(imageTopLeft, "drawable", thisPackage);
        int frameIDTopLeft = getResources().getIdentifier(frameTopLeft, "id", thisPackage);
        int imageIDTopRight = getResources().getIdentifier(imageTopRight, "drawable", thisPackage);
        int frameIDTopRight = getResources().getIdentifier(frameTopRight, "id", thisPackage);
        int imageIDBottomLeft = getResources().getIdentifier(imageBottomLeft, "drawable", thisPackage);
        int frameIDBottomLeft = getResources().getIdentifier(frameBottomLeft, "id", thisPackage);
        int imageIDBottomRight = getResources().getIdentifier(imageBottomRight, "drawable", thisPackage);
        int frameIDBottomRight = getResources().getIdentifier(frameBottomRight, "id", thisPackage);

        imageHandler = new ImageHandler();

        //Embed a single image in a frame
        imageHandler.embed_image(this, imageIDTopLeft, frameIDTopLeft);
        imageHandler.embed_image(this, imageIDTopRight, frameIDTopRight);
        imageHandler.embed_image(this, imageIDBottomLeft, frameIDBottomLeft);
        imageHandler.embed_image(this, imageIDBottomRight, frameIDBottomRight);
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
