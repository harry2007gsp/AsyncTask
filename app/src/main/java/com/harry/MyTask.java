package com.harry;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by hsingh on 8/17/15.
 */
class MyTask extends AsyncTask<String, String, ArrayList<Model>> {
//        Context context;
//
//        public MyTask(Context context) {
//            this.context = context;
//        }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("test", "Pre Execute");
    }

    @Override
    protected ArrayList<Model> doInBackground(String... urls) {
        String string = downloadWithURLConnecton(urls[0]);
        ArrayList<Model> list = parseData(string);
        Log.d("test", "Do in the Background : " + TextUtils.join(", ", list));
        publishProgress("download complete");
        return list;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        Log.d("test", "Progress Update: " + values[0]);
        MainActivity.text.setText(values[0]);
    }

    @Override
    protected void onPostExecute(ArrayList<Model> list) {
        super.onPostExecute(list);
        MainActivity.text.setText(list.get(0).getPlace());
        Log.d("test", "Post Execute");
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Log.d("test", "Cancelled");
    }

    @Override
    protected void onCancelled(ArrayList<Model> models) {
        super.onCancelled(models);
        Log.d("test", "BOOLEAN Cancelled");
    }

    public String downloadWithURLConnecton(String url) {
//            String url = "http://api.geonames.org/postalCodeLookupJSON?postalcode=6600&country=AT&username=demo";
        InputStream inputStream = null;
        StringBuilder b = new StringBuilder();


        try {
            URL url1 = new URL(url);
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection)url1.openConnection();
                httpURLConnection.setRequestMethod("GET");
                inputStream = httpURLConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";

                while ((line = br.readLine()) != null) {
                    b.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return b.toString();
    }

    public ArrayList<Model> parseData(String string) {
        ArrayList<Model> modelList = new ArrayList<>();
        Log.d("test", "got in parse data");
//            Log.d("test", string);
        try {
            JSONObject object = new JSONObject(string);
            JSONArray array = object.optJSONArray("postalcodes");
            for (int i = 0; i < array.length(); i++) {
                JSONObject arrayObject = array.optJSONObject(i);
                String country = arrayObject.optString("countryCode");
                String place = arrayObject.optString("placeName");
                double longitude = arrayObject.optDouble("lng");
                double lattitude = arrayObject.optDouble("lat");
                Model model = new Model(country, place, longitude, lattitude);
                modelList.add(model);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return modelList;
    }
}