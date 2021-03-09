package jstl_test.el;

public class ELDTO {
	private String name;
	private String movie;
	public ELDTO() {}
	public ELDTO(String name, String movie) {
		super();
		this.name = name;
		this.movie = movie;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMovie() {
		return movie;
	}
	public void setMovie(String movie) {
		this.movie = movie;
	}
	@Override
	public String toString() {
		return "ELDTO [name=" + name + ", movie=" + movie + "]";
	}
	
}
