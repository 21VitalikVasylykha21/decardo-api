package org.decardo.firebase;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU 
 * @since 2024/05/08
 */
@Slf4j
@Service
public class FirebaseStorageService {
	private static final String BUCKET_NAME = "nazaret-church-goods.appspot.com";
	private static final String FIREBASE_KEY = "firebase-private-key.json";
	private static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media";
	private static final String EMPTY_IMAGE_URL = "https://firebasestorage.googleapis.com/v0/b/nazaret-church-goods.appspot.com/o/281f8cda-a580-4fb5-aaec-15546eb699ea.png?alt=media";
	private Storage storage;

	public FirebaseStorageService() {
		try {
			InputStream inputStream = FirebaseStorageService.class.getClassLoader().getResourceAsStream(FIREBASE_KEY);
			if (inputStream == null) {
				log.error("Failed init firebase storage, not find firebase key");
				return;
			}
			Credentials credentials = GoogleCredentials.fromStream(inputStream);
			storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
		} catch (IOException e) {
			log.error("Failed init firebase storage", e);
		}
	}

	public String upload(MultipartFile multipartFile) {
		try {
			String fileName = multipartFile.getOriginalFilename();
			fileName = UUID.randomUUID().toString().concat(getExtension(fileName));
			File file = convertToFile(multipartFile, fileName);
			String url = uploadFile(file, fileName);
			file.delete();
			return url;
		} catch (Exception e) {
			log.error("Failed upload file", e);
			return EMPTY_IMAGE_URL;
		}
	}

	private String uploadFile(File file, String fileName) throws IOException {
		BlobId blobId = BlobId.of(BUCKET_NAME, fileName);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
		storage.create(blobInfo, Files.readAllBytes(file.toPath()));
		return String.format(DOWNLOAD_URL, BUCKET_NAME, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
	}

	private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
		File tempFile = new File(fileName);
		try (FileOutputStream fos = new FileOutputStream(tempFile)) {
			fos.write(multipartFile.getBytes());
		}
		return tempFile;
	}

	private String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}
}