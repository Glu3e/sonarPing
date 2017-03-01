
/*
 * Copyright (c) 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;

//import DriveUpload;
//import FileUploadListener;
//import View;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

public class DriveUpload {
	//specify the name of the app and where the media to upload will be
	private static final String APPLICATION_NAME = "sonarPing";
	private static final String UPLOAD_PATH ="/Desktop/720p.mov";
	private static final java.io.File UPLOAD_FILE = new java.io.File(UPLOAD_PATH);
	
	  /** Directory to store user credentials. */
	  private static final java.io.File DATA_STORE_DIR =
	      new java.io.File(System.getProperty("user.home"), ".store/drive_sample");
	
	  private static FileDataStoreFactory dataStoreFactory;
	  private static HttpTransport httpTransport;
	  private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	  private static Drive drive;
	  
	  private static Credential authorize() throws Exception {
		    // load client secrets | the secret is from the google console you get it when you register your app
		    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
		        new InputStreamReader(DriveUpload.class.getResourceAsStream("/client-secret.json")));
		    if (clientSecrets.getDetails().getClientId().startsWith("Enter")
		        || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
		      System.out.println(
		          "Enter Client ID and Secret from https://code.google.com/apis/console/?api=drive "
		              + "into sonarPing/src/main/resources/client-secret.json");//in case its not in the right folder or you named diffrent
		      System.exit(1);
		    }
		    // set up authorization code flow
		    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport,
		        JSON_FACTORY, clientSecrets, Collections.singleton(DriveScopes.DRIVE_FILE))
		            .setDataStoreFactory(dataStoreFactory).build();
		    // authorize
		    return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
		  }

		  public static void main(String[] args) {
		    Preconditions.checkArgument(
		        !UPLOAD_PATH.startsWith("Enter "),
		        "Please enter the upload file path and download directory in %s", DriveUpload.class);

		    try {
		      httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		      dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);
		      // authorization
		      Credential credential = authorize();
		      // set up the global Drive instance
		      drive = new Drive.Builder(httpTransport, JSON_FACTORY, credential)
		          .setApplicationName(APPLICATION_NAME).build();

		      // run commands

		      View.header1("Starting Resumable Media Upload");
		      File uploadedFile = uploadFile(false);

		      View.header1("Success!");
		      return;
		    } catch (IOException e) {
		      System.err.println(e.getMessage());
		    } catch (Throwable t) {
		      t.printStackTrace();
		    }
		    System.exit(1);
		  }

		  /** Uploads a file using either resumable or direct media upload. */
		  //resumable media uploads are for bigger files like videos so it gets uploaded by chunks 
		  private static File uploadFile(boolean useDirectUpload) throws IOException {
		    File fileMetadata = new File();
		    fileMetadata.setTitle(UPLOAD_FILE.getName());

		    FileContent mediaContent = new FileContent("image/jpeg", UPLOAD_FILE);

		    Drive.Files.Insert insert = drive.files().insert(fileMetadata, mediaContent);
		    MediaHttpUploader uploader = insert.getMediaHttpUploader();
		    uploader.setProgressListener(new FileUploadListener());
		    return insert.execute();
		  }


}
