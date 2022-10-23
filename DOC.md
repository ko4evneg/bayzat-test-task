#Cryptotracker app
###Run instructions
1. Run init-db.sql script located in src/main/resources directory <br>
   f.e. `psql -U postgres -f C:\Java\bayzat\src\main\resources\database\init-db.sql`
2. Run CryptoTrackerApplication class in IDE

###Notes
- Schema and initial data recreated each application run. `spring.sql.init` section of appliaction.properties file should be edited to avoid it, if required.
- Postman collections (version 2) placed in root of the project in postman_collections directory