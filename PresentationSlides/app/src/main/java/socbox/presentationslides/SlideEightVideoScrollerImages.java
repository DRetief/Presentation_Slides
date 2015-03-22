package socbox.presentationslides;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.widget.ImageView;

/**
* Created by davidretief on 13/03/2015.
*/
public class SlideEightVideoScrollerImages extends PresentationSlides {

    private String TAG = "SlideEightVideoScrollerImagesTAG";

    SurfaceView videoSurface;

    // Image Variables
    public ImageHandler imageHandler;
    public ImageView imageTopLeft;
    public ImageView imageTopRight;

    // Vertical Scrolling View
    public ImageView imageViewScroll1;
    public ImageView imageViewScroll2;
    public ImageView imageViewScroll3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_eight_video_scroller_images);


        videoSurface = (SurfaceView)findViewById(R.id.slide_8_video_surface);


        embedImage();
        embedScrollingImage();

    }

    public void embedImage(){
        imageTopLeft = (ImageView)findViewById(R.id.slide_8_top_left_image);
        imageTopRight = (ImageView)findViewById(R.id.slide_8_top_right_image);

        String imageToEmbed = "socbox_logo";
        String frameToEmbedImage = "slide_8_top_left_image";
        String imageToEmbed2 = "socbox_logo";
        String frameToEmbedImage2 = "slide_8_top_right_image";

        int imageID = getResources().getIdentifier(imageToEmbed, "drawable", thisPackage);
        int frameID = getResources().getIdentifier(frameToEmbedImage, "id", thisPackage);
        int imageID2 = getResources().getIdentifier(imageToEmbed2, "drawable", thisPackage);
        int frameID2 = getResources().getIdentifier(frameToEmbedImage2, "id", thisPackage);

        Log.v("TAG", "imageID: " + imageID);
        Log.v("TAG", "frameID: " + frameID);
        Log.v("TAG", "imageID2: " + imageID2);
        Log.v("TAG", "frameID2: " + frameID2);

        imageHandler = new ImageHandler();

        //Embed a single image in a frame
        imageHandler.embed_image(this, imageID, frameID);
        imageHandler.embed_image(this, imageID2, frameID2);
    }


    public void embedScrollingImage(){
        imageViewScroll1 = (ImageView)findViewById(R.id.slide_8_scroll_1);
        imageViewScroll2 = (ImageView)findViewById(R.id.slide_8_scroll_2);
        imageViewScroll3 = (ImageView)findViewById(R.id.slide_8_scroll_3);

        String imageToEmbed = "socbox_logo";
        String frameToEmbedImage = "slide_8_top_left_image";

        int imageIDScroll1 = getResources().getIdentifier(imageToEmbed, "drawable", thisPackage);
        int frameIDScroll1 = getResources().getIdentifier(frameToEmbedImage, "id", thisPackage);
        int imageIDScroll2 = getResources().getIdentifier(imageToEmbed, "drawable", thisPackage);
        int frameIDScroll2 = getResources().getIdentifier(frameToEmbedImage, "id", thisPackage);
        int imageIDScroll3 = getResources().getIdentifier(imageToEmbed, "drawable", thisPackage);
        int frameIDScroll3 = getResources().getIdentifier(frameToEmbedImage, "id", thisPackage);

        Log.v("TAG", "imageID: " + imageIDScroll1);
        Log.v("TAG", "frameID: " + frameIDScroll1);
        Log.v("TAG", "imageID: " + imageIDScroll2);
        Log.v("TAG", "frameID: " + frameIDScroll2);
        Log.v("TAG", "imageID: " + imageIDScroll3);
        Log.v("TAG", "frameID: " + frameIDScroll3);

        imageHandler = new ImageHandler();

        //Embed a single image in a frame
        imageHandler.embed_image(this, imageIDScroll1, frameIDScroll1);
        imageHandler.embed_image(this, imageIDScroll2, frameIDScroll2);
        imageHandler.embed_image(this, imageIDScroll3, frameIDScroll3);
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
