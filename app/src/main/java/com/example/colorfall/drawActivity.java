package com.example.colorfall;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.Arrays;

import yuku.ambilwarna.AmbilWarnaDialog;


//this is a View in Model-View-Controller architecture
@SuppressWarnings({"ALL", "unused"})
public class drawActivity extends AppCompatActivity implements java.io.Serializable {

    private String nameOfFile = "";
    //All variable declarations for the many, many buttons and color storing strings.
    // private drawView drawingView;
    public static drawView drawingView;
    @SuppressLint("StaticFieldLeak")
    public static ImageButton selectedColor;
    private ImageButton blueColor;
    private ImageButton yellowColor;
    private ImageButton redColor;
    private ImageButton greenColor;
    private ImageButton blackColor;
    private ImageButton violetColor;
    private ImageButton cinnamonColor;
    private ImageButton orangeMintColor;
    private ImageButton whiteColor;
    private ImageButton azureColor;
    private ImageButton eraseButton;
    boolean correctLoad;
    private ImageButton drawButton;
    Bitmap decodedBitmap;
    private ImageButton colorPicker;
    private ImageButton wipeCanvas;
    private ImageButton recentOne;
    private ImageButton recentTwo;
    private ImageButton recentThree;
    private ImageButton recentFour;
    private ImageButton recentFive;
    private ImageButton recentSix;
    private ImageButton recentSeven;
    private ImageButton recentEight;
    private ImageButton recentNine;
    private ImageButton recentTen;
    private ImageButton canvasColor;
    private ImageButton paintBucket;
    private TextView currentSizeText;
    private static boolean changingCanvasColor = false;
    private int currentColor;
    private int recentCounter = 0;
    private String colorOne = "#FF000000";
    private String colorTwo = "#FF000000";
    private String colorThree = "#FF000000";
    private String colorFour = "#FF000000";
    private String colorFive = "#FF000000";
    private String colorSix = "#FF000000";
    private String colorSeven = "#FF000000";
    private String colorEight = "#FF000000";
    private String colorNine = "#FF000000";
    private String colorTen = "#FF000000";
    String colorPicked = "#ffffff";
    private ImageButton toggleGrid;
    private static int eraserSize = 70;
    static boolean pickerClicked = false;
    public static final float currentSize = 70F;
    //testing save file -> gallery
    private String files;
    private String file_name = "";
    public static String currentTool = "pencil";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        drawingView = findViewById(R.id.drawing);
        currentSizeText = findViewById(R.id.current_size);

        SeekBar seekSize = findViewById(R.id.seekBar);
        seekSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean userInput) {
                drawingView.setStrokeWidth(progress);
                progress = progress + 10;
                eraserSize = progress;
                if(currentTool.equals("pencil"))
                {
                    currentSizeText.setText("Brush Size: "+ progress);
                }

                if(currentTool.equals("eraser"))
                {
                    currentSizeText.setText("Eraser Size: "+ progress);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //All of the linkings for buttons and their respective method calls.
        selectedColor = findViewById(R.id.current_Color);
        selectedColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTool = "pencil";
                onSelectTool(view);
            }
        });

        colorPicker = findViewById(R.id.colorPicker);
        colorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTool = "pencil";
                onSelectTool(view);
            }
        });

        paintBucket = findViewById(R.id.paint_bucket);
        paintBucket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawingView.fillColor();
                onSelectTool(view);
            }
        });

        toggleGrid = findViewById(R.id.toggle_grid);
        toggleGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.toggleGrid = !drawView.toggleGrid;
                onSelectTool(view);
                drawingView.invalidate();
            }
        });

        canvasColor = findViewById(R.id.change_background);
        canvasColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelectTool(view);
            }
        });


        ImageButton saveButton = findViewById(R.id.save_file);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSave(view);
            }
        });

        ImageButton loadButton = findViewById(R.id.load_file);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLoad(view);
            }
        });

        blueColor = findViewById(R.id.blueColor);
        blueColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTool = "pencil";
                onSelectColor(view);
            }
        });

        redColor = findViewById(R.id.redColor);
        redColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTool = "pencil";
                onSelectColor(view);
            }
        });

        greenColor = findViewById(R.id.greenColor);
        greenColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTool = "pencil";
                onSelectColor(view);
            }
        });

        yellowColor = findViewById(R.id.yellowColor);
        yellowColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTool = "pencil";
                onSelectColor(view);
            }
        });

        blackColor = findViewById(R.id.blackColor);
        blackColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTool = "pencil";
                onSelectColor(view);
            }
        });

        cinnamonColor = findViewById(R.id.cinnamonColor);
        cinnamonColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTool = "pencil";
                onSelectColor(view);
            }
        });

        orangeMintColor = findViewById(R.id.orangeMintColor);
        orangeMintColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTool = "pencil";
                onSelectColor(view);
            }
        });

        azureColor = findViewById(R.id.azureColor);
        azureColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTool = "pencil";
                onSelectColor(view);
            }
        });

        whiteColor = findViewById(R.id.whiteColor);
        whiteColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTool = "pencil";
                onSelectColor(view);
            }
        });

        violetColor = findViewById(R.id.violetColor);
        violetColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTool = "pencil";
                onSelectColor(view);
            }
        });

        wipeCanvas = findViewById(R.id.delete_canvas);
        wipeCanvas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelectColor(view);
            }
        });

        eraseButton = findViewById(R.id.eraser);
        eraseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelectTool(view);
            }
        });

        drawButton = findViewById(R.id.pixel_pen);
        drawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelectTool(view);
            }
        });

        ImageButton newColors = findViewById(R.id.new_colors);
        newColors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorWheel();
            }
        });

        saveButton = findViewById(R.id.save_file);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSave(view);
            }
        });

        recentOne = findViewById(R.id.recentOne);
        recentOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTool = "pencil";
                onSelectColor(view);
            }
        });

        recentTwo = findViewById(R.id.recentTwo);
        recentTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTool = "pencil";
                onSelectColor(view);
            }
        });

        recentThree = findViewById(R.id.recentThree);
        recentThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTool = "pencil";
                onSelectColor(view);
            }
        });

        recentFour = findViewById(R.id.recentFour);
        recentFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTool = "pencil";
                onSelectColor(view);
            }
        });

        recentFive = findViewById(R.id.recentFive);
        recentFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTool = "pencil";
                onSelectColor(view);
            }
        });

        recentSix = findViewById(R.id.recentSix);
        recentSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTool = "pencil";
                onSelectColor(view);
            }
        });

        recentSeven = findViewById(R.id.recentSeven);
        recentSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTool = "pencil";
                onSelectColor(view);
            }
        });

        recentEight = findViewById(R.id.recentEight);
        recentEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTool = "pencil";
                onSelectColor(view);
            }
        });

        recentNine = findViewById(R.id.recentNine);
        recentNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTool = "pencil";
                onSelectColor(view);
            }
        });

        recentTen = findViewById(R.id.recentTen);
        recentTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTool = "pencil";
                onSelectColor(view);
            }
        });

    }

    //Sets tools based on button presses.

    @SuppressLint("SetTextI18n")
    private void onSelectTool(View view)
    {
        if (view.getId() == drawButton.getId())
        {
            currentTool = "pencil";
            drawView.eraserMode = false;
            drawingView.setErase(false);
            drawView.isFilling = false;
            //Used for Espresso test
            Toast T = Toast.makeText(this, "Pencil selected.", Toast.LENGTH_SHORT);
            T.setGravity(Gravity.TOP|Gravity.LEFT, 350, 1150);
            T.show();
        }

        if (view.getId() == eraseButton.getId())
        {
            drawView.isFilling = false;
            currentTool = "eraser";
            drawView.eraserMode = true;
            drawingView.setErase(true);
            //Used for Espresso test
            currentSizeText.setText("Eraser Size: "+ eraserSize);
            Toast T = Toast.makeText(this, "Eraser selected.", Toast.LENGTH_SHORT);
            T.setGravity(Gravity.TOP|Gravity.LEFT, 350, 1150);
            T.show();
        }

        if (view.getId() == colorPicker.getId())
        {
            drawView.isFilling = false;
            currentTool = "picker";
            pickerClicked = true;
            //Used for Espresso test
            Toast T = Toast.makeText(this, "Color picker selected.", Toast.LENGTH_SHORT);
            T.setGravity(Gravity.TOP|Gravity.LEFT, 350, 1150);
            T.show();
        }

        if (view.getId() == paintBucket.getId())
        {
            //Used for Espresso test
            Toast T = Toast.makeText(this, "Paint Bucket selected.", Toast.LENGTH_SHORT);
            T.setGravity(Gravity.TOP|Gravity.LEFT, 350, 1150);
            T.show();
        }

        if (view.getId() == canvasColor.getId())
        {
            changingCanvasColor = true;
            ColorWheel();
        }

        if(view.getId() == toggleGrid.getId())
        {
            if (drawView.toggleGrid) {
                Toast T = Toast.makeText(this, "Grid Enabled.", Toast.LENGTH_SHORT);
                T.setGravity(Gravity.TOP | Gravity.LEFT, 350, 1150);
                T.show();
            }
            if (drawView.toggleGrid == false)
            {
                Toast T = Toast.makeText(this, "Grid Disabled.", Toast.LENGTH_SHORT);
                T.setGravity(Gravity.TOP | Gravity.LEFT, 350, 1150);
                T.show();
            }
        }

    }

    //*******************************
    //The primary function that handles all color swapping, be it for
    //the basic pre-set colors, or for grabbing colors from the "recent colors" rows.
    //Also sets the background color of the diamond in the dead center to server as an indicator for
    //what color the user has current selected.
    //*******************************

    private void onSelectColor(View view) {

        toolSelectionFacade verifyingTools = new toolSelectionFacade(currentTool, currentSize);

        if (view.getId() == blueColor.getId()) {
            drawingView.setColor("#072F5F");
            verifyingTools.verifyToolandSwapColor("#072F5F");
            currentColor = Color.parseColor("#072F5F");
            selectedColor.setBackgroundColor(currentColor);
        }

        if (view.getId() == orangeMintColor.getId()) {
            drawingView.setColor("#ff7518");
            verifyingTools.verifyToolandSwapColor("#ff7518");
            currentColor = Color.parseColor("#ff7518");
            selectedColor.setBackgroundColor(currentColor);

        }

        if (view.getId() == violetColor.getId()) {
            drawingView.setColor("#800080");
            verifyingTools.verifyToolandSwapColor("#800080");
            currentColor = Color.parseColor("#800080");
            selectedColor.setBackgroundColor(currentColor);
        }

        if (view.getId() == cinnamonColor.getId()) {
            drawingView.setColor("#D2691E");
            verifyingTools.verifyToolandSwapColor("#D2691E");
            currentColor = Color.parseColor("#D2691E");
            selectedColor.setBackgroundColor(currentColor);
        }

        if (view.getId() == azureColor.getId()) {
            drawingView.setColor("#2471A3");
            verifyingTools.verifyToolandSwapColor("#2471A3");
            currentColor = Color.parseColor("#2471A3");
            selectedColor.setBackgroundColor(currentColor);
        }

        if (view.getId() == whiteColor.getId()) {
            drawingView.setColor("#ffffff");
            verifyingTools.verifyToolandSwapColor("#ffffff");
            currentColor = Color.parseColor("#ffffff");
            selectedColor.setBackgroundColor(currentColor);
        }

        if (view.getId() == redColor.getId()) {
            drawingView.setColor("#FFFF0000");
            verifyingTools.verifyToolandSwapColor("#FFFF0000");
            currentColor = Color.parseColor("#FFFF0000");
            selectedColor.setBackgroundColor(currentColor);
        }

        if (view.getId() == yellowColor.getId()) {
            drawingView.setColor("#FFFF00");
            verifyingTools.verifyToolandSwapColor("#FFFF00");
            currentColor = Color.parseColor("#FFFF00");
            selectedColor.setBackgroundColor(currentColor);
        }

        if (view.getId() == greenColor.getId()) {
            drawingView.setColor("#00FF3E");
            verifyingTools.verifyToolandSwapColor("#00FF3E");
            currentColor = Color.parseColor("#00FF3E");
            selectedColor.setBackgroundColor(currentColor);
        }

        if (view.getId() == blackColor.getId()) {
            drawingView.setColor("#FF000000");
            verifyingTools.verifyToolandSwapColor("#FF000000");
            currentColor = Color.parseColor("#FF000000");
            selectedColor.setBackgroundColor(currentColor);
        }

        if (view.getId() == recentOne.getId())
        {
            if(colorOne.equals("#FF000000"))
            {
                Toast T = Toast.makeText(this, "No recent colors!\nColor set to black instead.", Toast.LENGTH_SHORT);
                T.setGravity(Gravity.TOP|Gravity.LEFT, 350, 1150);
                T.show();
            }
            drawingView.setColor(colorOne);
            verifyingTools.verifyToolandSwapColor(colorOne);
            currentColor = Color.parseColor(colorOne);
            selectedColor.setBackgroundColor(currentColor);
        }

        if (view.getId() == recentTwo.getId())
        {
            if(colorTwo.equals("#FF000000"))
            {
                Toast T = Toast.makeText(this, "No recent colors!\nColor set to black instead.", Toast.LENGTH_SHORT);
                T.setGravity(Gravity.TOP|Gravity.LEFT, 350, 1150);
                T.show();
            }
            drawingView.setColor(colorTwo);
            verifyingTools.verifyToolandSwapColor(colorTwo);
            currentColor = Color.parseColor(colorTwo);
            selectedColor.setBackgroundColor(currentColor);
        }

        if (view.getId() == recentThree.getId())
        {
            if(colorThree.equals("#FF000000"))
            {
                Toast T = Toast.makeText(this, "No recent colors!\nColor set to black instead.", Toast.LENGTH_SHORT);
                T.setGravity(Gravity.TOP|Gravity.LEFT, 350, 1150);
                T.show();
            }
            drawingView.setColor(colorThree);
            verifyingTools.verifyToolandSwapColor(colorThree);
            currentColor = Color.parseColor(colorThree);
            selectedColor.setBackgroundColor(currentColor);
        }

        if (view.getId() == recentFour.getId())
        {
            if(colorFour.equals("#FF000000"))
            {
                Toast T = Toast.makeText(this, "No recent colors!\nColor set to black instead.", Toast.LENGTH_SHORT);
                T.setGravity(Gravity.TOP|Gravity.LEFT, 350, 1150);
                T.show();
            }
            drawingView.setColor(colorFour);
            verifyingTools.verifyToolandSwapColor(colorFour);
            currentColor = Color.parseColor(colorFour);
            selectedColor.setBackgroundColor(currentColor);
        }

        if (view.getId() == recentFive.getId())
        {
            if(colorFive.equals("#FF000000"))
            {
                Toast T = Toast.makeText(this, "No recent colors!\nColor set to black instead.", Toast.LENGTH_SHORT);
                T.setGravity(Gravity.TOP|Gravity.LEFT, 350, 1150);
                T.show();
            }
            drawingView.setColor(colorFive);
            verifyingTools.verifyToolandSwapColor(colorFive);
            currentColor = Color.parseColor(colorFive);
            selectedColor.setBackgroundColor(currentColor);
        }

        if (view.getId() == recentSix.getId())
        {
            if(colorSix.equals("#FF000000"))
            {
                Toast T = Toast.makeText(this, "No recent colors!\nColor set to black instead.", Toast.LENGTH_SHORT);
                T.setGravity(Gravity.TOP|Gravity.LEFT, 350, 1150);
                T.show();
            }
            drawingView.setColor(colorSix);
            verifyingTools.verifyToolandSwapColor(colorSix);
            currentColor = Color.parseColor(colorSix);
            selectedColor.setBackgroundColor(currentColor);
        }

        if (view.getId() == recentSeven.getId())
        {
            if(colorThree.equals("#FF000000"))
            {
                Toast T = Toast.makeText(this, "No recent colors!\nColor set to black instead.", Toast.LENGTH_SHORT);
                T.setGravity(Gravity.TOP|Gravity.LEFT, 350, 1150);
                T.show();
            }
            drawingView.setColor(colorSeven);
            verifyingTools.verifyToolandSwapColor(colorSeven);
            currentColor = Color.parseColor(colorSeven);
            selectedColor.setBackgroundColor(currentColor);
        }

        if (view.getId() == recentEight.getId())
        {
            if(colorThree.equals("#FF000000"))
            {
                Toast T = Toast.makeText(this, "No recent colors!\nColor set to black instead.", Toast.LENGTH_SHORT);
                T.setGravity(Gravity.TOP|Gravity.LEFT, 350, 1150);
                T.show();
            }
            drawingView.setColor(colorEight);
            verifyingTools.verifyToolandSwapColor(colorEight);
            currentColor = Color.parseColor(colorEight);
            selectedColor.setBackgroundColor(currentColor);
        }

        if (view.getId() == recentNine.getId())
        {
            if(colorThree.equals("#FF000000"))
            {
                Toast T = Toast.makeText(this, "No recent colors!\nColor set to black instead.", Toast.LENGTH_SHORT);
                T.setGravity(Gravity.TOP|Gravity.LEFT, 350, 1150);
                T.show();
            }
            drawingView.setColor(colorNine);
            verifyingTools.verifyToolandSwapColor(colorNine);
            currentColor = Color.parseColor(colorNine);
            selectedColor.setBackgroundColor(currentColor);
        }

        if (view.getId() == recentTen.getId())
        {
            if(colorThree.equals("#FF000000"))
            {
                Toast T = Toast.makeText(this, "No recent colors!\nColor set to black instead.", Toast.LENGTH_SHORT);
                T.setGravity(Gravity.TOP|Gravity.LEFT, 350, 1150);
                T.show();
            }
            drawingView.setColor(colorTen);
            verifyingTools.verifyToolandSwapColor(colorTen);
            currentColor = Color.parseColor(colorTen);
            selectedColor.setBackgroundColor(currentColor);
        }

        //*****************************
        //Wipes the canvas clean so the user doesn't have to tediously erase the entire screen.
        //*****************************

        if (view.getId() == wipeCanvas.getId()) {
            AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
            newDialog.setTitle("      Would you like to wipe the canvas?");
            newDialog.setPositiveButton("[Yes]", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    drawingView.wipeCanvas();
                    dialog.dismiss();
                }
            });
            newDialog.setNegativeButton("[No]", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            newDialog.show();

        }
    }


    //**********************
    //Method that opens a color wheel when clicking the + sign, and then
    //Swaps the color of the brush to the selected color if the user clicks ok
    //after selecting a color.
    //Does nothing if the user selects cancel.
    //ONLY COLORS ADDED BY USING THE COLOR WHEEL ARE ADDED TO THE RECENT COLORS ROWS.
    //It's redundant to add the base colors to the recent colors rows anyways.
    //***********************

    private void ColorWheel()
    {

        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(this, currentColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                changingCanvasColor = false;
                System.out.println("Color wheel canceled.");
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color)
            {

                if (changingCanvasColor)
                {
                    drawingView.setBackgroundColor(color);
                    changingCanvasColor = false;
                }
                else {
                    currentColor = color;
                    String colorStr= "#"+Integer.toHexString(color);
                    System.out.println("\n"+colorStr);
                    drawingView.setColor(colorStr);
                    selectedColor.setBackgroundColor(color);
                    recentCounter++;
                    if (recentCounter == 1) {
                        recentOne.setBackgroundColor(color);
                        colorOne = colorStr;
                    }

                    if (recentCounter == 2) {
                        recentTwo.setBackgroundColor(color);
                        colorTwo = colorStr;
                    }

                    if (recentCounter == 3) {
                        recentThree.setBackgroundColor(color);
                        colorThree = colorStr;
                    }

                    if (recentCounter == 4) {
                        recentFour.setBackgroundColor(color);
                        colorFour = colorStr;
                    }

                    if (recentCounter == 5) {
                        recentFive.setBackgroundColor(color);
                        colorFive = colorStr;

                    }

                    if (recentCounter == 6) {
                        recentSix.setBackgroundColor(color);
                        colorSix = colorStr;
                    }

                    if (recentCounter == 7) {
                        recentSeven.setBackgroundColor(color);
                        colorSeven = colorStr;
                    }

                    if (recentCounter == 8) {
                        recentEight.setBackgroundColor(color);
                        colorEight = colorStr;
                    }

                    if (recentCounter == 9) {
                        recentNine.setBackgroundColor(color);
                        colorNine = colorStr;
                    }

                    if (recentCounter == 10) {
                        recentTen.setBackgroundColor(color);
                        colorTen = colorStr;
                        recentCounter = 0;
                    }
                }
            }
        });
        ambilWarnaDialog.show();
    }

    //*********************************
    //Saves the bitmap and stores it as a file.
    //*********************************

    private void save() {
        Context context = getApplicationContext();
        String fileName = context.getFilesDir().getPath() + "/";
        fileName = fileName + nameOfFile + ".ser";

        File file = new File(fileName);
        if (file.exists()) {
            double bytes = file.length();
            Log.d("TAG", "bytes: " + bytes);
            Log.d("TAG", "file path: " + fileName);
        } else {
            Log.d("TAG", "file does not exist");
        }
        //Calls save process
        bitmapSaveLoad.saveBitmap(fileName, drawingView.getBitmap());

        TextView canvas_title = (TextView) findViewById(R.id.canvas_title);
        if(file.exists())
        {
            fileName = fileName.substring(41, (fileName.length() - 4));
            canvas_title.setText(fileName);
        }
        else
            canvas_title.setText("FAILURE TO SAVE");

    }

    //*********************************
    //Displays all currently saved files as a way to check files are properly being stored.
    //*********************************
    private void printSavedFiles() {
        Context context = getApplicationContext();
        System.out.println(context.getFilesDir().getAbsolutePath());
        String[] fList = context.fileList();
        System.out.println(Arrays.toString(fList) + "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        Log.d("TAG", "files:" + Arrays.toString(fList));
        files+=Arrays.toString(fList) + "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n";
    }

    public String getFiles(){
        return files;
    }

    //*********************************
    //Loads a chosen bitmap back to the canvas.
    //*********************************
    private void load() {
        //setup

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("What picture would you like to load?");
        final EditText input = new EditText(this);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nameOfFile = input.getText().toString();
                Context context = getApplicationContext();//if errors occur down the road, its likely from this
                String fileName = context.getFilesDir().getPath() + "/" + nameOfFile + ".ser";
                System.out.println(fileName);

                //check if file exists and is non empty
                File file = new File(fileName);

                if(file.exists()) {
                    double bytes = file.length();
                    Log.d("TAG", "bytes: " + bytes);
                    Log.d("TAG", "file path: " + fileName);
                    decodedBitmap = bitmapSaveLoad.loadBitmap(fileName);
                    correctLoad = drawingView.setBitmap(decodedBitmap);
                }
                else
                {
                    Log.d("TAG", "file does not exist");

                }
                TextView canvas_title = (TextView) findViewById(R.id.canvas_title);
                if(correctLoad)
                {
                    fileName = fileName.substring(41, (fileName.length() - 4));
                    canvas_title.setText(fileName);
                    correctLoad = false;
                }
                else
                    canvas_title.setText("File does not exist. Try to load another.");

                Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    //onCLick method for save button
    private void onClickSave(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Name of your masterpiece");
        final EditText input = new EditText(this);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nameOfFile = input.getText().toString();
                save();
                Toast.makeText(getApplicationContext(), "Drawing saved...", Toast.LENGTH_SHORT).show();
                printSavedFiles();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    @SuppressWarnings("unused")
    //Calls load on button click
    private void onClickLoad(View view) {
        load();
    }

    @SuppressWarnings("unused")
    //Returns the drawingView object when called
    public static drawView getDrawingView() {
        return drawingView;
    }
}
