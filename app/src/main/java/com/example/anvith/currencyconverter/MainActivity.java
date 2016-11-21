package com.example.anvith.currencyconverter;

import android.app.ActionBar;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.AsyncTask;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import com.example.anvith.currencyconverter.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    String selected1,selected2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        Spinner spinner = (Spinner) findViewById(R.id.fromcurrency);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.fromcurrency, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                selected1 = parent.getItemAtPosition(position).toString();
                // TODO Auto-generated method stub

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        Spinner spinner2 = (Spinner) findViewById(R.id.tocurrency);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.tocurrency, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                selected2 = parent.getItemAtPosition(position).toString();
                // TODO Auto-generated method stub

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
    }

    public class RetrieveCurrency extends AsyncTask<Void,  Void,  JSONObject> {

        OkHttpClient client = new OkHttpClient();
        JSONObject data = null;
        HttpUrl.Builder urlBuilder;
        Response response;
      //  RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);


        protected void onPreExecute() {

            urlBuilder = HttpUrl.parse("http://api.fixer.io/latest").newBuilder();
            urlBuilder.addQueryParameter("base", "INR");
            urlBuilder.addQueryParameter("symbols", "USD,AUD,GBP,EUR");


        }

        @Override
        protected void onPostExecute(JSONObject a) {
            try {
                JSONObject d = new JSONObject( data.getString("rates"));
                Log.e("HEy", d.getString("AUD"));

                EditText editText = (EditText) findViewById(R.id.from_currency);
                String f = editText.getText().toString();
                double b = Double.parseDouble(f);

                findViewById(R.id.loadingPanel).setVisibility(View.GONE);

                if (selected1.equals("Indian Rupee")) {
                    if (selected2.equals("US Dollar")) {
                        double X = b * Double.parseDouble(d.getString("USD"));
                        double USD = Math.round(X * 100.0) / 100.0;
                        String str = String.valueOf(USD);
                        TextView textView = (TextView) findViewById(R.id.to_currency);
                        textView.setText(str);
                    }
                    if (selected2.equals("Australian Dollar")) {
                        double X = b * Double.parseDouble(d.getString("AUD"));
                        double AUD = Math.round(X * 100.0) / 100.0;
                        String str = String.valueOf(AUD);
                        TextView textView = (TextView) findViewById(R.id.to_currency);
                        textView.setText(str);
                    }
                    if (selected2.equals("Euro")) {
                        double X = b * Double.parseDouble(d.getString("EUR"));
                        double EUR = Math.round(X * 100.0) / 100.0;
                        String str = String.valueOf(EUR);
                        TextView textView = (TextView) findViewById(R.id.to_currency);
                        textView.setText(str);
                    }
                    if (selected2.equals("British Pound")) {
                        double X = b * Double.parseDouble(d.getString("GBP"));
                        double GBP = Math.round(X * 100.0) / 100.0;
                        String str = String.valueOf(GBP);
                        TextView textView = (TextView) findViewById(R.id.to_currency);
                        textView.setText(str);
                    }
                    if (selected2.equals("Indian Rupee")) {

                        double INR = Math.round(b * 100.0) / 100.0;
                        String str = String.valueOf(INR);
                        TextView textView = (TextView) findViewById(R.id.to_currency);
                        textView.setText(str);
                    }
                }

                else if (selected1.equals("Australian Dollar")) {
                    double U =Double.parseDouble(d.getString("AUD"));
                    if (selected2.equals("US Dollar")) {
                        double X = b * (Double.parseDouble(d.getString("USD"))/U);
                        double USD = Math.round(X * 100.0) / 100.0;
                        String str = String.valueOf(USD);
                        TextView textView = (TextView) findViewById(R.id.to_currency);
                        textView.setText(str);
                    }
                    if (selected2.equals("Australian Dollar")) {
                        double AUD = Math.round(b * 100.0) / 100.0;
                        String str = String.valueOf(AUD);
                        TextView textView = (TextView) findViewById(R.id.to_currency);
                        textView.setText(str);
                    }
                    if (selected2.equals("Euro")) {
                        double X = b * (Double.parseDouble(d.getString("EUR"))/U);
                        double EUR = Math.round(X * 100.0) / 100.0;
                        String str = String.valueOf(EUR);
                        TextView textView = (TextView) findViewById(R.id.to_currency);
                        textView.setText(str);
                    }
                    if (selected2.equals("British Pound")) {
                        double X = b * (Double.parseDouble(d.getString("GBP"))/U);
                        double GBP = Math.round(X * 100.0) / 100.0;
                        String str = String.valueOf(GBP);
                        TextView textView = (TextView) findViewById(R.id.to_currency);
                        textView.setText(str);
                    }
                    if (selected2.equals("Indian Rupee")) {
                        double X = b/U;
                        double INR = Math.round(X * 100.0) / 100.0;
                        String str = String.valueOf(INR);
                        TextView textView = (TextView) findViewById(R.id.to_currency);
                        textView.setText(str);
                    }
                }

                else if (selected1.equals("US Dollar")) {
                    double U =Double.parseDouble(d.getString("USD"));
                    if (selected2.equals("US Dollar")) {
                        double USD = Math.round(b * 100.0) / 100.0;
                        String str = String.valueOf(USD);
                        TextView textView = (TextView) findViewById(R.id.to_currency);
                        textView.setText(str);
                    }
                    if (selected2.equals("Australian Dollar")) {
                        double X = b * (Double.parseDouble(d.getString("AUD"))/U);
                        double AUD = Math.round(X * 100.0) / 100.0;
                        String str = String.valueOf(AUD);
                        TextView textView = (TextView) findViewById(R.id.to_currency);
                        textView.setText(str);
                    }
                    if (selected2.equals("Euro")) {
                        double X = b * (Double.parseDouble(d.getString("EUR"))/U);
                        double EUR = Math.round(X * 100.0) / 100.0;
                        String str = String.valueOf(EUR);
                        TextView textView = (TextView) findViewById(R.id.to_currency);
                        textView.setText(str);
                    }
                    if (selected2.equals("British Pound")) {
                        double X = b * (Double.parseDouble(d.getString("GBP"))/U);
                        double GBP = Math.round(X * 100.0) / 100.0;
                        String str = String.valueOf(GBP);
                        TextView textView = (TextView) findViewById(R.id.to_currency);
                        textView.setText(str);
                    }
                    if (selected2.equals("Indian Rupee")) {
                        double X = b/U;
                        double INR = Math.round(X * 100.0) / 100.0;
                        String str = String.valueOf(INR);
                        TextView textView = (TextView) findViewById(R.id.to_currency);
                        textView.setText(str);
                    }
                }

                else if (selected1.equals("British Pound")) {
                    double U =Double.parseDouble(d.getString("GBP"));
                    if (selected2.equals("US Dollar")) {
                        double X = b * (Double.parseDouble(d.getString("USD"))/U);
                        double USD = Math.round(X * 100.0) / 100.0;
                        String str = String.valueOf(USD);
                        TextView textView = (TextView) findViewById(R.id.to_currency);
                        textView.setText(str);
                    }
                    if (selected2.equals("Australian Dollar")) {
                        double X = b * (Double.parseDouble(d.getString("AUD"))/U);
                        double AUD = Math.round(X * 100.0) / 100.0;
                        String str = String.valueOf(AUD);
                        TextView textView = (TextView) findViewById(R.id.to_currency);
                        textView.setText(str);
                    }
                    if (selected2.equals("Euro")) {
                        double X = b * (Double.parseDouble(d.getString("EUR"))/U);
                        double EUR = Math.round(X * 100.0) / 100.0;
                        String str = String.valueOf(EUR);
                        TextView textView = (TextView) findViewById(R.id.to_currency);
                        textView.setText(str);
                    }
                    if (selected2.equals("British Pound")) {
                        double GBP = Math.round(b * 100.0) / 100.0;
                        String str = String.valueOf(GBP);
                        TextView textView = (TextView) findViewById(R.id.to_currency);
                        textView.setText(str);
                    }
                    if (selected2.equals("Indian Rupee")) {
                        double X = b/U;
                        double INR = Math.round(X * 100.0) / 100.0;
                        String str = String.valueOf(INR);
                        TextView textView = (TextView) findViewById(R.id.to_currency);
                        textView.setText(str);
                    }
                }

                else if (selected1.equals("Euro")) {
                    double U =Double.parseDouble(d.getString("EUR"));
                    if (selected2.equals("US Dollar")) {
                        double X = b * (Double.parseDouble(d.getString("AUD"))/U);
                        double USD = Math.round(X * 100.0) / 100.0;
                        String str = String.valueOf(USD);
                        TextView textView = (TextView) findViewById(R.id.to_currency);
                        textView.setText(str);
                    }
                    if (selected2.equals("Australian Dollar")) {
                        double X = b * (Double.parseDouble(d.getString("AUD"))/U);
                        double AUD = Math.round(X * 100.0) / 100.0;
                        String str = String.valueOf(AUD);
                        TextView textView = (TextView) findViewById(R.id.to_currency);
                        textView.setText(str);
                    }
                    if (selected2.equals("Euro")) {
                        double EUR = Math.round(b * 100.0) / 100.0;
                        String str = String.valueOf(EUR);
                        TextView textView = (TextView) findViewById(R.id.to_currency);
                        textView.setText(str);
                    }
                    if (selected2.equals("British Pound")) {
                        double X = b * (Double.parseDouble(d.getString("GBP"))/U);
                        double GBP = Math.round(X * 100.0) / 100.0;
                        String str = String.valueOf(GBP);
                        TextView textView = (TextView) findViewById(R.id.to_currency);
                        textView.setText(str);
                    }
                    if (selected2.equals("Indian Rupee")) {
                        double X = b/U;
                        double INR = Math.round(X * 100.0) / 100.0;
                        String str = String.valueOf(INR);
                        TextView textView = (TextView) findViewById(R.id.to_currency);
                        textView.setText(str);
                    }
                }

            }catch(JSONException x){

            }
        }

        @Override
        protected JSONObject doInBackground(Void... params) {
            CheckConnectivity a =new CheckConnectivity();

            if(a.hasActiveInternetConnection()){
                String url = urlBuilder.build().toString();


                try{

                    try{
                        Request request = new Request.Builder().url(url).build();
                        response = client.newCall(request).execute();
                        String res = response.body().string();
                        data =new JSONObject(res);

                    }catch (IOException e)
                    {
                        e.printStackTrace();

                    }
                } catch (JSONException j)
                {
                    j.printStackTrace();
                    return data;
                }
            }

            return data;


        }


        private class CheckConnectivity {

            private boolean isNetworkAvailable() {
                ConnectivityManager connectivityManager
                        = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                return activeNetworkInfo != null;
            }


            public boolean hasActiveInternetConnection() {

                if (isNetworkAvailable()) {
                    try {
                        HttpURLConnection urlc = (HttpURLConnection)
                                (new URL("http://clients3.google.com/generate_204")
                                        .openConnection());
                        urlc.setRequestProperty("User-Agent", "Android");
                        urlc.setRequestProperty("Connection", "close");
                        urlc.setConnectTimeout(1500);
                        urlc.connect();
                        return (urlc.getResponseCode() == 204 && urlc.getContentLength() == 0);
                    } catch (IOException e) {
                        Log.e("Internet CHeck", "Error checking internet connection", e);
                    }
                } else {
                    Log.d("Internet Check", "No network available!");
                }
                return false;
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void showResult(View v) {
        RetrieveCurrency c=new RetrieveCurrency();
        c.execute();
        Log.e("Hello" ,"world");
    }



    public void Reset(View v) {
        Spinner spinner = (Spinner) findViewById(R.id.fromcurrency);
        Spinner spinner2 = (Spinner) findViewById(R.id.tocurrency);
         spinner.setSelection(0);
        spinner2.setSelection(0);

        EditText editText = (EditText) findViewById(R.id.from_currency);
        editText.setText(" ");

        TextView textView = (TextView) findViewById(R.id.to_currency);
        textView.setText("-");


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
