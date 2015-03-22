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
 * Created by davidretief on 10/03/2015.
 */
public class SlideFourDualImageScroll extends PresentationSlides {

    private String TAG = "SlideFourDualImageScrollTAG";

    // Text Variables
    public TextHandler textHandler;
    TextView slide2TextView;
    int headingSize = 25;

    // Image Variables
    public ImageHandler imageHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_four_dual_image_scroll);

        // Text variables
        String headingText = null;

        // Image variables
        String[] topScrollingImageTitles = new String[0];
        String[] bottomScrollingImageTitles = new String[0];
        int topScrollingImageCount = 0;
        int bottomScrollingImageCount = 0;


        // Extract data from parent activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Obtain Text info from Parent
            headingText = extras.getString("slide4Heading");

            // Obtain Image info from parent
            topScrollingImageCount = extras.getInt("topScrollingImageCount");
            bottomScrollingImageCount = extras.getInt("bottomScrollingImageCount");
            topScrollingImageTitles = extras.getStringArray("topScrollingImageTitles");
            bottomScrollingImageTitles = extras.getStringArray("bottomScrollingImageTitles");
        }
        else {
            Log.v(TAG, "ERROR: Slide not populated");
        }


        // Set TextView in which to embed text
        slide2TextView = (TextView)findViewById(R.id.slide_4_middle_text);
        addText(slide2TextView, headingText, headingSize);

        // Define the LinearLayouts in which to embed each ImageViews
        final LinearLayout slide4TopScrollLayout = (LinearLayout)
                findViewById(R.id.slide_4_top_scroll_layout);
        final LinearLayout slide4BottomScrollLayout = (LinearLayout)
                findViewById(R.id.slide_4_bottom_scroll_layout);

        // Embed Images into Slide
        embedScrollingImages(bottomScrollingImageCount, bottomScrollingImageTitles, slide4BottomScrollLayout);
        embedScrollingImages(topScrollingImageCount, topScrollingImageTitles, slide4TopScrollLayout);
    }

    public void addText(TextView textView, String text, int textSize) {

        //call the text module with the relevant information
        textHandler = new TextHandler(this);

        textHandler.setText(text, textView);
        textHandler.setTextSize(textSize, textView);
    }

    /*
        Dynamically create [int scrollingImageCount] ImageViews , embed frames in the vertical
        scrolling image panel and then embed the requested images within the ImageViews.
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
