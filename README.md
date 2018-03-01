# Tech stack:
* Java (JDK 1.8)
* Spring MVC
* Hibernate
* H2 DB
* JUnit
* Spring Test
* Mockito
* Yahoo finance API for FX Rates 

# Deployment:
The war is deployed on Heroku. To access the page [click here](https://ccy-converter.herokuapp.com/)

# Development for future:
* Currencies are populated as static set. They need to be in sync with Yahoo Finance API. Need to find out a way to do the same.
* User Id need to be persisted in a session. At the moment, it is done via hidden field which is not a good practice.
* The above will lead to inconsistent behaviour of logout button.
* In memory H2 DB is not good enough. Whenever the component is restarted, users date will disappear. So, data should be persisted to disk.