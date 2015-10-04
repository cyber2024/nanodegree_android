package moviestreamer.ggg.com.moviestreamer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerOrderBy;
    private GridView gridViewThumnails;
    private ArrayAdapter<String> spinnerAdapter;
    private String spinnerOrderByArray[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerOrderByArray = new String[]{ "Order By Rating", "Order By Popularity"};
        spinnerOrderBy = (Spinner) findViewById(R.id.spinnerOrderBy);
        gridViewThumnails = (GridView) findViewById(R.id.gridViewThumbnails);
        spinnerAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, spinnerOrderByArray);
        spinnerOrderBy.setAdapter(spinnerAdapter);
        gridViewThumnails.setAdapter(new ImageAdapter(this));

        spinnerOrderBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=39ec4d7704b6310a7305e91435a56c83
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
