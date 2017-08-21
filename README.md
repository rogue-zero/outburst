# Outburst

More than just a Spark. Library with some useful tools to improve web workflow using: 

 * [Spark](http://sparkjava.com/)
 * [Guava](https://github.com/google/guava)
 * [Sql2o](http://www.sql2o.org/)
 * [SL4J](https://www.slf4j.org/)
 * [gson](https://github.com/google/gson)

## TODO

-[ ] email, exception, sqlambda, Ambiente, GsonTransformer, OracleBoolean and Version are not documented and tested.
They should be.

-[ ] data are specific to TST. They should be parametrized. Maybe via something like `Environment.init(param1, param2, ...)`.

-[ ] document the need to add to pom to `Version` to work. See if `test/resources` are really necessary.
```xml
<build>
    <resources>
        <resource>
            <directory>src/main/resources</directory>
            <filtering>true</filtering>
        </resource>
    </resources>
    <testResources>
        <testResource>
            <directory>src/test/resources</directory>
            <filtering>true</filtering>
        </testResource>
    </testResources>
</build>
```

## Documentation

### PublicCors

Use this to make your API public with no restrictions to whoam access your API. 
**Beware this can make your API unsecure**.
 
#### Usage

```java
public class CorsExample {
    public void applyCors() {
        Cors = new Cors();
        before((req, res) -> cors.applyCors(res));
    }  
}
```

### ErrorMessage

Use this as return to your API on any error

#### Usage

```java
public class ErrorMessageExample {
    public void threatExceptions() {
        exception(Exception.class, (e, req, res) -> {
            LOGGER.error("Uknown error: ", e);
        
            int code = HttpStatus.INTERNAL_SERVER_ERROR_500;
            res.status(code);
        
            ErrorMessage error = new ErrorMessage(code, "Internal Server Error");
            res.type(JSON_UTF_8.toString());
            res.body(factory.gson().toJson(error, ErrorMessage.class));
        });    
    }
}
```