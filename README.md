# DistributedSystems

This project has been realised during the course of Distributed Systems at the Danish Technical University.
The objective of our project was to design a multi sided platform improving the concept of grid computing by proposing a free wifi connection as counterpart to the individual users helping to run the project. 
The system is designed to bring together the following users:
- on the one hand, companies paying to get their computations done. 
- on the other hand, individual users waiting in airports running computation in exchange of free wifi.

This repository contains some implementation for a proof of concept, but has not been fully finished yet. Some unit tests are available, but all elements have not been implemented and integrated together.

This repository is composed of many folders. 
- ComProtocols is a folder containing implementation drafts of protocols
- MainSystem part supposed to enable the communication between users.
- SubSystem part supposed to represents the sub servers of the system.

How to use the project?

The project is based on three different projects. The one directly related to the project realization are "MainSystem" and "SubSystem". In order to build them, you will have to add the library "coollection" to these projects. 
In eclipse, to run it you should have a look in the folder lib, right click on it. Go to "Build path" -> "Add to build path". Similar thing should be done in others IDE.

Once all project can be compiled and are runnable, they should be tested by using unit tests. All unit tests are in the folder junit of the corresponding project. Explanations are provided on the purpose of all unit tests inside the java file headers. When performing tests on servers and clients, it is important to start by running the server first.