package socbox.presentationslides;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by davidretief on 13/03/2015.
 */
public class SlideSixVertImageText extends PresentationSlides {

    private String TAG = "SlideSixVertImageTextTAG";

    // Text variables
    public TextHandler textHandler;

    // Variables for Text Header
    TextView slide6TextHeading;
    int headingSize = 30;

    // Variables for Text Body
    TextView slide6TextBody;
    int bodySize = 20;

    // Image Variables
    public ImageHandler imageHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_six_vert_image_text);

        // Text variables
        String headingText = null;
        String bodyText = null;

        // Image variables
        String[] vertScrollImageTitles = new String[0];
        String slide6BottomImage = null;
        int scrollingImageCount = 0;

        // Extract data from parent activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Obtain Text info from Parent
            headingText = extras.getString("slide6Heading");
            bodyText = extras.getString("slide6BodyText");

            // Obtain Image info from parent
            scrollingImageCount = extras.getInt("scrollingImageCount");
            String[] slideImages = extras.getStringArray("slide6Images");

            slide6BottomImage = slideImages[0];
            vertScrollImageTitles = new String[scrollingImageCount];
            for (int i=0; i<scrollingImageCount; i++) {
                vertScrollImageTitles[i] = slideImages[i+1];
            }
        }
        else {
            Log.v(TAG, "ERROR: Slide not populated");
        }


        // Set TextView in which to embed heading
        slide6TextHeading = (TextView)findViewById(R.id.slide_6_text_heading);
        addText(slide6TextHeading, headingText, headingSize);

        // Set TextView in which to embed body
        slide6TextBody = (TextView)findViewById(R.id.slide_6_text_body);
        addText(slide6TextBody, bodyText, bodySize);

        // Define the linearLayout in which to embed each ImageView
        final LinearLayout slide6VertScrollLayout = (LinearLayout)
                findViewById(R.id.slide_6_vert_scroll_layout);

        // Embed Images into Slide
        embedScrollingImages(scrollingImageCount, vertScrollImageTitles, slide6VertScrollLayout);
        embedImage(slide6BottomImage);
    }

    public void addText(TextView textView, String text, int size) {
        // Call the text module with the relevant information
        textHandler = new TextHandler(this);

        textHandler.setTextSize(size, textView);
        textHandler.setText(text, textView);
    }


    /*
        Dynamically create ImageViews according to scrollingImageCount, embed frames in the
        vertical scrolling image panel and then embed the requested images within the
        ImageViews.
    */
    public void embedScrollingImages(int scrollingImageCount, String[] scrollingImageTitles,
                                     LinearLayout scrollLayout){

        int[] scrollingImageIDs = new int[scrollingImageCount];
        int[] scrollingFrameIDs = new int[scrollingImageCount];

        imageHandler = new ImageHandler();

        // Define the layout parameters to be given to each ImageView
        LinearLayout.LayoutParams imageViewParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        imageViewParams.gravity= Gravity.CENTER;
        imageViewParams.setMargins(5, 5, 5, 5);

        for(int i=0; i<scrollingImageCount; i++) {
            // Create ImageView
            final ImageView scrollingImageView = new ImageView(this);

            // Give ImageView an ID, layoutParams and group images together.
            scrollingImageView.setId(i+1);
            scrollingImageView.setLayoutParams(imageViewParams);
            scrollingImageView.setAdjustViewBounds(true);

            // Add ImageView to LinearLayout within scrolling panel.
            scrollLayout.addView(scrollingImageView);

            // Obtain ID values for the image to be embedded and the Imageview
            scrollingImageIDs[i] = getResources().getIdentifier(scrollingImageTitles[i], "drawable", thisPackage);
            scrollingFrameIDs[i] = scrollingImageView.getId();

            Log.v(TAG, "Embedding scrollable image: " + scrollingImageTitles[i] + " in layout: " +
                    scrollLayout.getId() + " in frame: " + scrollingFrameIDs[i]);

            // Embed the Image
            imageHandler.embed_image(this, scrollingImageIDs[i], scrollingFrameIDs[i]);
        }
    }

    public void embedImage(String slide6BottomImage){

        imageHandler = new ImageHandler();

        // Obtain ID values for the image to be embedded and the Imageview
        int imageID = getResources().getIdentifier(slide6BottomImage, "drawable", thisPackage);
        int frameID = getResources().getIdentifier("slide_6_image_view", "id", thisPackage);

        Log.v(TAG, "Embedding bottom image: " + slide6BottomImage);

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