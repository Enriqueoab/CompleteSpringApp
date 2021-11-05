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
## 2. AWS credentials, connect our server to amazon

We have to open our AWS console account and go to My account and click in My Security Credentials. In the section "Access keys" we have to create one and download the file.

Now we have to write the java code needed to create a intance of the amazon S3 client and be able to interact with buckets. We are going to create a class called [`awsCloudServiceConfig`](demo\src\main\java\com\LearningApp\demo\config\awsCloudServiceConfig.java) and build our S3 client with the code shown below:

```
	//Creation of the S3 client
	@Bean //Instance of this clase to be injected in others classes (IoC container)

	public AmazonS3 aznS3(){

		AWSCredentials cloudCredentials = new BasicAWSCredentials(
			"AKIAWCFEFT2GB7BUVHTE",
			"e7IU7jfrtyv7eNeGRc2yXH1hwC8kv6SKiZJn1YQW");

			return AmazonS3ClientBuilder
			.standard()
			.withCredentials(new AWSStaticCredentialsProvider(cloudCredentials))
			.build();
    }
```
## 3. Creating the bucket to store our app data