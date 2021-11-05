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
