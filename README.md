# ChemoScheduler

How to start the ChemoScheduler application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/ChemoScheduler-1.0-SNAPSHOT.jar server -- src/main/resources/chemo-scheduler.yaml`
1. To check that your application is running enter url `http://localhost:8080`

Setting up the Database
---

ChemoScheduler uses as embedded H2 database for persistent storage.  In addition, it uses Liquibase for configuring and managing the database schema.  To configure your local H2 database instance with the latest schema:

From the root of the ChemoSchedule application, run

1.  `mvn package`
1.  `java -jar target/ChemoScheduler-1.0-SNAPSHOT.jar db migrate -- src/main/resources/chemo-scheduler.yaml`

Viewing the API specification
---

ChemoScheduler includes an API specification in Open API 3.0 format: [/doc/openapi.yaml](https://github.com/xfqiobyi/dfci_scheduler/blob/master/doc/openapi.yaml).  An easy way to view this in an OpenAPI editor is to used the Swagger Editor docker container.

First, install [Docker](https://www.docker.com/get-started).  Then:

```
> docker pull swaggerapi/swagger-editor
> docker run -d -p 80:8080 swaggerapi/swagger-editor
```

The point a web browser at `http://localhost/` to load the Open API editor.  File > Import File, and select /doc/openapi.yaml
