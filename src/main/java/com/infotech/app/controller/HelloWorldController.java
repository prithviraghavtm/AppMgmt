package com.infotech.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.infotech.model.*;
import com.infotech.client.*;

@RestController
public class HelloWorldController {

	@Autowired
	private ArticleService articleService;
		
	@RequestMapping(value="/hello")
	public String hello(){
		//return "Hello World!!";
		//List<Article> list = articleService.getAllArticles();
		String result = articleService.pushMessage();
		return result;	
	}
	
	@RequestMapping(value="/hey")
	public String hey(){
		
	//	try
	//	{
	//		RegionModel reg1 = new RegionModel("001", "jakkuru");
	//		RegionDAO red1 = new RegionDAO();
	//		red1.SaveRegion(reg1);
		
			return "hey wass up!!";
	//	}
		
	//	catch(Exception e)
		//{
		//	return "failure" + e.getMessage();
		//}
	}
	
	@RequestMapping(value="/addRegion")
	@ResponseBody
	public String AddRegion(@RequestParam("code") String code, @RequestParam("region") String region)
	{
	//	try
		//{
			//RegionModel reg1 = new RegionModel(code, region);
			//RegionDAO red1 = new RegionDAO();
			//red1.SaveRegion(reg1);
		
			return "hey wass up!!";
		//}
		
		//catch(Exception e)
		//{
			//return "failure" + e.getMessage();
		//}
	}	
	
	@PostMapping(value="/addRegion1")
	public String AddRegion1(@RequestBody  Region region)
	{
		try
		{
			RegionModel reg1 = new RegionModel(region.getCode(), region.getRegion());
			TableAccessHelper<RegionModel> regionAccessHelper = new TableAccessHelper<RegionModel> ("BankCodeToRegion", RegionModel.class);
			RegionDAO dao = new RegionDAO(regionAccessHelper);
			dao.SaveRegion(reg1);
		
			return "hey wass up!!";
		}
		
		catch(Exception e)
		{
			return "failure" + e.getMessage();
		}
	}
	
	@PostMapping(value="/deleteRegion1")
	public String deleteRegion(@RequestBody  Region region)
	{
		try
		{
			RegionModel reg1 = new RegionModel(region.getCode(), region.getRegion());
			TableAccessHelper<RegionModel> regionAccessHelper = new TableAccessHelper<RegionModel> ("BankCodeToRegion", RegionModel.class);
			RegionDAO dao = new RegionDAO(regionAccessHelper);
			dao.deleteRegion(region.getCode(), region.getRegion());
		
			return "hey wass up!!";
		}
		
		catch(Exception e)
		{
			return "failure" + e.getMessage();
		}
	}
	
	@PostMapping(value="/addAgent")
	public String addAgent(@RequestBody  Agent agent)
	{
		try
		{
            AgentModel agentm = new AgentModel();
            agentm.AgentId = agent.AgentId;
            agentm.partitionKey = agent.AgentId;
            agentm.AgentName = agent.AgentName;
            agentm.docId = "1";
            
            AgentDAO dao = new AgentDAO();
            dao.createTodoItem(agentm);
		
			return "hey wass up!!";
		}
		
		catch(Exception e)
		{
			return "failure" + e.getMessage();
		}
	}
}

class Agent
{
	public String AgentName;
	
	public String AgentId;
	
}

// Class for Region Entity.
class Region
{
	private String code;
	private String region;
	
	public String getCode()
	{
		return this.code;
	}
	
	public void setCode(String code)
	{
		this.code = code;
	}
	
	public String getRegion()
	{
		return this.region;
	}
	
	public void setRegion(String region)
	{
		this.region = region;
	}
}

