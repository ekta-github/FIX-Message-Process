FIX Message Processor
=====================

A console application that prompts the user for a FIX 4.4 NewOrderSingle message, parses the input, and validates the message
for errors.

Usage
-----

#### To run the implementation that does not use any third-party FIX engines:
Import as a project in Eclipse and Run as a Java Application.
Alternatively run the following at the command prompt to execute the program:
```
java -cp fix-message-processor.jar com.richardarcega.fix.client.ClientImpl
```

#### To run the implementation that utilizes the QuickFix/J FIX Engine:
Import as a project in Eclipse and Run as a Java Application.
Alternatively run the following at the command prompt to execute the program:
```
java -cp fix-message-processor.jar com.richardarcega.fix.client.ClientQuickFixJImpl
```


Building
--------

#### To build the project using Maven, run the following goal:
```
mvn clean javadoc:jar source:jar package
```



Sample I/O
----------

Type 'exit' to quit
Enter FIX 4.4 NewOrderSingle message >>> 8=FIX.4.49=12235=D34=21549=CLIENT1252=20100225-19:41:57.31656=B1=Marcel11=1334621=140=255=MSFT38=144=554=159=060=20100225-19:39:52.02010=4
FIX message is valid!
```



License
-------
This is free and unencumbered software released into the public domain.

Anyone is free to copy, modify, publish, use, compile, sell, or
distribute this software, either in source code form or as a compiled
binary, for any purpose, commercial or non-commercial, and by any
means.

In jurisdictions that recognize copyright laws, the author or authors
of this software dedicate any and all copyright interest in the
software to the public domain. We make this dedication for the benefit
of the public at large and to the detriment of our heirs and
successors. We intend this dedication to be an overt act of
relinquishment in perpetuity of all present and future rights to this
software under copyright law.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.