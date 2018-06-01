package com.infotech.model;

//import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.table.*;

public class RegionModel extends TableServiceEntity
{
	public RegionModel(String BranchCode, String Region) 
	{
        this.partitionKey = BranchCode;
        this.rowKey = Region;
    }

    public RegionModel() { }

    String state;
    
    public String getstate() {
        return this.state;
    }

    public void setstate(String state) {
        this.state = state;
    }
}



