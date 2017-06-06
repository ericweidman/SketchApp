Open PSQL

Currently the DB is setup to refresh when you restart the application
so you won't have to drop/create every time changes are made to the schema.

Create database:
"CREATE DATABASE sketchapp;"

Connect to database:
"\c sketchapp"

View database tables:
"\dt"

View all values in a table:
"SELECT * FROM users;"
"SELECT * FROM drawings;"

Drop database:
Database cannot be dropped if you are currently connected to it.
Connect to any other database, one that should always exist is your username.
Or you can just create any other DB and connect to that.

"DROP DATABASE sketchapp;"
