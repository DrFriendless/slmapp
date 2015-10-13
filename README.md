# slmapp
Statistical Language Modelling Android Application

This is the Android app component of the Statistical Language Modelling application.
It's an Android app written in Java 7 using SVG.
SVG is not interesting except that it caused the biggest problem - some versions of Android turn on hardware acceleration by default, and with hardware acceleration on the SVG pictures don't render.
The SVG library source code is included in this repository as various other libraries I tried were incompatible with different versions of Android.
A more complete description of the project is in the file "Dinky Corpus Project Report.pdf".
