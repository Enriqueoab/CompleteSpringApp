package fileStore;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileStore {
    
    private final AmazonS3 s3;

    @Autowired
    public FileStore(AmazonS3 s3){
        this.s3=s3;

    }

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


}
