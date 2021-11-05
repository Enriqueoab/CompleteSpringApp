#  Complete application with Spring, Amazon S3, and React:

## 1. We have to get the dependency of AWS for out project

I used this version [`1.12.104`](https://mvnrepository.com/artifact/com.amazonaws/aws-java-sdk/1.12.104).

What we have to do is copy the dependency instruction and pasted in our file [`pom.xml`](demo/pom.xml) inside the dependencies tag, like so:

```
		<dependency>
			<groupId>com.amazonaws</groupId>
			<artifactId>aws-java-sdk</artifactId>
			<version>1.12.104</version>
		</dependency>

```
