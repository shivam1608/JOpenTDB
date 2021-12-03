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
