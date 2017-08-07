steps:

1. Create screma and table using src/main/dbscripts/weather_ddl_01.sql 
2. Install jetty, maven 3.3.2 in eclipse. 
3. Import project in eclipse as existing maven project.
4. Edit src/main/resources/database/SqlMapConfig.xml and provice appropriate DB details.
5. Build then Debug/Run on server.


Features
1. Provides min and maximum temperature of the day.
2. It can provide array in sorted order for graphical view on UI.

current Limitations:
1. Exception handling is not done.
2. Error handling is not done.
3. UI is not there, plain REST get call is only supported
4. Currently city is hardcoded.
5. Code refactoring/ Core coverage is not done.
6. logging is pending.

 
