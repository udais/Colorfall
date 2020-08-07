This app is going to be a simple drawing application that will allow the user to draw pictures, save them, export them, 
and import templates.

ITERATION #1 ___________________________________________________________________________________________________________

CODE NOTES: 
The '//WORK IN PROGRESS//' header indicates codeblocks or files [if found under the import section] that were started 
but not completed for this iteration. 
WIP files include: TemplatesActivity, RecycleViewAdapter, ourPath, importActivity, GalleryItem, and galleryActivity


TESTING: 
We have successfully implemented testing for the Draw user story.

Unfortunately, due to the drawing library we’re using, we can't use Espresso to test. Espresso does not record the strokes
or movement of the brush on the canvas. This makes it impossible to perform tests where we draw on the screen, switch colors
and then draw on the screen, erase any drawings on the screen, or wiping the canvas clean after drawing on the screen.

Because of the nature of our app, drawing something on the screen and then testing if what we're drawing is actually
on the screen with Espresso is not possible. We will need to use Mockito in further iterations
to force the canvas to have dummy data.

For the Add a Drawing User Story: 
Espresso has no way to check if a drawing object exists. 

For the Draw User Story: 
The actual drawing action is not recorded and the brush movements are handled internal in the library; 
therefore, we cannot use Espresso to test it. 

For the Erase User Story: 
The actual erase action is not recorded and the eraser movements are handled internal in the library; 
therefore, we cannot use Espresso to test it. 

For the Erasing The Entire Canvas User Story: 
Espresso cannot directly check the contents of the drawing object, so it is not able to determine if the canvas is wiped.

ITERATION #2 ___________________________________________________________________________________________________________

 This iteration was a lot easier to do because all of the work in iteration #1 made it easier to build upon and implement the color 
 tools. However, saving and loading the actual drawings is turning out to be much harder than anticipated. Despite spending over half
 the iteration working on saving/loading, it's proving to be so difficult that working on it has been pushed to iteration #3, and we 
 instead decided to focus on adding more functionality to the canvas. However, the following was still added and works:
 
 +A color picker tool that can select colors already placed on the canvas.
 +The ability to select any specific color you want via a color wheel.
 +Changing the brush/eraser size.
 +An indicator for which color you currently have selected.
 +A bank for your top 10 recent colors added from using the color wheel.
 +5 more colors added to the pre-set rows.
 
TESTING: 
We have successfully implemented testing for the brush/eraser size changing.

However, just like iteration #1, espresso does not keep track of anything drawn onto the canvas. Because of this, the
color changing user stories for the color wheel, color picker, and recent colors were not possible to do. The new tools 
obviously work as intended and have been tested extensively manually, but grabbing the color values of the brush is not possible.

For the brush/eraser size changing user story:
The size is artificially tested by checking the textview to see that the number in the textview changes when resizing the brush,
but it doesn't grab the the size directly from the brush. Instead, the textview just reflects the changes in the brush size.

For the color picker/color indicator user story:
The color picker requires that we draw onto the canvas with a color, then use the color picker to grab that color, but as stated before, 
drawing/coloring on the canvas is not recorded by espresso.

For the color wheel user story and the recent colors user story:
The same goes for the color wheel and the recent colors bank, because we 
can change colors using either of those tools, but espresso won't record drawing on the canvas or let us verify that the color is what
we changed to.

THE DESIGN PATTERN:FACADE
 Facade design pattern was implemented to hide the process of validating a tool first to ensure that it's a tool that can have its
 color/size changed, then changing the size/color if valid. The currentToolCheck and currentSizeCheck classes do as their names imply;
 Each verify if the current tool is a tool that can change in size or color. Then the toolSelectionFacade class combines both of those
 classes and verifies/checks color/checks size/changes color/changes size all behind the scenes with a single call of             
 "verifyingTools.verifyToolandSwapColor(colorFour);" within the drawActivity.
 This has no current real use, outside of changing the color of the brush, because all current tools can change color and/or size.
 However, it serves as a future proofing design pattern to make it so when adding future tools
 we can check if the tool is capable of changing colors or changing size via this facade.
 For instance, a brush can change color and size, and eraser cannot change color but can change size,
 but a bucket fill tool could change color but not size. This facade makes it so adding something like a rotate/panning
 tool, which should not be capable of size/color changes, is not capable of changing the color or size of the brush/other tools/itself.

ITERATION #3 ___________________________________________________________________________________________________________
 This iteration was focused mainly on finally fixing the saving/loading functions that have persisted since the first iteration and
 cleaning up the code with Lint. The following was added:
 
 +Saving a drawing and having its name added to the gallery.
 +Loading a drawing and having it appear on the canvas, along with its name.
 +A paint bucket that works with any color and fills any enclosed area or connected lines.
 +An option to toggle the grid.
 +The ability to change the background color of the entire canvas.
 +The eraser will erase space to match the canvas color.
 
 LINT:
 A good portion of the warnings were from 'spelling errors.' For many of the variable names, and there were a lot of variables, 
 names such as 'paint_bucket' triggered warnings. That's just the naming convention that's most common, so all warnings for spelling 
 were suppressed. Besides that, some more warnings were simplification of small snippets of code, such as 'if (gridEnabled == true)'
 causing a warning to change it to just 'if (gridEnabled)'. All of the trivial warnings were fixed, and all of the warnings triggered
 by preferences were suppressed.
 
 TESTING:
 This time around, all functionality was possible to test because we used textviews that updated when saving/loading/toggling/color
 changing. Therefore, we were able to confirm functionality by ensuring a textview matches what they should.
 
 For instance:
 For saving, we could confirm the file was saved by showing that the file appeared in the gallery and the canvas title was updated.
 For loading, we could confirm the file was loaded by checking if it's in the gallery, and when loaded the canvas title was updated.
 For toggling the grid, the canvas grid appears or disappears when toggled, displaying a toast that says the grid's current state.
 For changing the background color, the canvas background color is changed and checked.
 The paint bucket is the only one where the functionality is not visible in the test cases because of the same issues as before
 when it comes recording actions done to the canvas. However, the test still confirms that the paint bucket was selected via
 selectedTool variable and a toast that displays the current tool.
 
 
 
