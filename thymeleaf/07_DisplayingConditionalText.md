# Displaying Conditional Text with Thymeleaf

In this exercise, you will see how to use thymeleaf to display text in your template based on a condition.

## What you should do

### 1. Create a new spring boot application and a class called HomeController.java, that looks like this:
```java
@Controller
public class HomeContrller
{
    @RequestMapping("/hasvalue")
    public String hasValue(Model model){
        model.addAttribute("isAdmin", true);
        model.addAttribute("username", "ajhenley");
        return "conditional";
    }

    @RequestMapping("/hasnovalue")
    public String hasNoValue(){
        return "conditional";
    }
}
```

### 2. Create a template by right-clicking the template folder and name it links.html. It should look like this:
```html
<!DOCTYPE html>
<html xmlns:th="www.thymeleaf.org">
    <head>
        <title>Using Thymeleaf</title>
    </head>
    <body>
        <p th:inline="text">Hello, [[${fname}]] [[${lname}]]</p>
        <p th:text="'The name of the user is ' + ${fname} + ' ' + ${lname}"></p>
        <p th:text="|Welcome to our application, ${fname} ${lname}!|"></p>
    </body>
</html>
```

If it is done properly, when you run your application, you will be able to navigate to localhost:8080/thymeleaf and see this:

This is because the template will render into this html:

```html
<!DOCTYPE html>
<html>
  <head>
    <title>Using Thymeleaf</title>
  </head>
  <body>
    <p>Hello, AJ Henley</p>
    <p>The name of the user is AJ Henley</p>
    <p>Welcome to our application, AJ Henley!</p>
  </body>
</html>
```

