package com.example.colorfall;

/*****************************************
 * Facade that hides the process of validating a tool first to ensure
 * that it's a tool that can have its color/size changed.
 * This has no current real use, outside of changing the color of the brush.
 * However, it serves as a future proofing design pattern to make it so when adding future tools
 * we can check if the tool is capable of changing colors or changing size via this facade.
 * For instance, a brush can change color and size, and eraser cannot change color but can change size,
 * but a bucket fill tool could change color but not size. This facade makes it so adding something like a rotate/panning
 * tool is not capable of changing the color or size of the brush/other tools.
 */


class toolSelectionFacade
{
    private final String selectedTool;
    private final float toolSize;

    private final selectedToolCheck toolChecker;
    private final currentSizeCheck sizeChecker;

    public toolSelectionFacade(String newTool, float newToolSize)
    {
        selectedTool = newTool;
        toolSize = newToolSize;

        toolChecker = new selectedToolCheck();
        sizeChecker = new currentSizeCheck();
    }

    private String getSelectedTool() { return selectedTool;}
    private float getSize() { return toolSize;}

    public void verifyToolandSwapColor(String colorToSwapTo)
    {
        if (toolChecker.verifyTool(getSelectedTool()) && sizeChecker.verifySize(getSize()))
        {
            System.out.println("\nCOLOR HAS BEEN SWAPPED  "+ colorToSwapTo);
            drawActivity.drawingView.setColor(colorToSwapTo);
        }
        else
        {
            System.out.println("\n The tool is not a valid tool that can change colors or size.");
        }
    }
}
