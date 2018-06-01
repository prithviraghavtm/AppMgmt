package com.infotech.app.controller;

import java.util.ArrayList;
import java.lang.*;
//Include the following imports to use queue APIs.
import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.queue.*;

import java.util.List;
public class ArticleService {
	public List<Article> getAllArticles(){
		List<Article> list = new ArrayList<Article>();
		list.add(new Article(1, "Java Concurrency", "Java"));
		list.add(new Article(2, "Hibernate HQL", "Hibernate"));
		list.add(new Article(3, "Spring MVC with Hibernate", "Spring"));
		return list;
	}
	
	public String pushMessage()
	{
		String connectionString = "DefaultEndpointsProtocol=https;AccountName=prithvitest;AccountKey=HyunZEAmKndjNm2qN7CNbgGabhG+U2kuRCdoQgROm/VlUVanXFEDeIba2gGua6nMJykPLIdVPoC+Owo6b8/DZA==;EndpointSuffix=core.windows.net";
		
		try
		{
		    // Retrieve storage account from connection-string.
		    CloudStorageAccount storageAccount =
		       CloudStorageAccount.parse(connectionString);

		    // Create the queue client.
		    CloudQueueClient queueClient = storageAccount.createCloudQueueClient();

		    // Retrieve a reference to a queue.
		    CloudQueue queue = queueClient.getQueueReference("prithvitestqueue");

		    // Create the queue if it doesn't already exist.
		    queue.createIfNotExists();

		    // Create a message and add it to the queue.
		    CloudQueueMessage message = new CloudQueueMessage("Hello, World");
		    queue.addMessage(message);
		    return "Success";
		}
		catch (Exception e)
		{
		    // Output the stack trace.
		    e.printStackTrace();
		    return "Failed" + e.getMessage();
		}		
	}
} 
