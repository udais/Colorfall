package com.example.colorfall;
//*************************
//Class that performs a check to ensure the tool selected is in fact a tool that can be modified.
//**************************
@SuppressWarnings("SameReturnValue")
class selectedToolCheck
{

    private String getTool() {
        return "pencil";}

    public boolean verifyTool(String toolToCheck)
    {
        return toolToCheck.equals(getTool());

    }

}
