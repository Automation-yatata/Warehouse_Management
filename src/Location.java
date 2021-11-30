package backend;

class Location{
	
	public String Country;
	public String City;
	private String ZIP;

	public Location(String country, String city, String zip){

		this.Country = country;
		this.City = city;
		//verificar estrutura do código ZIP
		this.ZIP = zip;
	}

	public String getCountry(){
		
		return this.Country;
	}
	public String getCity(){
		
		return this.City;
	}
	public String getZIP(){
		
		return this.ZIP;
	}

	public void setCountry(String newCountry){
		
		this.Country = newCountry;
	}
	public void setCity(String newCity){
		
		this.City = newCity;
	}
	public int setZIP(String newZIP){
		
		//verificar estrutura do código ZIP
		//verificar se user é Admin
		/*
			return -1 caso não seja
		*/
		this.ZIP = newZIP;
		return 0;
	}

}