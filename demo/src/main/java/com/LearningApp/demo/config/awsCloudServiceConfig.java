package com.LearningApp.demo.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class awsCloudServiceConfig {

//Creation of the S3 client
@Bean //Instance of this clase to be injected in others classes (IoC container)
public AmazonS3 aznS3(){

    AWSCredentials cloudCredentials = new BasicAWSCredentials(
        "AKIAWCFEFT2GB7BUVHTE",
        "e7IU7jfrtyv7eNeGRc2yXH1hwC8kv6SKiZJn1YQW");

        return AmazonS3ClientBuilder
        .standard()
        .withRegion("EU_WEST_1")
        .withCredentials(new AWSStaticCredentialsProvider(cloudCredentials))
        .build();
    }

}
