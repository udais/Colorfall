package com.example.colorfall;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

//this is the Controller in view-controller-module architecture
/**********************************************************************************************
 * Description  : Setting the default details for the brush type, the size of the brush
 *                and how it looks. Also specifies how the app functions if you hold you
 *                touch the screen to draw.
 *
 *                I'll have to fill in more on this later, I still have to figure out how to
 *                allow the size of the canvas to be changed.
 *
 *
 *
 * Author       : Jonathan
 * Date         : 10/13/2019
 *********************************************************************************************/

@SuppressWarnings("unused")
public class drawView extends View implements Serializable
{
    private ourPath path;
    private static ourPaint drawPixel;                //Our brush object
    private Paint gridLines;
    private Paint pixelCanvasPaint;
    private int currentColor = 0xFF000000;
    private int pickedColor = 0xFF000000;
    private static Canvas drawPixelCanvas;
    private static Bitmap canvasPixelBitmap;
    private static Matrix drawMatrix;
    static final String hexValuePicked = "#0";
    static boolean toggleGrid = false;
    public static String pixelStr;
    public static boolean isFilling = false;
    public static boolean eraserMode = false;

    public void setErase(boolean isErase)
    {
        eraserMode = isErase;
        if(eraserMode) drawPixel.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        else drawPixel.setXfermode(null);
    }
    //constructor
    public drawView(Context context, AttributeSet attributes)
    {
        super(context, attributes);
        initializePixelArt();
    }

    //************************
    //Specifies what the app will do to the canvas when clicking, clicking and dragging, or releasing.
    //************************
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        toolSelectionFacade verifyingTools = new toolSelectionFacade(drawActivity.currentTool,
                drawActivity.currentSize);
        float pointX = event.getX();
        float pointY = event.getY();

        switch(event.getAction())
        {

            case MotionEvent.ACTION_DOWN:
                path.moveTo(pointX, pointY);

                int pixel = canvasPixelBitmap.getPixel((int) pointX, (int) pointY);
                if(pointX > 0 && pointY > 0 && drawActivity.pickerClicked)
                {
                    drawPixelCanvas.setBitmap(canvasPixelBitmap);
                    int redValue = Color.red(pixel);
                    int blueValue = Color.blue(pixel);
                    int greenValue = Color.green(pixel);
                    drawPixel.setColor(pixel);
                    drawActivity.selectedColor.setBackgroundColor(pixel);
                    pixelStr = Integer.toString(pixel);
                    currentColor = pixel;
                    verifyingTools.verifyToolandSwapColor(pixelStr);
                    drawActivity.pickerClicked = false;
                }

                return true;
            case MotionEvent.ACTION_MOVE:

                path.lineTo(pointX, pointY);

                break;
            case MotionEvent.ACTION_UP:
                if (isFilling)
                {
                    FloodFill(new Point((int) pointX, (int) pointY));
                    break;
                }
                drawPixelCanvas.setBitmap(canvasPixelBitmap);
                drawPixelCanvas.drawPath(path, drawPixel);
                path.reset();
                break;
            default:
                return false;
        }
        postInvalidate();
        return true;
    }

    //Initializes the canvas and the brush tool properties.

    private void initializePixelArt()
    {
        path = new ourPath();
        gridLines = new Paint();
        drawPixel = new ourPaint();

        gridLines.setColor(Color.BLACK);
        gridLines.setStrokeWidth(1F);
        drawPixel.setColor(currentColor);
        drawPixel.setAntiAlias(false);
        drawPixel.setStrokeWidth(70F);
        drawPixel.setStyle(Paint.Style.STROKE);
        drawPixel.setStrokeJoin(Paint.Join.ROUND);
        drawPixel.setStrokeCap(Paint.Cap.ROUND);
        pixelCanvasPaint = new Paint(Paint.DITHER_FLAG);
    }
    /***********************************************************************************************
     *  Method : setColor()
     *  Description: sets brush color parameter
     *
     *
     *
     **********************************************************************************************/
    //Color Setter method
    public void setColor(String color)
    {
        invalidate();
        currentColor = Color.parseColor(color);
        drawPixel.setColor(currentColor);
    }

    //Size Setter Method
    public void setStrokeWidth(float size)
    {
        invalidate();
        drawPixel.setStrokeWidth(size);
    }

    //Returns the canvas to a clean slate.
    public void wipeCanvas()
    {
        drawPixelCanvas.setBitmap(canvasPixelBitmap);
        drawPixelCanvas.drawColor(Color.TRANSPARENT,PorterDuff.Mode.CLEAR);
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(0, 0, oldw, oldh);
        canvasPixelBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawPixelCanvas = new Canvas(canvasPixelBitmap);
    }

    //Creates the grid effect across the canvas to help the user maybe line up their strokes better.
    //Will most likely have a disable option in the future.

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasPixelBitmap, 0, 0, pixelCanvasPaint);
        canvas.drawPath(path, drawPixel);
        if (toggleGrid) {
            int width = getMeasuredWidth();
            int height = getMeasuredHeight();
            // Vertical lines
            for (int i = 1; i < 16; i++) {
                canvas.drawLine(width * i / 16, 0, width * i / 16, height,
                        gridLines);
            }

            // Horizontal lines
            for (int i = 1; i < 16; i++) {
                canvas.drawLine(0, height * i / 16, width, height * i / 16,
                        gridLines);
            }
        }
    }

    public Bitmap getBitmap(){ return canvasPixelBitmap;}

    public boolean setBitmap(Bitmap overwrite){
        canvasPixelBitmap = overwrite;
        initializePixelArt();
        invalidate();

        return canvasPixelBitmap == overwrite;
    }

    public static ourPaint getBrush() {return drawPixel; }

    public void fillColor() {
        isFilling = true;
    }

    private synchronized void FloodFill(Point startPoint) {

        Queue<Point> queue = new LinkedList<>();
        queue.add(startPoint);

        int targetColor = canvasPixelBitmap.getPixel(startPoint.x, startPoint.y);

        while (queue.size() > 0) {
            Point nextPoint = queue.poll();
            assert nextPoint != null;
            if (canvasPixelBitmap.getPixel(nextPoint.x, nextPoint.y) != targetColor)
                continue;

            Point point = new Point(nextPoint.x + 1, nextPoint.y);

            while ((nextPoint.x > 0) && (canvasPixelBitmap.getPixel(nextPoint.x, nextPoint.y) ==
                    targetColor)) {
                canvasPixelBitmap.setPixel(nextPoint.x, nextPoint.y, currentColor);
                if ((nextPoint.y > 0) && (canvasPixelBitmap.getPixel(nextPoint.x, nextPoint.y -
                        1) == targetColor))
                    queue.add(new Point(nextPoint.x, nextPoint.y - 1));
                if ((nextPoint.y < canvasPixelBitmap.getHeight() - 1) && (canvasPixelBitmap.
                        getPixel(nextPoint.x, nextPoint.y + 1) == targetColor))
                    queue.add(new Point(nextPoint.x, nextPoint.y + 1));
                nextPoint.x--;
            }

            while ((point.x < canvasPixelBitmap.getWidth() - 1) && (canvasPixelBitmap.
                    getPixel(point.x, point.y) == targetColor)) {
                canvasPixelBitmap.setPixel(point.x, point.y, currentColor);
                if ((point.y > 0) && (canvasPixelBitmap.getPixel(point.x, point.y - 1) ==
                        targetColor))
                    queue.add(new Point(point.x, point.y - 1));
                if ((point.y < canvasPixelBitmap.getHeight() - 1)
                        && (canvasPixelBitmap.getPixel(point.x, point.y + 1) == targetColor))
                    queue.add(new Point(point.x, point.y + 1));
                point.x++;
            }
        }
    }
}
