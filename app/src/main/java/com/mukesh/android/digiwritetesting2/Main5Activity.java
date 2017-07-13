package com.mukesh.android.digiwritetesting2;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class Main5Activity extends AppCompatActivity {
    EditText textView;
    Button translatebutton;
    GoogleTranslate translator;
    ListView languages;
    String [] lang = {"Afrikaans",
            "Akan",
            "Albanian",
            "Amharic",
            "Arabic",
            "Armenian",
            "Azerbaijani",
            "Basque",
            "Belarusian",
            "Bemba",
            "Bengali",
            "Bihari",
            "Bork, bork, bork!",
            "Bosnian",
            "Breton",
            "Bulgarian",
            "Cambodian",
            "Catalan",
            "Cherokee",
            "Chichewa",
            "Chinese (Simplified)",
            "Chinese (Traditional)",
            "Corsican",
            "Croatian",
            "Czech",
            "Danish",
            "Dutch",
            "Elmer Fudd",
            "Esperanto",
            "Estonian",
            "Ewe",
            "Faroese",
            "Filipino",
            "Finnish",
            "French",
            "Frisian",
            "Ga",
            "Galician",
            "Georgian",
            "German",
            "Greek",
            "Guarani",
            "Gujarati",
            "Hacker",
            "Haitian Creole",
            "Hausa",
            "Hawaiian",
            "Hebrew",
            "Hindi",
            "Hungarian",
            "Icelandic",
            "Igbo",
            "Indonesian",
            "Interlingua",
            "Irish",
            "Italian",
            "Japanese",
            "Javanese",
            "Kannada",
            "Kazakh",
            "Kinyarwanda",
            "Kirundi",
            "Klingon",
            "Kongo",
            "Korean",
            "Krio (Sierra Leone)",
            "Kurdish",
            "Kurdish (Soranî)",
            "Kyrgyz",
            "Laothian",
            "Latin",
            "Latvian",
            "Lingala",
            "Lithuanian",
            "Lozi",
            "Luganda",
            "Luo",
            "Macedonian",
            "Malagasy",
            "Malay",
            "Malayalam",
            "Maltese",
            "Maori",
            "Marathi",
            "Mauritian Creole",
            "Moldavian",
            "Mongolian",
            "Montenegrin",
            "Nepali",
            "Nigerian Pidgin",
            "Northern Sotho",
            "Norwegian",
            "Norwegian (Nynorsk)",
            "Occitan",
            "Oriya",
            "Oromo",
            "Pashto",
            "Persian",
            "Pirate",
            "Polish",
            "Portuguese (Brazil)",
            "Portuguese (Portugal)",
            "Punjabi",
            "Quechua",
            "Romanian",
            "Romansh",
            "Runyakitara",
            "Russian",
            "Scots Gaelic",
            "Serbian",
            "Serbo-Croatian",
            "Sesotho",
            "Setswana",
            "Seychellois Creole",
            "Shona",
            "Sindhi",
            "Sinhalese",
            "Slovak",
            "Slovenian",
            "Somali",
            "Spanish",
            "Spanish (Latin American)",
            "Sundanese",
            "Swahili",
            "Swedish",
            "Tajik",
            "Tamil",
            "Tatar",
            "Telugu",
            "Thai",
            "Tigrinya",
            "Tonga",
            "Tshiluba",
            "Tumbuka",
            "Turkish",
            "Turkmen",
            "Twi",
            "Uighur",
            "Ukrainian",
            "Urdu",
            "Uzbek",
            "Vietnamese",
            "Welsh",
            "Wolof",
            "Xhosa",
            "Yiddish",
            "Yoruba",
            "Zulu"
    };
    ArrayAdapter<String> adapter;
    String dingding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        textView = (EditText) findViewById(R.id.textView);
        translatebutton = (Button) findViewById(R.id.Translatebutton);
        String string = getIntent().getStringExtra("string");
        textView.setText(string);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        translatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    final Dialog dialog = new Dialog(Main5Activity.this);
                    dialog.setContentView(R.layout.custom);
                    dialog.setTitle("Title...");

                languages = (ListView) dialog.findViewById(R.id.listView1);
                adapter = new ArrayAdapter<String>(Main5Activity.this, android.R.layout.simple_list_item_1, lang);

                languages.setAdapter(adapter);
                languages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (parent.getItemAtPosition(position).equals("Afrikaans")){
                            dingding = "af";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Akan")){
                            dingding = "ak";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Albanian")){
                            dingding = "sq";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Amharic")){
                            dingding = "am";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Arabic")){
                            dingding = "ar";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Armenian")){
                            dingding = "hy";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Azerbaijani")){
                            dingding = "az";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Basque")){
                            dingding = "eu";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Belarusian")){
                            dingding = "be";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Bemba")){
                            dingding = "bem";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Bengali")){
                            dingding = "bn";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Bihari")){
                            dingding = "bh";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Bork, bork, bork!")){
                            dingding = "xx-bork";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Bosnian")){
                            dingding = "bs";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Breton")){
                            dingding = "br";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Bulgarian")){
                            dingding = "bg";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Cambodian")){
                            dingding = "km";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Catalan")){
                            dingding = "ca";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Cherokee")){
                            dingding = "chr";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Chichewa")){
                            dingding = "ny";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Chinese (Simplified)")){
                            dingding = "zh-CN";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Chinese (Traditional)")){
                            dingding = "zh-TW";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Corsican")){
                            dingding = "co";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Croatian")){
                            dingding = "hr";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Czech")){
                            dingding = "cs";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Danish")){
                            dingding = "da";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Dutch")){
                            dingding = "nl";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Elmer Fudd")){
                            dingding = "xx-elmer";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Esperanto")){
                            dingding = "eo";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Estonian")){
                            dingding = "et";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Ewe")){
                            dingding = "ee";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Faroese")){
                            dingding = "fo";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("French")){
                            dingding = "fr";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Frisian")){
                            dingding = "fy";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Ga")){
                            dingding = "gaa";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Galician")){
                            dingding = "gl";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Georgian")){
                            dingding = "ka";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("German")){
                            dingding = "de";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Greek")){
                            dingding = "el";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Guarani")){
                            dingding = "gn";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Gujarati")){
                            dingding = "gu";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Hacker")){
                            dingding = "xx-hacker";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Haitian Creole")){
                            dingding = "ht";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Hausa")){
                            dingding = "ha";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Hawaiian")){
                            dingding = "haw";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Hebrew")){
                            dingding = "iw";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Hindi")){
                            dingding = "hi";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Hungarian")){
                            dingding = "hu";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Icelandic")){
                            dingding = "is";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Igbo")){
                            dingding = "ig";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Indonesian")){
                            dingding = "id";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Interlingua")){
                            dingding = "ia";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Irish")){
                            dingding = "ga";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Italian")){
                            dingding = "it";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Japanese")){
                            dingding = "ja";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Javanese")){
                            dingding = "jw";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Kannada")){
                            dingding = "kn";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Kazakh")){
                            dingding = "kk";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Kinyarwanda")){
                            dingding = "rw";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Kirundi")){
                            dingding = "rn";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Klingon")){
                            dingding = "xx-klingon";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Kongo")){
                            dingding = "kg";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Korean")){
                            dingding = "ko";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Krio (Sierra Leone)")){
                            dingding = "kri";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Kurdish")){
                            dingding = "ku";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Kurdish (Soranî)")){
                            dingding = "ckb";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Kyrgyz")){
                            dingding = "ky";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Laothian")){
                            dingding = "lo";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Latin")){
                            dingding = "la";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Latvian")){
                            dingding = "lv";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Lingala")){
                            dingding = "ln";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Lithuanian")){
                            dingding = "lt";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Lozi")){
                            dingding = "loz";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Luganda")){
                            dingding = "lg";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Luo")){
                            dingding = "ach";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Macedonian")){
                            dingding = "mk";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Malagasy")){
                            dingding = "mg";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Malay")){
                            dingding = "ms";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Malayalam")){
                            dingding = "ml";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Maltese")){
                            dingding = "mt";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Maori")){
                            dingding = "mi";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Marathi")){
                            dingding = "mr";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Mauritian Creole")){
                            dingding = "mfe";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Moldavian")){
                            dingding = "mo";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Mongolian")){
                            dingding = "mn";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Montenegrin")){
                            dingding = "sr-ME";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Nepali")){
                            dingding = "ne";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Nigerian Pidgin")){
                            dingding = "pcm";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Northern Sotho")){
                            dingding = "nso";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Norwegian")){
                            dingding = "no";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Norwegian (Nynorsk)")){
                            dingding = "nn";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Occitan")){
                            dingding = "oc";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Oriya")){
                            dingding = "or";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Oromo")){
                            dingding = "om";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Pashto")){
                            dingding = "ps";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Persian")){
                            dingding = "fa";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Pirate")){
                            dingding = "xx-pirate";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Polish")){
                            dingding = "pl";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Portuguese (Brazil)")){
                            dingding = "pt-BR";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Portuguese (Portugal)")){
                            dingding = "pt-PT";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Punjabi")){
                            dingding = "pa";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Quechua")){
                            dingding = "qu";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Romanian")){
                            dingding = "ro";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Romansh")){
                            dingding = "rm";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Runyakitara")){
                            dingding = "nyn";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Russian")){
                            dingding = "ru";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Scots Gaelic")){
                            dingding = "gd";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Serbian")){
                            dingding = "sr";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Serbo-Croatian")){
                            dingding = "sh";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Sesotho")){
                            dingding = "st";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Setswana")){
                            dingding = "tn";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Seychellois Creole")){
                            dingding = "crs";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Shona")){
                            dingding = "sn";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Sindhi")){
                            dingding = "sd";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Sinhalese")){
                            dingding = "si";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Slovak")){
                            dingding = "sk";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Slovenian")){
                            dingding = "sl";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Somali")){
                            dingding = "so";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Spanish")){
                            dingding = "es";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Spanish (Latin American)")){
                            dingding = "es-419";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Sundanese")){
                            dingding = "su";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Swahili")){
                            dingding = "sw";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Swedish")){
                            dingding = "sv";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Tajik")){
                            dingding = "tg";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Tamil")){
                            dingding = "ta";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Tatar")){
                            dingding = "tt";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Telugu")){
                            dingding = "te";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Thai")){
                            dingding = "th";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Tigrinya")){
                            dingding = "ti";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Tonga")){
                            dingding = "to";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Tshiluba")){
                            dingding = "lua";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Tumbuka")){
                            dingding = "tum";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Turkish")){
                            dingding = "tr";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Twi")){
                            dingding = "tw";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Uighur")){
                            dingding = "ug";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Ukrainian")){
                            dingding = "uk";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Urdu")){
                            dingding = "ur";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Uzbek")){
                            dingding = "uz";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Vietnamese")){
                            dingding = "vi";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Welsh")){
                            dingding = "cy";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Wolof")){
                            dingding = "wo";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Xhosa")){
                            dingding = "xh";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Yiddish")){
                            dingding = "yi";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Yoruba")){
                            dingding = "yo";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else if (parent.getItemAtPosition(position).equals("Zulu")){
                            dingding = "zu";
                            new EnglishToTagalog().execute();
                            dialog.dismiss();
                        }else{
                            Toast.makeText(Main5Activity.this, "Select Any Other Language", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.show();

            }
        });



    }

    private class EnglishToTagalog extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progress = null;

        protected void onError(Exception ex) {

        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                translator = new GoogleTranslate(
                        "AIzaSyC5_9IP3YaUItXKHSOuWhboJgZxXHtd77o");

                Thread.sleep(1000);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPreExecute() {
            // start the progress dialog
            progress = ProgressDialog.show(Main5Activity.this, null,
                    "Translating...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void result) {
            progress.dismiss();

            super.onPostExecute(result);
            translated();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }

    public void translated() {

        String translatetotagalog = textView.getText().toString();
        String text = translator.translte(translatetotagalog, "en", dingding);
        textView.setText(text);

    }











    public void save_doc(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Save As");
        builder.setMessage("Enter name of document and choose export format");
        final EditText namingdoc = new EditText(this);
        builder.setView(namingdoc);

        builder.setPositiveButton("Pdf File (.pdf)", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(namingdoc.getText().toString().isEmpty()){
                    Toast.makeText(Main5Activity.this, "Enter the name of the document and try again", Toast.LENGTH_SHORT).show();
                }else {
                    String fileName = namingdoc.getText().toString() + ".pdf";
                    String outpath = Environment.getExternalStorageDirectory() + "/" + fileName;
                    PdfDocument document = new PdfDocument();
                    View content = textView;
                    int pageNumber = 1;
                    PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(content.getWidth(), content.getHeight(), pageNumber).create();
                    PdfDocument.Page page = document.startPage(pageInfo);
                    content.draw(page.getCanvas());
//                Canvas canvas = page.getCanvas();
//                Paint paint = new Paint();
//                canvas.drawText(et.getText().toString(),10, 10, paint);
                    document.finishPage(page);

                    try {
                        document.writeTo(new FileOutputStream(outpath));
                        document.close();

                    } catch (IOException e) {
                        Log.i("error", e.getLocalizedMessage());
                    }
                    Toast.makeText(Main5Activity.this, "Saved " + fileName, Toast.LENGTH_LONG).show();
                }
            }});


        builder.setNeutralButton("Text File (.txt)", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(namingdoc.getText().toString().isEmpty()){
                    Toast.makeText(Main5Activity.this, "Enter the name of the document and try again", Toast.LENGTH_SHORT).show();
                }else {
                    String fileName = namingdoc.getText().toString() + ".txt";
                    String outpath = Environment.getExternalStorageDirectory() + "/" + fileName;
                    try {
                        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(outpath));
                        out.write(textView.getText().toString());
                        out.close();
                        Toast.makeText(Main5Activity.this, "Saved " + fileName, Toast.LENGTH_LONG).show();
                    } catch (Throwable t) {
                        Toast.makeText(Main5Activity.this, "Exception: " + t.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }});

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void share_doc(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Save As");
        builder.setMessage("Enter name of document and choose export format");
        final EditText namingdoc = new EditText(this);
        builder.setView(namingdoc);

        builder.setPositiveButton("Pdf File (.pdf)", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(namingdoc.getText().toString().isEmpty()){
                    Toast.makeText(Main5Activity.this, "Enter the name of the document and try again", Toast.LENGTH_SHORT).show();
                }else {
                    String fileName = namingdoc.getText().toString() + ".pdf";
                    String outpath = Environment.getExternalStorageDirectory() + "/" + fileName;
                    PdfDocument document = new PdfDocument();
                    View content = textView;
                    int pageNumber = 1;
                    PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(content.getWidth(), content.getHeight(), pageNumber).create();
                    PdfDocument.Page page = document.startPage(pageInfo);
                    content.draw(page.getCanvas());
//                Canvas canvas = page.getCanvas();
//                Paint paint = new Paint();
//                canvas.drawText(et.getText().toString(),10, 10, paint);
                    document.finishPage(page);

                    try {
                        document.writeTo(new FileOutputStream(outpath));
                        document.close();

                    } catch (IOException e) {
                        Log.i("error", e.getLocalizedMessage());
                    }

                    //String filename="";
                    File filelocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), fileName);
                    Uri path = Uri.fromFile(filelocation);
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    // set the type to 'email'
                    shareIntent.setType("application/*");
                    // the attachment
                    shareIntent.putExtra(Intent.EXTRA_STREAM, path);
                    // the mail subject
                    startActivity(Intent.createChooser(shareIntent, "Share Via"));
                }

            }});


        builder.setNegativeButton("Text File (.txt)", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(namingdoc.getText().toString().isEmpty()){
                    Toast.makeText(Main5Activity.this, "Enter the name of the document and try again", Toast.LENGTH_SHORT).show();
                }else {
                    String fileName = namingdoc.getText().toString() + ".txt";
                    String outpath = Environment.getExternalStorageDirectory() + "/" + fileName;
                    try {
                        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(outpath));
                        out.write(textView.getText().toString());
                        out.close();
                    } catch (Throwable t) {
                        Toast.makeText(Main5Activity.this, "Exception: " + t.toString(), Toast.LENGTH_SHORT).show();
                    }

                    //String filename="";
                    File filelocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), fileName);
                    Uri path = Uri.fromFile(filelocation);
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    // set the type to 'email'
                    shareIntent.setType("application/*");
                    // the attachment
                    shareIntent.putExtra(Intent.EXTRA_STREAM, path);
                    // the mail subject
                    startActivity(Intent.createChooser(shareIntent, "Share Via"));
                }

            }});

        builder.setNeutralButton("Text", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String Body = textView.getText().toString();
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/*");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, Body);
                startActivity(Intent.createChooser(shareIntent , "Send via..."));
            }});

        AlertDialog alert = builder.create();
        alert.show();
    }


}
