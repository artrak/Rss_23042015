package com.ua.art.rss_23042015;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    private String finalUrl="http://rian.com.ua/export/rss2/index.xml";  // http://tutorialspoint.com/android/sampleXML.xml
    private HandleXML obj;
    private EditText title,link,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //title = (EditText)findViewById(R.id.editText1);
        //link = (EditText)findViewById(R.id.editText2);
        //description = (EditText)findViewById(R.id.editText3);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void fetch(View view){       // нажатие кнопки
        obj = new HandleXML(finalUrl);
        obj.fetchXML();
        while(obj.parsingComplete);
        //title.setText(obj.getTitle());
        //link.setText(obj.getLink());
        //description.setText(obj.getDescription());
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
