package com.example.paintingfinger;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.graphics.Paint;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int brush_colour = 0xFF000000;
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
        Button showColours = findViewById(R.id.colour);
        showColours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // setup the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View popup = getLayoutInflater().inflate(R.layout.dialog_colour, null);

                // input values (brush colour)
                Button colour_red = popup.findViewById(R.id.red);
                Button colour_orange = popup.findViewById(R.id.orange);
                Button colour_yellow = popup.findViewById(R.id.yellow);
                Button colour_green = popup.findViewById(R.id.green);
                Button colour_blue = popup.findViewById(R.id.blue);
                Button colour_indigo = popup.findViewById(R.id.indigo);
                Button colour_violet = popup.findViewById(R.id.violet);
                Button colour_black = popup.findViewById(R.id.black);

                // create and show the alert dialog
                builder.setView(popup);
                final AlertDialog dialog = builder.create();
                dialog.show();

                colour_red.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myFingerPainterView.setColour(0xFFFF0000);
                        Toast.makeText(getApplicationContext(), "Red selected", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                colour_orange.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myFingerPainterView.setColour(0xFFFFA500);
                        Toast.makeText(getApplicationContext(), "Orange selected", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                colour_yellow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myFingerPainterView.setColour(0xFFFFFF00);
                        Toast.makeText(getApplicationContext(), "Yellow selected", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                colour_green.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myFingerPainterView.setColour(0xFF00FF00);
                        Toast.makeText(getApplicationContext(), "Green selected", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                colour_blue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myFingerPainterView.setColour(0xFF0000FF);
                        Toast.makeText(getApplicationContext(), "Blue selected", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                colour_indigo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myFingerPainterView.setColour(0xFF4B0082);
                        Toast.makeText(getApplicationContext(), "Indigo selected", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                colour_violet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myFingerPainterView.setColour(0xFFEE82EE);
                        Toast.makeText(getApplicationContext(), "Violet selected", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                colour_black.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myFingerPainterView.setColour(0xFF000000);
                        Toast.makeText(getApplicationContext(), "Black selected", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }
        });

        // set brush shape and width
        Button showShape = findViewById(R.id.shape);
        showShape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // setup the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View popup = getLayoutInflater().inflate(R.layout.dialog_shape, null);

                // input values (brush shape and width)
                Button shape_round = popup.findViewById(R.id.round);
                Button shape_square = popup.findViewById(R.id.square);
                final EditText shape_width = popup.findViewById(R.id.shape_width);
                final Button confirm = popup.findViewById(R.id.shape_confirm);
                shape_width.setText(Integer.toString(myFingerPainterView.getBrushWidth()));

                // create and show the alert dialog
                builder.setView(popup);
                final AlertDialog dialog = builder.create();
                dialog.show();

                shape_round.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myFingerPainterView.setBrush(Paint.Cap.ROUND);
                        Toast.makeText(getApplicationContext(), "Round brush selected", Toast.LENGTH_SHORT).show();
                    }
                });
                shape_square.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myFingerPainterView.setBrush(Paint.Cap.SQUARE);
                        Toast.makeText(getApplicationContext(), "Square brush selected", Toast.LENGTH_SHORT).show();
                    }
                });
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myFingerPainterView.setBrushWidth(Integer.parseInt(shape_width.getText().toString()));
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}
