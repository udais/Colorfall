package com.example.colorfall;
//*************************
//Class that performs a size check for tools behind the scenes.
//**************************
@SuppressWarnings("SameReturnValue")
class currentSizeCheck
{

    private float getSize() {
        return 70F;}

    public boolean verifySize(float sizeToCheck)
    {
        return sizeToCheck == getSize();

    }
}
