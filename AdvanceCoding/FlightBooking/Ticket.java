public class Ticket{
	private String name;
	private int age;
	private String flight;
	private int cls;
	private String seat;
	private int amt;

	Ticket(String name, int age, String flight, int cls, String seat, int amt){
		this.name = name;
		this.age = age;
		this.flight = flight;
		this.cls = cls;
		this.seat = seat;
		this.amt = amt;
	}

	public String getName(){
		return name;
	}
	public int getAge(){
		return age;
	}
	public String getSeat(){
		return seat;
	}
	public int getAmt(){
		return amt;
	}
	public String getFlight(){
		return flight;
	}
	public String getCls(){
		return cls==1?"Business":"Economy";
	}

	public String toString(){
		return flight+"_"+cls+"_"+seat;
	}
}