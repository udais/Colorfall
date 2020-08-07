package com.example.colorfall;

import android.graphics.Path;
import android.util.Log;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

//WORK IN PROGRESS//
@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class ourPath extends Path implements Serializable {

    private static final long serialVersionUID = -5974912367682897467L;//used for serializing

    //private drawView drawingView;
    private final List<Action> actions = new LinkedList<>();//list where all user actions are stored
    private final List<ourPaint> brushes = new LinkedList<>();//list of brushes which contains color, size etc.



    /***********************************Testing methods***************************/
    public void printList() {
        int i = 1;
        for (Action action : actions) {
            System.out.println(action);
            System.out.println(i);
            i++;
        }
    }
    /*******************************end Testing methods***************************/

    @SuppressWarnings("unused")
    private interface Action extends Serializable {
        void perform(Path path);
    }//end Action


    /****************Overrides****************************/
    @Override
    public void moveTo(float x, float y) {
        brushes.add(drawView.getBrush());
        actions.add(new Move(x, y));

        super.moveTo(x, y);
        Log.d("TAG","in move drawing method. x=" + x + " y=" +y + " SIZE = " +  + actions.size());
    }

    @Override
    public void lineTo(float x, float y) {
        actions.add(new Line(x, y));


        super.lineTo(x, y);
        Log.d("TAG","in line drawing method. x=" + x + " y=" +y + " SIZE = " +  + actions.size());
    }


    /**************inner MOVE class*******************/
    private static final class Move implements Action, Serializable {

        private final float x, y;

        Move(float x, float y) {
            this.x = x;
            this.y = y;
            Log.d("TAG","in move constructor. x=" + x + " y=" +y);
        }

        @Override
        public void perform(Path path) {
            path.moveTo(x, y);
        }
    }


    /**************inner LINE class*******************/
    private static final class Line implements Action, Serializable {

        private final float x, y;

        Line(float x, float y) {
            this.x = x;
            this.y = y;
            Log.d("TAG","in line constructor. x=" + x + " y=" +y);
        }

        @Override
        public void perform(Path path) {
            path.lineTo(x, y);
        }
    }
}
