# Robot2016
The Robot Code for the iRaiders 2016 Code!

## Eclipse Setup
The project files are now included. When importing from git, import as an existing project. Do NOT import with the new project wizard.

After cloning the project, you need to init the submodule. Open the Git view and expand Robot2016, right click 'Submodules', and click Update. If you prefer command line, you can do it this way (from the adisdriver directory):
```
git submodule init
git submodule update
```

Once the project is imported, save the OpenCV 3.1.0 Java wrapper library as "(project root directory)/lib/opencv-310.jar". We use a custom version of OpenCV 3.1.0. You can find it [here](https://github.com/mmiillkkaa/opencv).

## Deploying
In order to build/deploy from this branch, OpenCV 3.1.0 must be installed on the RoboRIO to /usr/local.
