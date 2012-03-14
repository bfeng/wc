Introduction
============
The jar is a java program which actually contains two Main classes. One is WordCountJ; the other is WordCountMR.

Folders and files
=======
> src
> ===
>  This is the folder for source code.

> input
> =====
>  This folder contians testing files.

> output
> ======
>  The program result will be storaged in this folder.

> build.xml
> ========
>  This file is an ant configuration file.
  
Compile
=======
`$ ant`

Run
===
`$ java -jar dist/wc.jar`

Run in hadoop
=============
`$ bin/hadoop jar wc.jar edu.iit.cs.cs553.WordCountMR input output`