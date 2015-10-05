package moviestreamer.ggg.com.moviestreamer;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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

    public void getMovieData(){
        Spinner spinner = (Spinner) findViewById(R.id.spinnerOrderBy);
        String spinnerValue = null;
        if(spinner.getSelectedItem().toString() == "Order By Rating"){
            spinnerValue = "rating";
        }else {
            spinnerValue = "popularity";
        }
        FetchMoviesTask fetchMoviesTask = new FetchMoviesTask();
        fetchMoviesTask.initiateExecute(spinnerValue);
    }

    public class FetchMoviesTask extends AsyncTask<String, Void, String[]>{
        private String spinnerValue = null;

        public void initiateExecute(String spinnerVal){
            spinnerValue = spinnerVal;
            this.execute();
        }

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String movieJsonStr = null;

            try{
                URL url = new URL(
                        "http://api.themoviedb.org/3/discover/movie?sort_by=" +spinnerValue+
                                ".desc&api_key=39ec4d7704b6310a7305e91435a56c83");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if(inputStream == null){
                    //do nothing
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while((line = reader.readLine()) != null)){
                    buffer.append(line + '\n');
                }
                if(buffer.length() == 0){
                    //stream empty, do nothing
                    return null;
                }
                movieJsonStr = buffer.toString();
            } catch (IOException e){
                Log.e("mainactivity", "Error", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("MainActivity", "Error closing stream", e);
                    }
                }
            }
            return getMovieDataFromJson(movieJsonStr);
        }

        public String[] getMovieDataFromJson(String jsonString)throws JSONException {

                JSONObject movieJson = new JSONObject(jsonString);

        }
    }
}
