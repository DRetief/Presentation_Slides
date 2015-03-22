package socbox.presentationslides;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by davidretief on 10/03/2015.
 */
public class SlideTwoImageText extends PresentationSlides {

    private String TAG = "SlideTwoImageTextTAG";

    // Text Variables (Header)
    public TextHandler textHandler;
    TextView slide2TextHeading;
    String headingText = "Short Piece of Text or Heading";
    int headingSize = 25;
    Typeface font = Typeface.SANS_SERIF;

    // Text Body Variables
    TextView slide2TextBody;
    String bodyText = "Larger Body of Informative Text Included Here";
    int bodySize = 15;
    Typeface bodyFont = Typeface.SANS_SERIF;

    // Image Variables
    public ImageView imageView;
    public ImageHandler imageHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_two_image_text);

        slide2TextHeading = (TextView)findViewById(R.id.slide_2_heading);
        slide2TextBody = (TextView)findViewById(R.id.slide_2_large_text_body);

        addText(slide2TextHeading);

        addTextBody(slide2TextBody);

        embedImage();
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

    public void addTextBody(TextView textView) {

        //call the text module with the relevant information
        textHandler = new TextHandler(this);

        //textHandler.setPos(textPosition[0], textPosition[1], textView);
        textHandler.setTextSize(bodySize, textView);
        textHandler.setText(bodyText, textView);
        //textHandler.setColour(int Colour, textView);
        textHandler.setTypeFace(bodyFont , textView);
        //textHandler.setFormat(boolean underline, boolean bold, boolean italics, textView);
    }

    public void embedImage(){
        imageView = (ImageView)findViewById(R.id.slide_2_image_view);

        String imageToEmbed = "socbox_logo";
        String frameToEmbedImage = "slide_2_image_view";

        int imageID = getResources().getIdentifier(imageToEmbed, "drawable", thisPackage);
        int frameID = getResources().getIdentifier(frameToEmbedImage, "id", thisPackage);

        Log.v("TAG", "imageID: " + imageID);
        Log.v("TAG", "frameID: " + frameID);

        imageHandler = new ImageHandler();

        //Embed a single image in a frame
        imageHandler.embed_image(this, imageID, frameID);
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
