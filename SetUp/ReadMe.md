# Set Up Java

## For Windows
1. [Install Java (JDK)](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
2. Set the JAVA_HOME variable:
    * Find out where Java is installed. If you didn't change the path during installation, it will be something like this:
      `C:\Program Files\Java\jdk1.8.0_65`
    * In Windows 7 right click My Computer and select Properties > Advanced.
      In Windows 8/10 go to Control Panel > System > Advanced System Settings
    * Click the Environment Variables button.
    * Under System Variables, click New
    * In the Variable Name field, enter:
      `JAVA_HOME`
    
    * In the Variable Value field, enter your JDK installation path.
      If the path contains spaces, use the shortened path name, for example C:\Progra~1\Java\jdk1.8.0_65)
    
    * Click OK and Apply Changes as prompted.

## For Mac
1. Open Up The Terminal
2. Install [homebrew](https://brew.sh/)
3. Enter The Command Below:

`brew cask install java`


