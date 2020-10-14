package googleAPI;

public class resources {
	
	public static String getresources()
	{
		String  get = "/maps/api/place/nearbysearch/json";
		return get;
	}
	
	
	public static String postresource()
	{
		String post = "/maps/api/place/add/json";
		return post;
	}

	
	public static String deleteresource()
	{
		String delete ="/maps/api/place/delete/json";
		return delete;
	}
	
	
	public static String postresourcexml()
	{
		String postxml = "/maps/api/place/add/xml";
		return postxml;
	}

}
