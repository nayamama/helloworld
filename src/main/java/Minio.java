import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;

import org.xmlpull.v1.XmlPullParserException;

import io.minio.MinioClient;
import io.minio.errors.MinioException;

public class Minio {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InvalidKeyException, XmlPullParserException {
        String accessKey = "8UB9W53TOPXAOS253M8W";
        String secretKey = "tOHmG2FMupohjnAPJuIfo7ZIxRsxHxo3ZHG2+Isi";

        try {
            // Create a minioClient with the MinIO Server name, Port, Access key and Secret key.
            MinioClient minioClient = new MinioClient("http://s3-storage.sqroot.local:9000", accessKey, secretKey);

            // Check if the bucket already exists.
            boolean isExist = minioClient.bucketExists("qi-artifactory");
            if(isExist) {
                System.out.println("Bucket already exists.");
            } else {
                // Make a new bucket called asiatrip to hold a zip file of photos.
                minioClient.makeBucket("qi-artifactory");
            }

            // Upload the zip file to the bucket with putObject
            minioClient.putObject("qi-artifactory","hello-world-1.0-SNAPSHOT.jar", "D:\\projects\\qi\\java\\helloworld\\target\\hello-world-1.0-SNAPSHOT.jar");
            System.out.println("file is successfully uploaded.");

        }catch(MinioException e){
            System.out.println("Error occurred: " + e);
        }
    }
}
