# REVIEW

### Project structure:
When using Maven as the build tool, it's important to keep a project structure for the following reasons:

a. **Readability and maintainability:** A well-organized project structure makes the codebase more readable and maintainable. 

b. **Modularity:** Proper structuring encourages modularity, which allows creation of different packages depending on the purpose of the class, interface, enum, package etc.

c. **Ease to Test:** Separating source code from test code in a structured manner allows for more effective and organized testing. Creating a test package enables Maven recognise where to look for the test class and be able to run the unit test as required.

d. **Documentation:** A well-structure project serves as implicit documentation. It tells developers where to find specific components and how differnt parts of the project relate to each other.

e. etc

A simple structure for maven project is represented below:

src/main/java

    └── com

      └── ethnocopia
            ├── controller
            │   ├── CustomerController.java
            │   └── ...
            ├── service
            │   ├── CustomerService.java
            │   └── ...
            ├── repository
            │   ├── CustomerRepository.java
            │   └── ...
            ├── model
            │   ├── Customer.java
            │   └── ...
            ├── dto
            │   ├── CustomerDTO.java
            │   └── ...
            └── Application.java

            
src/test/java
  
    └── com
  
      └── ethnocopia
            ├── controller
            │   ├── CustomerControllerTest.java
            │   └── ...
            ├── service
            │   ├── CustomerServiceTest.java
            │   └── ...
            ├── repository
            │   ├── CustomerRepositoryTest.java
            │   └── ...
            ├── model
            │   ├── CustomerTest.java
            │   └── ...
            └── ApplicationTest.java






### Customer:

1) While it is good practice to limit as much depencies in a project as possible, it is to be noted that some avail with possibilities of limiting the amount of codes written, thereby making codes pleasant to the eye and easier to understand.

a. **@Getter and @Setter:** The use of Lombok depency would give us the ability to use the @Getter and @Setter annotations, thereby reducing the need to specify all the "getters" and "setters" methods for the field properties. 

b. **@ToString:** It also avails us with the @ToString annotation which presents us with the String representation of our Customer object.

c: **@AllArgsContructor and @NoArgsConstructor:** These annotations would remove the need to include the codes for "all arguments constructor" and "no argument" constructor.


2) When creating a blueprint for an object to be persisted (otherwise known as class), there is need to validate some parameters. A common one to validate is the "email", which should be marked as a unique field.
Some other fields like the "name" and/or "age" can be made mandatory depending the perculiarity of the use of the customer data.




### Customer Repository:

Although it isn't compulsory to add @Repository in the repository interface, it is a good practice to include it especially when you'd be defining your own queries. 

When a class is annotated with @Repository:

a. Spring will treat it as a repository and perform exception translation for database-specific exceptions into Spring's DataAccessException hierarchy. This makes it easier to handle and propagate database-related exceptions consistently throughout the application.

b. Spring component scanning will automatically detect and register the classes. This allows the repository to be automatically instantiated as a Spring bean, and it can be injected into other Sprng-mananged components, such as services.

c. It explicitly conveys the intent of the class as a repository for data access. This can make the code more self-documenting and help other developers quickly understand the purpose of the class/interface.



### Main Class:
#### Good implementations:
a. It's a good practice to use constructor injection over field injection. Alternative to reducing the amount of code when doing constructor injection would be using **@RequiredArgsConstructor** and using final keyword on the field parameter to which the object is to be injected.

b. It's good to use @RequestMapping in the controller to preceed all request coming relating to "customers"

c. It's also a good practice to use api versioning for the @RequestMapping. However, it's better to include the version in the application.yml or application.properties file. As per: **server.servlet.context-path=/api/v1**

#### Bad implementations:
a. The main class should be modularized and structured into the Service, Controller packages and class. This would also help in component scanning.

b. For clarity and debugging purposes, include endpoind for a particular operation to perform, not withstanding the type of method **(POST, PUT, PATCH, GET etc)** used in making the request. Example: 
@GetMapping("/get-customers"), @PostMapping("/add-customers").

c. There is need to compose a response message for each operation. Possibly including a response code that suggests the status of the operation conducted.
