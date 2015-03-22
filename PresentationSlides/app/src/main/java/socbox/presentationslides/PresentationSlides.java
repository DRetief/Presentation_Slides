package socbox.presentationslides;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class PresentationSlides extends ActionBarActivity {

    public String thisPackage = "socbox.presentationslides";
    private String TAG = "PresentationSlidesTAG";

    Button slideOneButton;
    Button slideTwoButton;
    Button slideThreeButton;
    Button slideFourButton;
    Button slideFiveButton;
    Button slideSixButton;
    Button slideSevenButton;
    Button slideEightButton;
    Button slideNineButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation_slides);

        slideOneButton = (Button)findViewById(R.id.slide_one_button);
        slideTwoButton = (Button)findViewById(R.id.slide_two_button);
        slideThreeButton = (Button)findViewById(R.id.slide_three_button);
        slideFourButton = (Button)findViewById(R.id.slide_four_button);
        slideFiveButton = (Button)findViewById(R.id.slide_five_button);
        slideSixButton = (Button)findViewById(R.id.slide_six_button);
        slideSevenButton = (Button)findViewById(R.id.slide_seven_button);
        slideEightButton = (Button)findViewById(R.id.slide_eight_button);
        slideNineButton = (Button)findViewById(R.id.slide_nine_button);


        slideOneButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                slide1ButtonPressed();
            }
        });

        slideTwoButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                slide2ButtonPressed();
            }
        });

        slideThreeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                slide3ButtonPressed();
            }
        });

        slideFourButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
               slide4ButtonPressed();
            }
        });

        slideFiveButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                slide5ButtonPressed();
            }
        });

        slideSixButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                slide6ButtonPressed();
            }
        });

        slideSevenButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                slide7ButtonPressed();

            }
        });

        slideEightButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                slide8ButtonPressed();
            }
        });

        slideNineButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                slide9ButtonPressed();
            }
        });
    }

    private void slide1ButtonPressed(){
        // Text info to be passed into activity
        String slide1Heading = "Slide 1 Heading";

        //Start new activity class
        Intent slideOneIntent = new Intent(PresentationSlides.this, SlideOneLargeVideo.class );
        slideOneIntent.putExtra("slide1Heading", slide1Heading);

        startActivity(slideOneIntent);
    }

    private void slide2ButtonPressed(){
        // Text info to be passed into activity
        String slide2Heading = "Slide 2 Heading";
        String slide2TextBody = "Larger Body of Informative Text Included Here";

        // Image info to be passed into activity
        String slide2Image = "ic_adventure";

        //Start new activity class
        Intent slideTwoIntent = new Intent(PresentationSlides.this, SlideTwoImageText.class );
        slideTwoIntent.putExtra("slide2Heading", slide2Heading);
        slideTwoIntent.putExtra("slide2TextBody", slide2TextBody);
        slideTwoIntent.putExtra("slide2Image", slide2Image);

        startActivity(slideTwoIntent);
    }

    private void slide3ButtonPressed(){
        // Text info to be passed into activity


        //Start new activity class
        Intent slideThreeIntent = new Intent(PresentationSlides.this, SlideThreeLargeImage.class );
        startActivity(slideThreeIntent);
    }

    private void slide4ButtonPressed(){
        // Text info to be passed into activity
        String slide4Heading = "Short Piece of Text or Heading";

        // Image info to be passed into activity
        int topScrollingImageCount = 6; // Number of images to be embedded in top scrolling panel
        int bottomScrollingImageCount = 4; // Number of images to be embedded in bottom scrolling panel

        // Image Names
        String[] topScrollingImageTitles = new String[topScrollingImageCount];
        topScrollingImageTitles[0] = "ic_adventure";
        topScrollingImageTitles[1] = "ic_chemistry";
        topScrollingImageTitles[2] = "ic_football";
        topScrollingImageTitles[3] = "ic_happypaint";
        topScrollingImageTitles[4] = "ic_quidditch";
        topScrollingImageTitles[5] = "ic_mountain";

        // Image Names
        String[] bottomScrollingImageTitles = new String[bottomScrollingImageCount];
        bottomScrollingImageTitles[0] = "socbox_logo";
        bottomScrollingImageTitles[1] = "socbox_logo";
        bottomScrollingImageTitles[2] = "socbox_logo";
        bottomScrollingImageTitles[3] = "socbox_logo";

        //Start new activity class
        Intent slideFourIntent = new Intent(PresentationSlides.this, SlideFourDualImageScroll.class );
        slideFourIntent.putExtra("slide4Heading", slide4Heading);
        slideFourIntent.putExtra("topScrollingImageTitles", topScrollingImageTitles);
        slideFourIntent.putExtra("bottomScrollingImageTitles", bottomScrollingImageTitles);
        slideFourIntent.putExtra("topScrollingImageCount", topScrollingImageCount);
        slideFourIntent.putExtra("bottomScrollingImageCount", bottomScrollingImageCount);
        startActivity(slideFourIntent);
    }

    private void slide5ButtonPressed(){

        //Start new activity class
        Intent slideFiveIntent = new Intent(PresentationSlides.this, SlideFiveQuadImageVideo.class );
        startActivity(slideFiveIntent);
    }

    private void slide6ButtonPressed(){
        // Text info to be passed into activity
        String slide6Heading = "Slide 6 Heading"; // Max ~ 21 characters w/ fontSize = 30
        String slide6BodyText = "Medium Text Body for Description"; // Max ~ 160 characters w/ fontSize = 20

        // Image info to be passed into activity
        int slide6ScrollImageCount = 5; // Number of images to be embedded in scrolling panel

        // Image Names
        String[] slide6Images = new String[(1 + slide6ScrollImageCount)];
        // Bottom Image
        slide6Images[0] = "ic_adventure";
        // Vertical Scrolling Image Panel
        slide6Images[1] = "ic_chemistry";
        slide6Images[2] = "ic_football";
        slide6Images[3] = "ic_happypaint";
        slide6Images[4] = "ic_mountain";
        slide6Images[5] = "ic_quidditch";

        //Start new activity class
        Intent slideSixIntent = new Intent(PresentationSlides.this, SlideSixVertImageText.class );
        slideSixIntent.putExtra("slide6Heading", slide6Heading);
        slideSixIntent.putExtra("slide6BodyText", slide6BodyText);
        slideSixIntent.putExtra("slide6Images", slide6Images);
        slideSixIntent.putExtra("scrollingImageCount", slide6ScrollImageCount);
        startActivity(slideSixIntent);
    }

    private void slide7ButtonPressed(){
        // Text info to be passed into activity
        String slide7Heading = "Slide 7 Heading";
        String[] slide7ImageHeadings = new String[6];

        for (int i=0; i<6; i++) {
            slide7ImageHeadings[i] = "Image Heading " + (i+1);
        }

        // Image Names
        String[] slide7Images = new String[6];
        slide7Images[0] = "ic_adventure";  // Top left
        slide7Images[1] = "ic_chemistry";  // Top right
        slide7Images[2] = "ic_football";   // Mid left
        slide7Images[3] = "ic_happypaint"; // Mid right
        slide7Images[4] = "ic_mountain";   // Bottom left
        slide7Images[5] = "ic_quidditch";  // Bottom right

        // Number of static images in slide
        int staticImagesCount = 6;

        //Start new activity class
        Intent slideSevenIntent = new Intent(PresentationSlides.this, SlideSevenImageGrid.class );
        slideSevenIntent.putExtra("slide7Heading", slide7Heading);
        slideSevenIntent.putExtra("slide7ImageHeadings", slide7ImageHeadings);
        slideSevenIntent.putExtra("slide7Images", slide7Images);
        slideSevenIntent.putExtra("staticImagesCount", staticImagesCount);
        startActivity(slideSevenIntent);
    }

    private void slide8ButtonPressed(){

    }

    private void slide9ButtonPressed(){
        // Text info to be passed into activity
        String slide9Heading = "Slide 9 Heading";
        String[] slide9ScrollHeadings = new String[6];

        for (int i=0; i<6; i++) {
            slide9ScrollHeadings[i] = "Scroller " + (i+1) + " Heading ";
        }

        // Image info to be passed into activity
        int topScrollingImageCount = 6; // Number of images to be embedded in top scrolling panel
        int midScrollingImageCount = 5; // Number of images to be embedded in middle scrolling panel
        int bottomScrollingImageCount = 4; // Number of images to be embedded in bottom scrolling panel

        // Image Names
        String[] topScrollingImageTitles = new String[topScrollingImageCount];
        topScrollingImageTitles[0] = "ic_adventure";
        topScrollingImageTitles[1] = "ic_chemistry";
        topScrollingImageTitles[2] = "ic_football";
        topScrollingImageTitles[3] = "ic_happypaint";
        topScrollingImageTitles[4] = "ic_quidditch";
        topScrollingImageTitles[5] = "ic_mountain";

        // Image Names
        String[] midScrollingImageTitles = new String[midScrollingImageCount];
        midScrollingImageTitles[0] = "ic_chemistry";
        midScrollingImageTitles[1] = "socbox_logo";
        midScrollingImageTitles[2] = "ic_quidditch";
        midScrollingImageTitles[3] = "ic_football";
        midScrollingImageTitles[4] = "ic_happypaint";

        // Image Names
        String[] bottomScrollingImageTitles = new String[bottomScrollingImageCount];
        bottomScrollingImageTitles[0] = "ic_football";
        bottomScrollingImageTitles[1] = "ic_happypaint";
        bottomScrollingImageTitles[2] = "socbox_logo";
        bottomScrollingImageTitles[3] = "ic_adventure";

        Intent slideNineIntent = new Intent(PresentationSlides.this, SlideNineTriScroller.class );
        slideNineIntent.putExtra("slide9Heading", slide9Heading);
        slideNineIntent.putExtra("slide9ScrollHeadings", slide9ScrollHeadings);
        slideNineIntent.putExtra("topScrollingImageTitles", topScrollingImageTitles);
        slideNineIntent.putExtra("midScrollingImageTitles", midScrollingImageTitles);
        slideNineIntent.putExtra("bottomScrollingImageTitles", bottomScrollingImageTitles);
        slideNineIntent.putExtra("topScrollingImageCount", topScrollingImageCount);
        slideNineIntent.putExtra("midScrollingImageCount", midScrollingImageCount);
        slideNineIntent.putExtra("bottomScrollingImageCount", bottomScrollingImageCount);
        startActivity(slideNineIntent);
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
