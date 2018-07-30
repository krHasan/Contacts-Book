# Contacts-Book
It's a desktop application build with JavaFX framework. User can add contacts with customer name, number, address and customer priority. No doubt it is mainly focused for the business contacts. That's why I use 'Customer Name' instead of 'Name', here 'Priority' comes to a meaning. Obviously Contacts-Book have Edit and Delete option also. 

## Built With
-		Language: Java
-		Framework: JavaFX
-		Database: SQLite
-   	Ant Build

## Workthrough
Generally a contacts saving application contains very common features like adding, editing and deleting contacts. 'Contacts-Book' have all that with some focusing features. 

### Autocomplete 
It shows up to 5 autocomplete best matching suggestions based on the data on it's database. It gives a message to user that if the input **Information are already is in the database** or not. As user click on a suggestion the text field will fill up with that.
<p align="center">
  <img src="/files/autosuggestions.png?raw=true">
</p>

### Button Changes
As long as the **Required field** will not filled user will show the cancel button. All fields are filled up the button will changed and ready for action.
<p align="center">
  <img src="/files/btnChanges.png?raw=true">
</p>

### Copy to Clipboard
When user need to copy a information from the table, how do they do it. A non-editable table won't let user to select its content. So I added copy to clipboard facility. All you have to do is **Click Once** on the cell whose text you want to get, it will copy to your clipboard. You just past and use it.
<p align="center">
  <img src="/files/copy.png?raw=true">
</p>
<p align="center">
  <img src="/files/past.png?raw=true">
</p>

### Three Clicks to get Edit option
I intentionally didn't add any button or menu option to go to contact edit window. The only way is you have to **Click 3 Times** on that contact row whose information you want to update.
<p align="center">
  <img src="/files/3click.png?raw=true">
</p>

### Custom Dialog
The built-in JavaFX Dialog isn't look very pretty and you have no chance to edit its graphics. So I let it go and create my own custom dialog window. They are very nice looking and works as like as the built-in do.
<p align="center">
  <img src="/files/dialogs.png?raw=true">
</p>

### Data Backup and Restore
As the contacts are very important to persistent so I let user to backup a copy of their database to anywhere to their system and also restore when they needed. 
<p align="center">
  <img src="/files/backup.png?raw=true">
</p>

### Menu With Icons
And here comes the Menu. As like dialog the built-in menu also looks very ancient. So I added some css to give a stylish looking menu items with some meaningful icons.
<p align="center">
  <img src="/files/menu.png?raw=true">
</p>

### Focusing Windows
The above described features will be shown through this below windows.
<p align="center">
  <img src="/files/dashboard.png?raw=true">
</p>
<p align="center">
  <img src="/files/addNewContact.png?raw=true">
</p>
<p align="center">
  <img src="/files/contactsList.png?raw=true">
</p>
<p align="center">
  <img src="/files/update.png?raw=true">
</p>
<p align="center">
  <img src="/files/delete.png?raw=true">
</p>
<p align="center">
  <img src="/files/backupwindow.png?raw=true">
</p>


## Test It
Wanna test this application with your won. **Download** it form the given links (Choose it according to your platform).

> [Linux (64)](https://drive.google.com/open?id=1BG-veIM0ZmqdE036JvDR8-gFYM-f4bVI)

> [Mac](https://drive.google.com/open?id=169BlkDb3MZweakqP_IPFquFwbIYE1fYI)

> [Windows (64 and 32)](https://drive.google.com/open?id=1DxyjYufRhhQbvHL9CznPNjd2RTqum7m_)


## Getting Started
Want to contribute? Great! Here is the process

#### Prerequisites
 - [Install git](https://www.atlassian.com/git/tutorials/install-git)
 - Obviously you have jdk(8 or above) installed on your machine
 - An IDE (Mine was Eclipse)
 - Install JavaFX plugin on your IDE. Such as "e(fx)clipse" is the JavaFX plugin for Eclipse.
 - [Install](http://gluonhq.com/products/scene-builder) and setup Scene Builder (Search google how to add Scene Builder to your IDE. [Here](http://o7planning.org/en/10621/install-javafx-scene-builder-into-eclipse) is an example for Eclipse)

#### Installing
 - [Clone](https://help.github.com/articles/cloning-a-repository) the repository.
 - Add `sqlite-jdbc-3.23.1.jar` from Libraries folder as external jar file in the built path.
Ready to develop. Cool yeah!

#### Deployment
In the case of deploy the application, these two sites help me most
 - [code.makery](http://code.makery.ch/library/javafx-8-tutorial/part7/)
 - [Oracle](https://docs.oracle.com/javafx/2/deployment/self-contained-packaging.htm)


## Acknowledgments
First I would like to thanks the library makers, their enormous hard work let us opportunity to build the application what we want. Then obviously 'StackOverflow' and 'Google'.

## License
----

MIT (do whatever you want to do)

Made by [krHasan](https://www.facebook.com/Bappikhandoker)
