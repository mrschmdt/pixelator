# pixelator - Small project to pixelate images

This project is still under construction

Usage:

- `Pixelator.pixelate(inputPath: String, type : Main.Type) : void`

  The main method to pixelate a given image. 
  Takes inputPath (of image in jpg, png or any other ImageWriter supported fileformat),
  and a type value: Type.CIRCLE -> pixelates in circles, Type.RECTANGLE -> pixelates in rectangles
  
Current structure:

`Pixelator` 

Handles dividing the given image into parts and distributing the parts among different Threads that themselves handle pixelation
Writes the pixelated BufferedImage to the output path

`PixelatorRunnable`
A runnable that pixelates every nth (number of threads) area (the resulting pixelated areas/blocks) by iterating over the image,
calculating the average color and drawing the pixelated elements at every nth position in an empty BufferedImage from the Pixelator class


Distributing the workload among different threads is still a work in progress
Also has issues with borders

<p>
<img src="https://github.com/mrschmdt/pixelator/blob/master/data/woman%20with%20the%20pearl%20earring.jpg" alt="alt text" width="400" height="467" hspace="10">
<img src="https://github.com/mrschmdt/pixelator/blob/master/data/woman%20with%20the%20pearl%20earring%20-%20pixelated.png" alt="alt text" width="400" height="467">
</p>



