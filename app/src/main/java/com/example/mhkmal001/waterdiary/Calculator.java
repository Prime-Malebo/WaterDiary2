package com.example.mhkmal001.waterdiary;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Calculator extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.mhkmal001.waterdiary.MESSAGE";
    public int realSum;

    // text file issues
    public String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        // Get the intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(Calculator.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView14);
        textView.setText(message);

        // creating the text file
        File dir = new File(path);
        dir.mkdirs();
    }

    /** called when the user clicks the Cancel button */
    public void cancelDiary(View view){
       Intent intent = new Intent(this, MainActivity.class);
       startActivity(intent);
    }

    /** called when the user clicks the Save_CategoryOne button */
    public void saveCategory(View view){
        Intent intent = new Intent(this, Calculator.class);
        EditText editText = (EditText) findViewById(R.id.editText3);
        //String message = editText.getText().toString();

        /** creating a public file and array */
        File file = new File(path +"savedData.txt");
        String[] saveUsage = String.valueOf(editText.getText()).split(System.getProperty("line.separator"));

        /**Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();

        Save (file, saveUsage);*/

        // extracting the current total
        TextView editTotal = (TextView)findViewById(R.id.textView14);
        String stringTotal = editTotal.getText().toString();

        // Converting the string to integer then summing up.
        int sum;
        sum = Integer.parseInt(editText.getText().toString());
        String message = Integer.toString(sum);

        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }

    /** Saving data on the created file */
    public static void Save(File file, String[] data)
    {
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(file);
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        try
        {
            try
            {
                for (int i = 0; i<data.length; i++)
                {
                    fos.write(data[i].getBytes());
                    if (i < data.length-1)
                    {
                        fos.write("\n".getBytes());
                    }
                }
            }
            catch (IOException e) {e.printStackTrace();}
        }
        finally
        {
            try
            {
                fos.close();
            }
            catch (IOException e) {e.printStackTrace();}
        }
    }

    /** clicks savebutton2 */
    public void saveTwo(View view){
        Intent intent = new Intent(this, Calculator.class);
        EditText editText = (EditText) findViewById(R.id.editText4);

        // extracting the current total
        TextView editTotal = (TextView)findViewById(R.id.textView14);
        String stringTotal = editTotal.getText().toString();

        int sum = Integer.parseInt(editText.getText().toString()) + Integer.parseInt(stringTotal);
        String message = Integer.toString(sum);

        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }

    /** called when the user clicks the Save_Entry button */
    public void saveEntry(View view){
        Intent intent = new Intent(this, EntryPage.class);
        EditText date = (EditText) findViewById(R.id.editText11);
        String message = date.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
