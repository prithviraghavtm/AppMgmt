package com.infotech.client;

import java.util.List;

import com.infotech.model.AgentModel;
import com.microsoft.azure.documentdb.ConnectionPolicy;
import com.microsoft.azure.documentdb.ConsistencyLevel;
import com.microsoft.azure.documentdb.Database;
import com.microsoft.azure.documentdb.Document;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.documentdb.DocumentClientException;
import com.microsoft.azure.documentdb.DocumentCollection;
import com.google.gson.Gson;

public class AgentDAO {

	 private static final String HOST = "https://agent-mgmt.documents.azure.com:443/";
	 private static final String MASTER_KEY = "HI45EyXQr4qtukdYQgaANTMh7vlCrekdEr7WLXwtAqiKLq4It19bg1Zv3BWzl9sj8CS6CdVzB54yWqovS8srfQ==";

	 private static DocumentClient documentClient = new DocumentClient(HOST, MASTER_KEY,
	                 ConnectionPolicy.GetDefault(), ConsistencyLevel.Session);

	 public static DocumentClient getDocumentClient() {
	     return documentClient;
	 }
	 
     // The name of our database.
     private static final String DATABASE_ID = "JFS-Database";

     // The name of our collection.
     private static final String COLLECTION_ID = "JFS-Collection";

     // Cache for the database object, so we don't have to query for it to
     // retrieve self links.
     private static Database databaseCache;

     // Cache for the collection object, so we don't have to query for it to
     // retrieve self links.
     private static DocumentCollection collectionCache;
	 
     private Database getTodoDatabase() {
         if (databaseCache == null) {
             // Get the database if it exists
             List<Database> databaseList = documentClient
                     .queryDatabases(
                             "SELECT * FROM root r WHERE r.id='" + DATABASE_ID
                                     + "'", null).getQueryIterable().toList();

             if (databaseList.size() > 0) {
                 // Cache the database object so we won't have to query for it
                 // later to retrieve the selfLink.
                 databaseCache = databaseList.get(0);
             } else {
                 // Create the database if it doesn't exist.
                 try {
                     Database databaseDefinition = new Database();
                     databaseDefinition.setId(DATABASE_ID);

                     databaseCache = documentClient.createDatabase(
                             databaseDefinition, null).getResource();
                 } catch (DocumentClientException e) {
                     // TODO: Something has gone terribly wrong - the app wasn't
                     // able to query or create the collection.
                     // Verify your connection, endpoint, and key.
                     e.printStackTrace();
                 }
             }
         }

         return databaseCache;
     }
     
     private DocumentCollection getTodoCollection() {
         if (collectionCache == null) {
             // Get the collection if it exists.
             List<DocumentCollection> collectionList = documentClient
                     .queryCollections(
                             getTodoDatabase().getSelfLink(),
                             "SELECT * FROM root r WHERE r.id='" + COLLECTION_ID
                                     + "'", null).getQueryIterable().toList();

             if (collectionList.size() > 0) {
                 // Cache the collection object so we won't have to query for it
                 // later to retrieve the selfLink.
                 collectionCache = collectionList.get(0);
             } else {
                 // Create the collection if it doesn't exist.
                 try {
                     DocumentCollection collectionDefinition = new DocumentCollection();
                     collectionDefinition.setId(COLLECTION_ID);

                     collectionCache = documentClient.createCollection(
                             getTodoDatabase().getSelfLink(),
                             collectionDefinition, null).getResource();
                 } catch (DocumentClientException e) {
                     // TODO: Something has gone terribly wrong - the app wasn't
                     // able to query or create the collection.
                     // Verify your connection, endpoint, and key.
                     e.printStackTrace();
                 }
             }
         }

         return collectionCache;
     }
     
     public AgentModel createTodoItem(AgentModel todoItem) {
         // Serialize the TodoItem as a JSON Document.
    	 Gson gson= new Gson();
         Document todoItemDocument = new Document(gson.toJson(todoItem));

         // Annotate the document as a TodoItem for retrieval (so that we can
         // store multiple entity types in the collection).
         todoItemDocument.set("entityType", "todoItem");

         try {
             // Persist the document using the DocumentClient.
             todoItemDocument = documentClient.createDocument(
                     getTodoCollection().getSelfLink(), todoItemDocument, null,
                     false).getResource();
         } catch (DocumentClientException e) {
             e.printStackTrace();
             return null;
         }

         return gson.fromJson(todoItemDocument.toString(), AgentModel.class);
     }
     
}
