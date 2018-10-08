package model.vo;

public class FestivalVO {
	String festivalName;
	String festivalLocation;
	String location;
	String city;
	String startDate;
	String endDate;
	String agency;
	String img;
	
	public FestivalVO(String festivalName, String festivalLocation, String location, 
			String city, String startDate,
			String endDate, String agency) {
		super();
		this.festivalName = festivalName;
		this.festivalLocation = festivalLocation;
		this.location = location;
		this.city = city;
		this.startDate = startDate;
		this.endDate = endDate;
		this.agency = agency;
	}
	public FestivalVO(String festivalName, String festivalLocation, String location, 
			String city, String startDate,
			String endDate, String agency,String img) {
		super();
		this.festivalName = festivalName;
		this.festivalLocation = festivalLocation;
		this.location = location;
		this.city = city;
		this.startDate = startDate;
		this.endDate = endDate;
		this.agency = agency;
		this.img = img;
	}
	public String getFestivalName() {
		return festivalName;
	}
	public void setFestivalName(String festivalName) {
		this.festivalName = festivalName;
	}
	public String getFestivalLocation() {
		return festivalLocation;
	}
	public void setFestivalLocation(String festivalLocation) {
		this.festivalLocation = festivalLocation;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getAgency() {
		return agency;
	}
	public void setAgency(String agency) {
		this.agency = agency;
	}
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "FestivalVO [festivalName=" + festivalName + ", festivalLocation=" + festivalLocation + ", location="
				+ location + ", city=" + city + ", startDate=" + startDate + ", endDate=" + endDate + ", agency="
				+ agency + "]";
	}
	
	
}
