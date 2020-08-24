## Temp folder keeps populating with automatically, filling up the drive.

#### Requirements
- Java 14

#### Issue 
C:/Users/<username>/AppData/Local/Temp/ folder keeps filling up with folders that have a name like 
  002CA894-1E35-4AFB-98AF-7AA2CD989F78
  
For me, this kept happening once every minute of every hour of every day until my C drive had alsolutely no space left.
I noticed that each of these folders had the same set of .dll files, as well as a DismHost.exe.

Since I could not track down the root cause, and since I would be stuck for hours waiting for my computer to clear out all this stuff if I delete it manually, I decided to write this java program to autodetect the duplicated folders located here using a regular expression.
Then, it will iteratively delete each of these folders, sending them to the recycle bin.

By saving the deletetemps.bat file to a location on my drive, and then setting up windows task scheduler to auto run this each morning at 12:24AM, I am able to save alot of time during my actual work day. The folders are sent into the recycling bin, so they aren't deleted permanently. But by having a program running each night during off-hours and doing the grunt work of moving the files into the recycling bin, subsequent manual cleanup is very fast. Windows can delete 20 GB worth of these folders from the recycling bin extraordinarily fast, compared to the time necessary to manually remove the same 20GB of files from the appdata folder (takes literal hours).

#### Setup
Clone this repo to your local hard drive. The program is Derp.java, which needs Java 14 to be compiled and run. The script, deletetemps.bat, is what will run the java program. Store these somewhere on your hard drive, and make some modifications to the java file and the batch file. You will need to modify the paths included there to match the path on your computer. Generally speaking, you should only need to modify the username.
Give it a test run, and see if the .bat file will run. It spits out a log file in D:\deletetemps. If you do not have a D drive, just change the path in the deletetemps.bat file to match whatever path you'd like to use.
If you have issues running this, feel free to shoot me an email at cptwonton@gmail.com

#### TODO
Still, after setting this all up, I notice a few folders in appdata/temp that still follow the naming convention and still have DismHost.exe inside, but do not have anything else that came with the folder. This is probably because windows was able to delete everything that was not currently in use, and DismHost.exe was the only file still in use by "something" during the nightly delete operation. Still, this is an improvement over having EVERYTHING still in the appdata/temp folder

Deleting things permanently, rather than sending them into the recycling bin, may be a subsequent improvement. Or, emptying the recycling bin during the nightly delete. This comes with its own side effects of reducing the recycling bin's safety functionality - automatically emptying the recycling bin each night means you will not be able to recover anything the next day that you deleted previously.

