# BSB File Processing service
BSB File processing service is a micro service which will download the latest BSB file from Australian payment network and write those details to database.
Up on receiving a new file it validates that file has been already processed(check against the database file tracking table)
Process the file, write the data to database, trigger an event to API server to update the distributed cache, start the notification process through API service

### Technologies used
1. Springboot
2. JDK 11
3. Postgres


### Assumptions
Things are not dockerized
File downloading from ftp is not implemented yet.
Upon receiving a BSB full list file, not verifying against the existing record in database. Blindly replace the database with new file. This logic can be revisited if needed


