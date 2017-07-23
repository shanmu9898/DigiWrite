package com.mukesh.android.digiwritetesting2;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class ContactActivity extends AppCompatActivity {

    //URL derived from form URL
    public static final String link = "https://docs.google.com/forms/d/e/1FAIpQLSf1BKwW7U0YsWB7i-ZOs-g-VZnLTS2JVV5fROEmq6KYzfm-jw/formResponse";
    //input element ids found from the live form page
    public static final String EMAIL_KEY = "entry.1475042151";
    public static final String SUBJECT_KEY = "entry.288641306";
    public static final String MESSAGE_KEY = "entry.2115714840";


    private Context context;
    private EditText emailEditText;
    private EditText subjectEditText;
    private EditText messageEditText;
    private Button sendButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();

        sendButton = (Button) findViewById(R.id.sendButton);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        subjectEditText = (EditText) findViewById(R.id.subjectEditText);
        messageEditText = (EditText) findViewById(R.id.messageEditText);
        context = this;

    }
    public void send_button(View view){
        //Make sure all the fields are filled with values
        if (TextUtils.isEmpty(emailEditText.getText().toString()) ||
                TextUtils.isEmpty(subjectEditText.getText().toString()) ||
                TextUtils.isEmpty(messageEditText.getText().toString())) {
            Toast.makeText(context, "All fields are mandatory.", Toast.LENGTH_LONG).show();
            return;
        }
        //Check if a valid email is entered
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailEditText.getText().toString()).matches()) {
            Toast.makeText(context, "Please enter a valid email.", Toast.LENGTH_LONG).show();
            return;
        }
        new SendPostRequest().execute();
    }
    public class SendPostRequest extends AsyncTask<String, Void, String> {
        String email = emailEditText.getText().toString();
        String subject =  subjectEditText.getText().toString();
        String message =  messageEditText.getText().toString();

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL(link); // here is your URL path

                JSONObject postDataParams = new JSONObject();
                postDataParams.put(EMAIL_KEY,email);
                postDataParams.put(SUBJECT_KEY,subject);
                postDataParams.put(MESSAGE_KEY,message);
                Log.e("params",postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in=new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return new String ("Message successfully sent!");

                }
                else {
                    return new String("false : "+responseCode);
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }
}
