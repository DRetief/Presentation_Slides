package socbox.presentationslides;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SlideSevenImageGrid extends PresentationSlides {

    private String TAG = "SlideSevenImageGridTAG";

    // Text variables
    public TextHandler textHandler;

    // Slide Heading
    TextView slide7SlideHeading;
    int headingSize = 30;

    // Image Headings
    int imageHeadingSize = 20;

    // Image Variables
    ImageHandler imageHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_seven_image_grid);

        // Text variables
        String headingText = null;
        String[] imageHeadings = new String[6];

        // Image variables
        String[] imageTitles = new String[6];
        int staticImagesCount = 0;

        // Extract data from parent activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Obtain Text info from Parent
            headingText = extras.getString("slide7Heading");
            imageHeadings = extras.getStringArray("slide7ImageHeadings");

            // Obtain Image info from parent
            imageTitles = extras.getStringArray("slide7Images");
            staticImagesCount = extras.getInt("staticImagesCount");
        }
        else {
            Log.v(TAG, "ERROR: Slide not populated");
        }

        // Set TextView for the slide heading and embed it
        slide7SlideHeading = (TextView)findViewById(R.id.slide_7_heading);
        addText(slide7SlideHeading, headingText, headingSize);

        // Set TextViews in which to embed image headings
        TextView[] slide7ImageHeadingViews = new TextView[6];
        slide7ImageHeadingViews[0] = (TextView)findViewById(R.id.slide_7_image_title_1);
        slide7ImageHeadingViews[1] = (TextView)findViewById(R.id.slide_7_image_title_2);
        slide7ImageHeadingViews[2] = (TextView)findViewById(R.id.slide_7_image_title_3);
        slide7ImageHeadingViews[3] = (TextView)findViewById(R.id.slide_7_image_title_4);
        slide7ImageHeadingViews[4] = (TextView)findViewById(R.id.slide_7_image_title_5);
        slide7ImageHeadingViews[5] = (TextView)findViewById(R.id.slide_7_image_title_6);

        for (int i=0; i<6; i++) {
            addText(slide7ImageHeadingViews[i], imageHeadings[i], imageHeadingSize);
        }

        String imageViewName = "slide_7_image_";
        // Embed the images for the slide
        embedMultipleImages(imageTitles, staticImagesCount, imageViewName);
    }

    public void addText(TextView textView, String text, int textSize) {

        //call the text module with the relevant information
        textHandler = new TextHandler(this);

        // Set text, size and font
        textHandler.setText(text, textView);
        textHandler.setTextSize(textSize, textView);
    }

    public void embedMultipleImages(String[] imageTitles, int staticImagesCount, String imageViewName){

        imageHandler = new ImageHandler();

        int[] imageIDs = new int[staticImagesCount];
        int[] imageViewIDs = new int[staticImagesCount];

        for (int i=0; i<staticImagesCount; i++){
            // Obtain ID values for the Imageview and the image to be embedded.
            imageIDs[i] = getResources().getIdentifier(imageTitles[i], "drawable", thisPackage);
            imageViewIDs[i] = getResources().getIdentifier(imageViewName + (i+1), "id", thisPackage);

            Log.v(TAG, "Embedding image: " + imageTitles[i]);

            // Embed the Image
            imageHandler.embed_image(this, imageIDs[i], imageViewIDs[i]);
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