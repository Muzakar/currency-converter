# Tech stack:
* Spring MVC
* Hibernate
* H2 DB
* JUnit
* Spring Test
* Yahoo finance API for FX Rates 

# Deployment:
The war is deployed on Heroku. To access the page [click here](https://ccy-converter.herokuapp.com/)

# Development for future:
* Persisting user id in all the jsp. At the moment, it is done via hidden field which is not a good practice.
* In memory H2 DB is not good enough. Whenever the component is restarted, users date will disappear. So, data should be persisted to disk.