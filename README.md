#  Complete application with Spring, Amazon S3, and React:

In this application I have learned how to create an aws S3 client, how to set and how work some of the Spring annotations so far

## 1. We have to get the dependency of AWS for our project

I used this version [`1.12.104`] we can get the dependency tag from [´here´](https://mvnrepository.com/artifact/com.amazonaws/aws-java-sdk/1.12.104).

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

Now we have to write the java code needed to create a intance of the amazon S3 client and be able to interact with buckets. We are going to create a class called [`awsCloudServiceConfig`](demo\src\main\java\com\LearningApp\demo\config\awsCloudServiceConfig.java) and build our S3 client with the code shown below:

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

To keep our code organized we are going to create a package (bucket) and inside it we are going to create a class called [`AwsBucketName`](demo\src\main\java\bucket\AwsBucketName.java) to store the name of our S3 bucket, that way if we change it in the future we would have to change just the value in this class:


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