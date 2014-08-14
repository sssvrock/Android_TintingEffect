package andorid.tinteffect.com.tintineffect;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class TintingActivity extends Activity implements View.OnClickListener{

    private String[] colors = {"white", "magenta", "yellow", "cyan", "green"};
    private int index = 0;


    private int getIdFromName(String name) {
        return getResources().getIdentifier(name,"id",getPackageName());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinting);

        for (int i=0; i<5; i++) {
            String name = "button_"+Integer.toString(i+1);
            findViewById(getIdFromName(name)).setOnClickListener(this);
        }
        // onClick(null);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tinting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        for (int i=0; i<5; i++) {
            String name = "button_"+Integer.toString(i+1);
            ((Button)findViewById(getIdFromName(name))).setText(colors[index]);
            int tint = Color.parseColor(colors[index]);
            // ((Button)findViewById(getIdFromName(name))).setBackgroundColor(tint);
           ((Button)findViewById(getIdFromName(name))).getBackground().setColorFilter(tint, PorterDuff.Mode.DARKEN);
        }
        index++;
        if (index>4) index=0;

    }
}
