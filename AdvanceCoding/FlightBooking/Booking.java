import java.util.List;
import java.util.ArrayList;

public class Booking{
	private boolean meals;
	private int totalAmount;
	private List<Ticket> tickets = new ArrayList<>();

	Booking(){}

	public void book(Ticket t, boolean meals){
		this.meals = meals;
		totalAmount += t.getAmt();
		tickets.add(t);
	}

	public int cancel(){
		totalAmount = 0;
		int amt = tickets.size()*200;
		tickets.clear();
		return amt;
	}

	public void displayMealSeats(){
		if(!meals) return;
		for(Ticket i: tickets){
			System.out.println("Flight : "+i.getFlight()+" Seat : "+i.getSeat());
		}
	}

	public List<Ticket> getTickets(){
		return tickets;
	}

	public void displaySummary(){
		System.out.println("======================Summary==========================");
		System.out.println("\t\tTotal Price for this Booking is "+totalAmount);
		for (Ticket t: tickets) {
			System.out.println("Name : "+t.getName());
			System.out.println("Age : "+t.getAge());
			System.out.println("Flight : "+t.getFlight());
			System.out.println("Class : "+t.getCls()+" Class");
			System.out.println("Seat : "+t.getSeat());
			System.out.println("Meals : "+ (meals?"Yes":"No"));
			System.out.println("Ticket Price : "+t.getAmt());
			System.out.println("================================================");
		}
	}
}