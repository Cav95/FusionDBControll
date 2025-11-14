# DescriptionDatabase 
Application to manage some feuture into fusion database from extern query.

We can manage:
- Driven Description with its traduction from italian to english.
- Hystory of change in print value.  

## To start application 
In same fondel with exeguible need to exist configuration file "configDBConnection.ini".
Inside it input parametre of connection string like immage below.

![Alt text](.\readme_resources\IniRules.png "Connection")

If you don't need user password can write all in one-line like:

*key1=jdbc:mysql://localhost:3306/DesFusion?user=root&password=*
Key1 need Edm_2008 connection string.
Key2 need BombConfine connection string.