// /*package com.textmodule.mark.textmodule;

// import android.app.Activity;
// import android.content.Intent;
// import android.graphics.Color;
// import android.graphics.Paint;
// import android.graphics.Typeface;
// import android.text.method.ScrollingMovementMethod;
// import android.widget.TextView;

// public class TextHandler {

//     Activity activity;
//     TextView textPanel;

//     public TextHandler(Activity calledFrom){
//         this.activity = calledFrom;

//         //create a text panel object
//         textPanel = (TextView) this.activity.findViewById(R.id.outText);

//     }
//     public void showText(String inputText,int xpos, int ypos,  int textSize, Typeface chosenFont, int bold, int italics, int scroll, int underline){

//         //set the position of the text panel
//         textPanel.setX(xpos);
//         textPanel.setY(ypos);
//         //set the message content and style of message
//         textPanel.setTextSize(textSize);
//         textPanel.setText(inputText);
//         textPanel.setTextColor(Color.GREEN);
//         textPanel.setTypeface(chosenFont);


//         //allow the use of the scroll function if required
//         if (scroll == 1) {
//             textPanel.setMovementMethod(new ScrollingMovementMethod());
//         }

//         //set the underlined, bold and italics options
//         if (underline == 1){
//             textPanel.setPaintFlags(textPanel.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
//         }
//         if ((bold == 1)&&(italics == 0)){
//             textPanel.setTypeface(null,Typeface.BOLD);
//         }
//         if ((italics == 1)&&(bold == 0)){
//             textPanel.setTypeface(null,Typeface.ITALIC);
//         }
//         if ((italics == 1) && (bold == 1)){
//             textPanel.setTypeface(null,Typeface.BOLD_ITALIC);
//         }}

//     public void moveActivity(){
//         Intent intent = new Intent(this.activity, SecondActivity.class);
//         this.activity.startActivity(intent);
//     }
// }


package socbox.presentationslides;

/**
 * Created by Andy on 11/03/2015.
 */

        import android.app.Activity;
        import android.content.Intent;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.Typeface;
        import android.text.method.ScrollingMovementMethod;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.widget.TextView;

        import org.w3c.dom.Text;

public class TextHandler {

    int x[] = new int[2];

    Activity activity;
    TextView textPanel;

    public TextHandler(Activity calledFrom){
        this.activity = calledFrom;
    }


    public int[] setPosition(TextView panel){

        panel.getLocationOnScreen(x);
        Log.v("FUCK", "text position:" + x[0]);
        Log.v("FUCK", "text position:" + x[1]);
        return x;
    }



    public void setX(int xPos, TextView textPanel){textPanel.setX(xPos);}
    public void setY(int yPos, TextView textPanel){textPanel.setY(yPos);}
    public void setPos(int xPos,int yPos, TextView textPanel){textPanel.setX(xPos);textPanel.setY(yPos);}
    public void setTextSize(int textSize, TextView textPanel){textPanel.setTextSize(textSize);}
    public void setText(String inputText, TextView textPanel){textPanel.setText(inputText);}
    public void setColour(int Colour, TextView textPanel){textPanel.setTextColor(Colour);}
    public void setTypeFace(Typeface font, TextView textPanel){textPanel.setTypeface(font);}
    public void setFormat(boolean underline, boolean bold, boolean italics, TextView textPanel){
        if(underline)
            textPanel.setPaintFlags(textPanel.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        else {
            //Something should happen here
        }

        if ((bold)&&(!italics)){
            textPanel.setTypeface(null,Typeface.BOLD);
        }
        else if ((italics)&&(!bold)){
            textPanel.setTypeface(null,Typeface.ITALIC);
        }
        else if ((italics) && (bold)){
            textPanel.setTypeface(null,Typeface.BOLD_ITALIC);
        }
        else{
            textPanel.setTypeface(null,Typeface.NORMAL);
        }



    }


}