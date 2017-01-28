package com.corp.infinity.dictionary_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.corp.infinity.dictionary_app.model.Word;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private static TextView textView;
    private static EditText editText1;
    private static EditText editText2;
    private static EditText editText3;
    private static Button button1;
    private static EditText editText_find;
    private static RadioButton rb1;
    private static RadioButton rb2;
    private static Button button2;
    private static TextView textView_output;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onFindButtonClick();
        onRadioSelect();
        realm = Realm.getDefaultInstance();
        editText1 = (EditText)findViewById(R.id.editText1);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);
        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_into_database(editText1.getText().toString().trim(),editText2.getText().toString().trim(),editText3.getText().toString().trim());
                refresh_views();
                Toast.makeText(MainActivity.this,"Data Saved",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void refresh_views() {
        RealmResults<Word> wordResinResults = realm.where(Word.class).findAll();
        String output = "";
        for (Word word: wordResinResults){
            output = output + word.toString();
            textView_output.setText(output);
        }
    }

    private void save_into_database(final String word, final String synonym, final String antonym) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Word word1 = bgRealm.createObject(Word.class);
                word1.setWord(word);
                word1.setSynonym(synonym);
                word1.setAntonym(antonym);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // Transaction was a success.
                Log.v("database",">>>>>>>>>stored of<<<<<<<<<");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
                Log.e("database",error.getMessage());
            }
        });
    }

    public void onFindButtonClick(){
        button2 = (Button) findViewById(R.id.button2);
        textView_output = (TextView)findViewById(R.id.textview_output);
        editText_find = (EditText)findViewById(R.id.editText_find);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText_find.getText().toString().equals("Study")){
                    RealmQuery<Word> query = realm.where(Word.class);
                    query.equalTo("word", "Study");
                    RealmResults<Word> result1 = query.findAllSorted("synonym");
                    Toast.makeText(MainActivity.this,"Synonyms and Antonyms are:\n\n" + result1,Toast.LENGTH_LONG).show();
                }

                if(editText_find.getText().toString().equals("Brave")){
                    RealmQuery<Word> query = realm.where(Word.class);
                    query.equalTo("word", "Brave");
                    RealmResults<Word> result1 = query.findAll();
                    Toast.makeText(MainActivity.this,"Synonyms and Antonyms are:\n\n" + result1,Toast.LENGTH_LONG).show();
                }

                if(editText_find.getText().toString().equals("Foolish")){
                    RealmQuery<Word> query = realm.where(Word.class);
                    query.equalTo("word", "Foolish");
                    RealmResults<Word> result1 = query.findAll();
                    Toast.makeText(MainActivity.this,"Synonyms and Antonyms are:\n\n" + result1,Toast.LENGTH_LONG).show();
                }

                if(editText_find.getText().toString().equals("Anger")){
                    RealmQuery<Word> query = realm.where(Word.class);
                    query.equalTo("word", "Anger");
                    RealmResults<Word> result1 = query.findAll();
                    Toast.makeText(MainActivity.this,"Synonyms and Antonyms are:\n\n" + result1,Toast.LENGTH_LONG).show();
                }

                if(editText_find.getText().toString().equals("Good")){
                    RealmQuery<Word> query = realm.where(Word.class);
                    query.equalTo("word", "Good");
                    RealmResults<Word> result1 = query.findAll();
                    Toast.makeText(MainActivity.this,"Synonyms and Antonyms are:\n\n" + result1,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void onRadioSelect(){
        rb1 = (RadioButton)findViewById(R.id.syn);
        rb2 = (RadioButton)findViewById(R.id.ant);

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText_find.getText().toString().equals("Study")){
                    RealmQuery<Word> query = realm.where(Word.class);
                    query.equalTo("word", "Study").distinct("synonym");
                    RealmResults<Word> result1 = query.findAll();
                    Toast.makeText(MainActivity.this,"Synonyms are\n\n" + result1,Toast.LENGTH_LONG).show();
                }

                if(editText_find.getText().toString().equals("Brave")){
                    RealmQuery<Word> query = realm.where(Word.class);
                    query.equalTo("word", "Brave").distinct("synonym");
                    RealmResults<Word> result1 = query.findAll();
                    Toast.makeText(MainActivity.this,"Synonyms are\n\n" + result1,Toast.LENGTH_LONG).show();
                }

                if(editText_find.getText().toString().equals("Foolish")){
                    RealmQuery<Word> query = realm.where(Word.class);
                    query.equalTo("word", "Foolish").distinct("synonym");
                    RealmResults<Word> result1 = query.findAll();
                    Toast.makeText(MainActivity.this,"Synonyms are:\n\n" + result1,Toast.LENGTH_LONG).show();
                }

                if(editText_find.getText().toString().equals("Anger")){
                    RealmQuery<Word> query = realm.where(Word.class);
                    query.equalTo("word", "Anger").distinct("synonym");
                    RealmResults<Word> result1 = query.findAll();
                    Toast.makeText(MainActivity.this,"Synonyms are:\n\n" + result1,Toast.LENGTH_LONG).show();
                }

                if(editText_find.getText().toString().equals("Good")){
                    RealmQuery<Word> query = realm.where(Word.class);
                    query.equalTo("word", "Good").distinct("synonym");
                    RealmResults<Word> result1 = query.findAll();
                    Toast.makeText(MainActivity.this,"Synonyms are:\n\n" + result1,Toast.LENGTH_LONG).show();
                }
            }
        });

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText_find.getText().toString().equals("Study")){
                    RealmQuery<Word> query = realm.where(Word.class);
                    query.equalTo("word", "Study").distinct("antonym");
                    RealmResults<Word> result1 = query.findAll();
                    Toast.makeText(MainActivity.this,"Antonyms are:\n\n" + result1,Toast.LENGTH_LONG).show();
                }

                if(editText_find.getText().toString().equals("Brave")){
                    RealmQuery<Word> query = realm.where(Word.class);
                    query.equalTo("word", "Brave").distinct("antonym");
                    RealmResults<Word> result1 = query.findAll();
                    Toast.makeText(MainActivity.this,"Antonyms are:\n\n" + result1,Toast.LENGTH_LONG).show();
                }

                if(editText_find.getText().toString().equals("Foolish")){
                    RealmQuery<Word> query = realm.where(Word.class);
                    query.equalTo("word", "Foolish").distinct("antonym");
                    RealmResults<Word> result1 = query.findAll();
                    Toast.makeText(MainActivity.this,"Antonyms are:\n\n" + result1,Toast.LENGTH_LONG).show();
                }

                if(editText_find.getText().toString().equals("Anger")){
                    RealmQuery<Word> query = realm.where(Word.class);
                    query.equalTo("word", "Good").distinct("antonym");
                    RealmResults<Word> result1 = query.findAll();
                    Toast.makeText(MainActivity.this,"Antonyms are:\n\n" + result1,Toast.LENGTH_LONG).show();
                }

                if(editText_find.getText().toString().equals("Good")){
                    RealmQuery<Word> query = realm.where(Word.class);
                    query.equalTo("word", "Good").distinct("antonym");
                    RealmResults<Word> result1 = query.findAll();
                    Toast.makeText(MainActivity.this,"Antonyms are:\n\n" + result1,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
