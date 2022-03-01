package com.example.jsondatafetching;

import android.os.AsyncTask;

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

public class fetchData extends AsyncTask<Void,Void,Void> {
    String data="";
    String singleParsed="";
    String dataParsed="";
    @Override
    protected Void doInBackground(Void... voids) {
        try{
            URL url=new URL("https://pastebin.com/embed_js/v0bCDH3P");
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while(line !=null){
                line=bufferedReader.readLine();
                data=data+line;
            }
            JSONArray jsonArray=new JSONArray(data);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject= (JSONObject) jsonArray.get(i);
                singleParsed="Name:"+jsonObject.get("Name")+"\n"+
                             "Address:"+jsonObject.get("Address")+"\n";

                dataParsed=dataParsed+singleParsed;
            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.data.setText(this.dataParsed);
    }


}
