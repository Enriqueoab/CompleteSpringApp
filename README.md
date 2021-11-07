#  Complete application with Spring, Amazon S3 and React:

In this application I have learned how to create an aws S3 client, how to set and how works some of the Spring annotations so far


## 1. We have to get the dependency of AWS for our project

I used this version [`1.12.104`] we can get the dependency tag from [`here`](https://mvnrepository.com/artifact/com.amazonaws/aws-java-sdk/1.12.104).

What we have to do is copy the dependency instruction and pasted in our file [`pom.xml`](demo/pom.xml) inside the dependencies tag, like so:

```ruby
	<dependency>
		<groupId>com.amazonaws</groupId>
		<artifactId>aws-java-sdk</artifactId>
		<version>1.12.104</version>
	</dependency>

```

## 2. AWS credentials, connect our server to amazon

We have to open our AWS console account and go to My account and click in My Security Credentials. In the section "Access keys" we have to create one and download the file.

![alt text](https://github.com/Enriqueoab/CompleteSpringApp/blob/main/img/create-aws-access-key.png.png)

Now we have to write the java code needed to create a intance of the amazon S3 client and be able to interact with buckets. We are going to create a class called [`awsCloudServiceConfig`](demo/src/main/java/com/LearningApp/demo/config/awsCloudServiceConfig.java) and build our S3 client with the code shown below:

```ruby
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

In our AWS account we have to choose the S3 service and create a bucket, make sure that the bucket's name is unique otherwise it won't work.
Once it's created we can see something like so:

![alt text](https://github.com/Enriqueoab/CompleteSpringApp/blob/main/img/CreationAwsBucket.png)

To keep our code organized we are going to create a package (bucket) and inside it we are going to create a class called [`AwsBucketName`](demo/src/main/java/bucket/AwsBucketName.java) to store the name of our S3 bucket, that way if we change it in the future we would have to change just the value in this class:


 ```ruby
		package bucket;

		public enum AwsBucketName {
			
			
			BUCKET_NAME("mycompleteapplicationawsbucket0511");
			private final String bucketName;

			private AwsBucketName(String bucketName) {
				this.bucketName = bucketName;
			}
			
			public String getBucketName() {
				return bucketName;
			}

		}
```

## 4. Implement the S3 bucket to save our data

First we are going to create a class called [`FileStore`](demo/src/main/java/fileStore/FileStore.java) inside of the fileStore package to keep the code tidy.
This class is going to use, thanks to Spring, throught injections the class [`awsCloudServiceConfig`](demo/src/main/java/com/LearningApp/demo/config/awsCloudServiceConfig.java) to have access to our S3 bucket and save any kind of data of our app.

This method allow us to saves files in Amazon S3:

 ```ruby
    //The object optionalMetadata will serve as the metadata that
    //we can pass to store images or files
    //<contentTime, contentLength>
    //inStream is what gets save in S3
    public void save(String path, String fileName,
    Optional<Map<String,String>> optionalMetadata,
    InputStream inStream)
    {

        ObjectMetadata metadata = new ObjectMetadata();
        optionalMetadata.ifPresent(map -> {
            if (!map.isEmpty()) {
                map.forEach((key, value) -> metadata.addUserMetadata(key, value));
            }
        });

        try {
            s3.putObject(path, fileName, inStream, metadata);
        } catch (AmazonServiceException ase) {
            throw new IllegalStateException("S3 store failed: ",ase);
        }


    }
```

## 5. Creating package and user class

First we are going to create the package profile and inside we have to create the class [`UserProfile`](demo/src/main/java/profile/UserProfile.java).

In this class we have to create three atributes userImageLink, username, userId and its setter and getters the class constructor and the hashcode equals override function. **Make sure that the getter of "userImageLink" handle properly a null value using Optional object, like so:

 ```ruby
	public Optional<String> getUserImageLink() {

		return Optional.ofNullable(userImageLink);

	}
```

## 6. Creating the api to the requests of our app

we are going to create a class called [TestUserProfile](demo/src/main/java/datastore/TestUserProfie.java) and set a function to create a couple of user profiles.

**@Repository** is a annotation used to indicate that the class provides the mechanism for storage, retrieval, search, update and delete operation on objects.

### 6.2 

Now we are going to create a class, in the "profile" package, as the controller called [UserController](demo/src/main/java/profile/UserController.java)

**@RestController**, this annotation is applied to a class to mark it as a request handler. Spring RestController annotation is used to create RESTful web services using Spring MVC.

**@RequestMapping("api/v1/user-profile")**, we use the @RequestMapping annotation to map URLs such as /appointments onto an entire class or a particular handler method.

**@GetMapping**, maps HTTP GET requests onto specific handler methods.

### 6.3

[UserService](demo/src/main/java/profile/UserService.java)

**@Service**, marks a Java class that performs some service, such as executing business logic, performing calculations, and calling external APIs.

### 6.4

[UserDataAccessService](demo/src/main/java/profile/UserDataAccessService.java)

**@Autowired**, can be used to autowire bean on the setter method just like @Required annotation, constructor, a property or methods with arbitrary names and/or multiple arguments.

## 7. Starting the frontend (React)

The client will be written in react and we will get and post request to our API.
We can bootstrap our react frontend with the comands below:

 ```ruby
		npx create-react-app name
		cd my-app
		npm start
```
With these commands we are going to run the server and automatically is going to open a localhost window showing us what we get using the bootstrap react.
Now we have to change it in order to get the elements that we need for out app