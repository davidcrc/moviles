package com.example.ynl.holamundo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.BitSet;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "MESSAGE"; //static se crea una ves para n objetos
    public static final String TAG = "MESSAGE";
    public static final int REQUEST_IMAGE_CAPTURE= 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToAnActivity(View view){
        Intent intent = new Intent(this,SegundaActividad.class);
        startActivity(intent);

        EditText editText = (EditText) findViewById(R.id.plain_input_text);//busca en el layout los componentes
        String message = editText.getText().toString();
        Log.e(TAG,message); //e warming, r, ...
        intent.putExtra(EXTRA_MESSAGE,message);

        startActivity(intent);
    }

    public void takePhoto(View view){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected  void  onActivityResult(int requestCode, int resultcode, Intent data){
        if( requestCode == REQUEST_IMAGE_CAPTURE && resultcode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView mImageView = (ImageView) findViewById(R.id.imageView);
            mImageView.setImageBitmap(imageBitmap);
        }
    }


}