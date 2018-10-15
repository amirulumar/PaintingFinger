package com.example.paintingfinger;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FingerPainterView myFingerPainterView = new FingerPainterView(this);
        myFingerPainterView.setId(R.id.myFingerPainterViewId);

        FrameLayout myFrameLayout = findViewById(R.id.myFrameLayout);

        // intent handling
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        myFingerPainterView.load(intent.getData());

        myFrameLayout.addView(myFingerPainterView);

        // set brush colour
        Button showColours = (Button) findViewById(R.id.colour);
        showColours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // setup the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View popup = getLayoutInflater().inflate(R.layout.dialog_colour, null);

                // input values (brush colour)
                final EditText colour_hex = popup.findViewById(R.id.colour_hex);
                final Button confirm = popup.findViewById(R.id.colour_confirm);

                // create and show the alert dialog
                builder.setView(popup);
                final AlertDialog dialog = builder.create();
                dialog.show();
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // leaves much to be desired
                        myFingerPainterView.setColour(Integer.parseInt(colour_hex.getText().toString()));
                        dialog.dismiss();
                    }
                });
            }
        });

        // set brush shape and width
        Button showShape = (Button) findViewById(R.id.shape);
        showShape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // setup the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View popup = getLayoutInflater().inflate(R.layout.dialog_shape, null);

                // input values (brush shape and width)
                final EditText shape_width = popup.findViewById(R.id.shape_width);
                final Button confirm = popup.findViewById(R.id.shape_confirm);
                shape_width.setText(Integer.toString(myFingerPainterView.getBrushWidth()));

                // create and show the alert dialog
                builder.setView(popup);
                final AlertDialog dialog = builder.create();
                dialog.show();
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // leaves much to be desired
                        myFingerPainterView.setBrushWidth(Integer.parseInt(shape_width.getText().toString()));
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}
