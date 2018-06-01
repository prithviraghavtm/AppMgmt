package com.infotech.client;
import com.microsoft.azure.storage.table.*;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

import org.springframework.beans.factory.annotation.Autowired;

import com.infotech.model.RegionModel;
import com.microsoft.azure.storage.*;


public class RegionDAO 
{

	@Autowired
	private TableAccessHelper<RegionModel> accessHelper;
	
	public RegionDAO(TableAccessHelper<RegionModel> accessHelper)
	{
		this.accessHelper = accessHelper;
	}
	
	public void SaveRegion(RegionModel reg1) throws InvalidKeyException, URISyntaxException, StorageException
	{
		this.accessHelper.save(reg1);	
	}
	
}
