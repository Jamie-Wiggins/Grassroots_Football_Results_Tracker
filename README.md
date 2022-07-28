## README

# Grassroots_Football_Results_Tracker
The following system allows grass roots teams to track their results in a centralised system. To ensure the correct result is achieved one admin from each team submits a result after a match and then the system will verify this f they match or raise a flag if action is required, for example the scores do not match.

# Tech Stack
* Java

## Class Diagram
<img width="604" alt="image" src="https://user-images.githubusercontent.com/58704773/181639327-f7eb3c99-54b6-403b-9ffb-12f27f17f712.png">

## Class Model Description
MainDatabase:
This database will store all the information used in the system including; accounts, 
results and messages. It will be altered as admins edit data and remove and add 
accounts. As well as managers who will submit results which will subsequently be 
added into the database. I have decided that the data base will organise the table 
and tournament tree within SQL and then the displays which use JFrame will request 
this data.

IDatabase:
This is an interface class for the database.

Server:
This is a class for the server of the database.
JFrame DisplayLeague/DisplayTournament/DisplayPrediction:
This is the display of the tables and tournament tree. It will request the desired data 
from the database and then display this in the JFrame which will be designed within 
the Eclipse software.

Account:
This is an abstract class which deals with the different types of users that will use the 
system. In this case that is admin and manager. There is no guest sub class because a 
guest does not require any forms of permissions to access certain parts of the 
system and would have no attributes or method. In other words, it would be an 
empty class, which in this case would be unnecessary.

Admin:
The admin has use case including; alter league and create manager, these have 
become methods in the body of the admin class. The methods will allow the class to 
add or delete certain aspects of the table which will alter the database, as well as 
add and create a new manager, which can also be added and stored in the database.

Manger:
The manger class deals with the submitting results and verifying results use cases. It 
does this by implementing methods in the body which allow a manager to input 
results which can then be verified, by comparing the result. This result will then be 
submitted into the database.

LogIn:
This class deals with how accounts access the system. It will require a username and 
password input. It also deals with the varication of the account to make sure it exists
on the database system. If there is any incorrect input in the log in details then the 
class will create a pop-up error message, informing the user that there is an issue.

Message:
This class deals with the details of messages which are used by the live chat feature. 
It records all the required attributes that are needed to be stored in the database as 
well as dealing with the input of the actual message by an account holder (admin or 
manager).

LiveChat:
The live chat class deals with the use of the messages from the database via the 
message class. It stores messages from the database as a list and will feedback sent 
and received results.

Predicition:
This class deals with the prediction algorithm for the display of the predicted results.
It will request a list of results and position of the teams involved in those results and 
use this to calculate the prediction. Predicting win, draw, and loss of each team’s
future games, up until the end of one season. This information will then be 
submitted back into the database, which deals with organising the table information
