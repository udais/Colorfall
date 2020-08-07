package com.example.colorfall;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileOutputStream;
import java.io.IOException;

class bitmapSaveLoad
{
    static public Bitmap loadBitmap(String fileName)
    {
        Bitmap loadedBitmap = BitmapFactory.decodeFile(fileName); //Creates temp Bitmap instance
        loadedBitmap = loadedBitmap.copy(Bitmap.Config.ARGB_8888, true); //Ensures Bitmap is Mutable
        return loadedBitmap;
    }

    static public void saveBitmap(String fileName, Bitmap bitmap)
    {
        try (FileOutputStream out = new FileOutputStream(fileName))
        {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out); //Save bitmap as PNG
            //NOTE as PNG is a lossless format, the compression factor (100) is ignored

            out.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}