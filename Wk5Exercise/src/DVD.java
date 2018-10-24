
public class DVD {
	
	private int ID;
	private String title;
	private String genre;
	private int year;
	
	public DVD() {
		
		this.ID = ID;
		this.title = title;
		this.genre = genre;
		this.year = year;
		
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public String toString() {
		
		return"ID: " + this.getID() 
		+ "\n Title: "		+ this.getTitle() 
		+ "\n Genre: " + this.getGenre()
		+ "\n Year: " + this.getYear();
	}

}
