# JOpenTDB
JOpenTDB is Lightweight, Fast , Easy To Use Sync and Async Wrapper for https://opentdb.com (an open source trivia api) 
### Version : 0.1.0

## Add to your projects
Easy to add in your projects using gradle, maven or jar

### Gradle
- Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
- Add the dependency (replace version with the version on top)
```gradle
dependencies {
	        implementation 'com.github.shivam1608:JOpenTDB:0.1.0'
	}
```

### Maven
- Add the repository in pom.xml file
```maven
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

```
- Add the dependency (replace version with the version on top)
```maven 

	<dependency>
	    <groupId>com.github.shivam1608</groupId>
	    <artifactId>JOpenTDB</artifactId>
	    <version>0.1.0</version>
	</dependency>


```

### Jar 
Download the jar from this repo 
```
out/artifact/JOpenTDB.jar or Use the Release Section
```

# Quick Start
- Get 10 Questions for Trivia (Synchronous)
```java
import me.shivzee.OpenTDB;
import me.shivzee.util.Question;
import me.shivzee.exceptions.TokenNotFoundException;

class TriviaExample {
public static void main(String [] args){
        try{
            OpenTDB openTDB = new OpenTDB();
            for (Question question : openTDB.getTrivia()){
                System.out.println("Question "+question.getQuestion());
                System.out.println("Answer "+question.getAnswer());
            }
        }catch (TokenNotFoundException exception){
            // Can Ignore Until using Session Tokens
        }
  }
 }

```
- Get 10 Questions for Trivia (Asynchronous)
```java
import me.shivzee.OpenTDB;
import me.shivzee.util.Question;

class TriviaExample {
public static void main(String [] args){
            OpenTDB openTDB = new OpenTDB();
            openTDB.getTriviaAsync((questions)->{
                for (Question question : questions){
                    System.out.println("Question "+question.getQuestion());
                    System.out.println("Answer "+question.getAnswer());
                }
            });
        
    }
}

```

## Creating an Instance 
- Without Session Token
```java 
import me.shivzee.OpenTDB;

OpenTDB trivia = new OpenTDB();
```
- With Session Token
```java 
import me.shivzee.OpenTDB;

OpenTDB trivia = new OpenTDB(Session.create());
```

## Some Comman Methods/Functions of Question Class
- the Question class
```java
String triviaQuestion = question.getQuestion();  // Get The Question
String answer = question.getAnswer(); // Get The Answer To That Question
List<String> options = question.getOptions(); // Other Incorrect Answers
Type type = question.getType(); // Type of the Question Multiple/Boolean
Difficulty difficulty = question.getDifficulty(); // Difficulty 
Category category = question.getCategory(); // The Category of The Question
```

# Using Options
- The getTrivia() is an overloaded function
```java
Category category = Category.RANDOM;
Difficulty difficulty = Difficulty.HARD;
Encoding encoding = Encoding.DEFAULT;
Type type = Type.MULTIPLE;

List<Question> questions = trivia.getTrivia(10 , category , type , encoding ,difficulty);
trivia.getTriviaAsync(10 , category , type, encoding , difficulty , (triviaQuestions)->{});
```

