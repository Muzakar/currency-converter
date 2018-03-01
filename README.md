# Tech stack:
* Java (JDK 1.8)
* Spring MVC
* Hibernate
* H2 DB
* JUnit
* Spring Test
* Mockito
* Yahoo finance API ( for FX Rates ) 
* Heroku ( for Cloud infrastructure )

# Deployment:
* The war is deployed on Heroku. To access the page <a href="https://ccy-converter.herokuapp.com/" target="_blank">click here</a>
[click here](https://ccy-converter.herokuapp.com/)
* Heroku's webapp runner is used as web container.
## Steps to Deploy:
* Login with your Heroku Id.
* Pull the code from this git branch.
* Push the code into Heroku. (Command: git push heroku master)
* Deploy the war. (Command: mvn clean heroku:deploy-war)
* Once deployed, the link to access the application will be specified in the console.
* To stop the process (Command: heroku ps:scale web=0)

# Development for future:
* Currencies are populated as static set. They need to be in sync with Yahoo Finance API. Need to find out a way to do the same.
* User Id need to be persisted in a session. At the moment, it is done via hidden field which is not a good practice.
* The above will lead to inconsistent behaviour of logout button.
* In memory H2 DB is not good enough. Whenever the component is restarted, users date will disappear. So, data should be persisted to disk.