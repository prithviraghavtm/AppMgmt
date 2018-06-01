package com.infotech.client;

import com.microsoft.azure.storage.table.*;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import com.infotech.model.RegionModel;
import com.microsoft.azure.storage.*;

public class TableAccessHelper<T extends TableServiceEntity>  {

	String tableName;
	public TableAccessHelper(String tableName)
	{
		this.tableName = tableName;
	}
	
	public void save(T obj) throws URISyntaxException, StorageException, InvalidKeyException
	{
	    // Retrieve storage account from connection-string.
	    CloudStorageAccount storageAccount =
	        CloudStorageAccount.parse("DefaultEndpointsProtocol=https;AccountName=agentmgmt;AccountKey=rGlILvEaW29jXSR8ppL1BxRiozq1/0Ac8mmpT8zsxxYfpKzIy6xzCCB4KA261A/4887jdhogpFTE0EenllzY9Q==;EndpointSuffix=core.windows.net");

	    // Create the table client.
	    CloudTableClient tableClient = storageAccount.createCloudTableClient();

	    // Create a cloud table object for the table.
	    CloudTable cloudTable = tableClient.getTableReference(this.tableName);

	    // Create an operation to add the new customer to the people table.
	    TableOperation insertreg1 = TableOperation.insertOrReplace(obj);

	    // Submit the operation to the table service.
	    cloudTable.execute(insertreg1);
	}
	
	public void get(T obj)
	{
        
		
	}
	
	public void delete(T obj)
	{
		
	}
	
	
}
