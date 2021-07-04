package ar.com.eventsocial.backend.repository_;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;

import javax.xml.bind.DatatypeConverter;

import org.springframework.web.multipart.MultipartFile;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;



public class StorageRepository {

	private Storage storage;

	public void Connection() throws IOException {
		
		FileInputStream serviceAccount;
		try {
			
		    String bucketName = "gs://eventuy-befe5.appspot.com";
		    String projectId = "eventuy";
			
			serviceAccount = new FileInputStream("./ServiceAccountKey.json");

			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setProjectId(projectId)
					.build();

			FirebaseApp.initializeApp(options);
			
			
			String base64String ="";
			
			String[] strings = base64String.split(",");
			 
			byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
	            
	        String path = "C:\\Users\\escuj\\OneDrive\\Escritorio\\test_image" + ".jpeg";
	       
	        File file = new File(path);
	        
	        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
	            outputStream.write(data);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
			BlobId blobId = BlobId.of(bucketName, "FileTest");
	        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
	        Blob blob = storage.create(blobInfo, Files.readAllBytes(file.toPath()));
			
			
			System.out.println("lala");
			

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String subirImagen(final File file, final String fileName) throws IOException {
      
		FileInputStream serviceAccount;
		
		serviceAccount = new FileInputStream("./ServiceAccountKey.json");
		
		BlobId blobId = BlobId.of("bucket name", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
       
        Credentials credentials = GoogleCredentials.fromStream(serviceAccount);
      
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
      
        return String.format("gs://eventuy-befe5.appspot.com", URLEncoder.encode(fileName));
    }
	

    public File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        
    	File tempFile = new File(fileName);
       
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }

	
}
