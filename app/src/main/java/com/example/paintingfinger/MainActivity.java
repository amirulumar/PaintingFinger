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

        setColour(myFingerPainterView);
        setShape(myFingerPainterView);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void setColour(final FingerPainterView paint) {
        // set brush colour
        Button showColours = findViewById(R.id.colour);
        showColours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // setup the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View popup = getLayoutInflater().inflate(R.layout.dialog_colour, null);

                // get brush colour and add prompt
                int colour = paint.getColour();
                String prompt = null;
                switch (colour) {
                    case 0xFFFF0000:
                        prompt = "Red";
                        break;
                    case 0xFFFFA500:
                        prompt = "Orange";
                        break;
                    case 0xFFFFFF00:
                        prompt = "Yellow";
                        break;
                    case 0xFF00FF00:
                        prompt = "Green";
                        break;
                    case 0xFF0000FF:
                        prompt = "Blue";
                        break;
                    case 0xFF4B0082:
                        prompt = "Indigo";
                        break;
                    case 0xFFEE82EE:
                        prompt = "Violet";
                        break;
                    case 0xFF000000:
                        prompt = "Black";
                        break;
                }
                Toast.makeText(getApplicationContext(), "Current colour: " + prompt, Toast.LENGTH_SHORT).show();

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

                setColourListener(colour_red, paint, dialog);
                setColourListener(colour_orange, paint, dialog);
                setColourListener(colour_yellow, paint, dialog);
                setColourListener(colour_green, paint, dialog);
                setColourListener(colour_blue, paint, dialog);
                setColourListener(colour_indigo, paint, dialog);
                setColourListener(colour_violet, paint, dialog);
                setColourListener(colour_black, paint, dialog);
            }
        });
    }

    public void setShape(final FingerPainterView paint) {
        // set brush shape and width
        Button showShape = findViewById(R.id.shape);
        showShape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // setup the alert builder
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View popup = getLayoutInflater().inflate(R.layout.dialog_shape, null);

                // get brush shape and add prompt
                Paint.Cap shape = paint.getBrush();
                String prompt = null;
                switch (shape) {
                    case ROUND:
                        prompt = "Round";
                        break;
                    case SQUARE:
                        prompt = "Square";
                        break;
                }
                Toast.makeText(getApplicationContext(), "Current shape: " + prompt, Toast.LENGTH_SHORT).show();

                // input values (brush shape and width)
                Button shape_round = popup.findViewById(R.id.round);
                Button shape_square = popup.findViewById(R.id.square);
                final EditText shape_width = popup.findViewById(R.id.shape_width);
                final Button confirm = popup.findViewById(R.id.shape_confirm);
                shape_width.setText(Integer.toString(paint.getBrushWidth()));

                // create and show the alert dialog
                builder.setView(popup);
                final AlertDialog dialog = builder.create();
                dialog.show();

                setShapeListener(shape_round, paint, dialog);
                setShapeListener(shape_square, paint, dialog);
                
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        paint.setBrushWidth(Integer.parseInt(shape_width.getText().toString()));
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    public void setColourListener (final View colour, final FingerPainterView paint, final AlertDialog dialog) {
        colour.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = null;
                switch (colour.getId()) {
                    case R.id.red:
                        paint.setColour(0xFFFF0000);
                        value = "Red selected";
                        break;
                    case R.id.orange:
                        paint.setColour(0xFFFFA500);
                        value = "Orange selected";
                        break;
                    case R.id.yellow:
                        paint.setColour(0xFFFFFF00);
                        value = "Yellow selected";
                        break;
                    case R.id.green:
                        paint.setColour(0xFF00FF00);
                        value = "Green selected";
                        break;
                    case R.id.blue:
                        paint.setColour(0xFF0000FF);
                        value = "Blue selected";
                        break;
                    case R.id.indigo:
                        paint.setColour(0xFF4B0082);
                        value = "Indigo selected";
                        break;
                    case R.id.violet:
                        paint.setColour(0xFFEE82EE);
                        value = "Violet selected";
                        break;
                    case R.id.black:
                        paint.setColour(0xFF000000);
                        value = "Black selected";
                        break;
                }
                Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        }));
    }

    public void setShapeListener(final View brush, final FingerPainterView paint, final AlertDialog dialog) {
        brush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = null;
                switch (v.getId()) {
                    case R.id.round:
                        paint.setBrush(Paint.Cap.ROUND);
                        value = "Round selected";
                        break;
                    case R.id.square:
                        paint.setBrush(Paint.Cap.SQUARE);
                        value = "Square selected";
                        break;
                }
                Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
}
