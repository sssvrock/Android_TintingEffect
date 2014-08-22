package andorid.tinteffect.com.tintineffect;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;


public class TintingActivity extends Activity implements View.OnClickListener{


    ImageView imageViewCar;
    ImageView imageViewCar2;
    public static final double PI = 3.14159d;
    public static final double FULL_CIRCLE_DEGREE = 360d;
    public static final double HALF_CIRCLE_DEGREE = 180d;
    public static final double RANGE = 256d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinting);

            findViewById(R.id.button_1).setOnClickListener(this);
            findViewById(R.id.button_1).setOnClickListener(this);

            imageViewCar = (ImageView)findViewById(R.id.imageViewCar);
            imageViewCar2 = (ImageView)findViewById(R.id.imageViewCar2);

//            imageViewCar.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    imageViewCar.setImageBitmap(applyTintEffect(BitmapFactory.decodeResource(getResources(), R.drawable.car),80));
//
//                }
//            });

            imageViewCar.setImageBitmap(applyTintEffect(BitmapFactory.decodeResource(getResources(), R.drawable.car),30));
           imageViewCar2.setImageBitmap(applyTintEffect(BitmapFactory.decodeResource(getResources(), R.drawable.car),80));



            findViewById(R.id.button_asset).setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {


        if(view.getId()==R.id.button_1)
        {
//            int color= Color.parseColor("magenta");
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            view.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        }
        else if(view.getId()==R.id.button_asset)
        {
//            int color= Color.parseColor("magenta");
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            view.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        }

    }


    public Bitmap applyTintEffect(Bitmap src, int degree) {
        // get source image size
        int width = src.getWidth();
        int height = src.getHeight();

        int[] pix = new int[width * height];
        // get pixel array from source
        src.getPixels(pix, 0, width, 0, 0, width, height);

        int RY, GY, BY, RYY, GYY, BYY, R, G, B, Y;
        double angle = (PI * (double)degree) / HALF_CIRCLE_DEGREE;

        int S = (int)(RANGE * Math.sin(angle));
        int C = (int)(RANGE * Math.cos(angle));

        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                int index = y * width + x;
                int r = ( pix[index] >> 16 ) & 0xff;
                int g = ( pix[index] >> 8 ) & 0xff;
                int b = pix[index] & 0xff;
                RY = ( 70 * r - 59 * g - 11 * b ) / 100;
                GY = (-30 * r + 41 * g - 11 * b ) / 100;
                BY = (-30 * r - 59 * g + 89 * b ) / 100;
                Y  = ( 30 * r + 59 * g + 11 * b ) / 100;
                RYY = ( S * BY + C * RY ) / 256;
                BYY = ( C * BY - S * RY ) / 256;
                GYY = (-51 * RYY - 19 * BYY ) / 100;
                R = Y + RYY;
                R = ( R < 0 ) ? 0 : (( R > 255 ) ? 255 : R );
                G = Y + GYY;
                G = ( G < 0 ) ? 0 : (( G > 255 ) ? 255 : G );
                B = Y + BYY;
                B = ( B < 0 ) ? 0 : (( B > 255 ) ? 255 : B );
                pix[index] = 0xff000000 | (R << 16) | (G << 8 ) | B;
            }
        // output bitmap
        Bitmap outBitmap = Bitmap.createBitmap(width, height, src.getConfig());
        outBitmap.setPixels(pix, 0, width, 0, 0, width, height);

        pix = null;

        return outBitmap;
    }

}
