# Run Instructions

If you use Maven, you can run the application by using:

```bash
./mvnw spring-boot:run
```

Alternatively, you can build the JAR file with:

```bash
./mvnw clean package
```

And then run the JAR file:

```bash
java -jar target/rest_service-0.0.1-SNAPSHOT.jar
```

## Viewing the greeting

Once the application is running, you can view the greeting by navigating to:

```
http://localhost:8080/greeting
```
You should see a JSON response similar to:

```json
{
  "id": 1,
  "content": "Hello, World!"
}
```

You can also customize the greeting by adding a `name` parameter:

```
http://localhost:8080/greeting?name=Justin
```
This will return a response like:

```json
{
  "id": 2,
  "content": "Hello, Justin!"
}
```