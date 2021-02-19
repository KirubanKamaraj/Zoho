import java.util.List;
import java.util.ArrayList;

public class Flight{
	private String flightName;
	private int rowE;
	private int rowB;
	private int colE;
	private int colB;
	private int s, m, e;
	private int bs, bm, be;
	private List<String> booked = new ArrayList<String>();

	Flight(String flightName, int rowB, int rowE, int bs, int bm, int be, int s, int m, int e){
		this.flightName = flightName;
		this.rowB = rowB;
		this.rowE = rowE;
		this.colE = s+m+e;
		this.colB = bs+bm+be;
		this.s = s;
		this.m = m;
		this.e = e;
		this.bs = bs;
		this.bm = bm;
		this.be = be;
	}

	public boolean taken(int type, String seat){
		return booked.contains(type+"_"+seat);
	}

	public void book(int type, String seat){
		booked.add(type+"_"+seat);	
	}

	public int getSeatAmount(int type, String seat){
		int amt = 0;
		if(type == 1)
			amt += 2000;
		else amt += 1000;
		String [] se = seat.split("_");
		int c = se[1].charAt(0)-64;

		if(type == 1){
			if(c == 1 || c == bs || c == bs+1 || c == bs+bm || c == bs+bm+1 || c == colB) amt+=100;
		}
		else{
			if(c == 1 || c == s || c == s+1 || c == s+m || c == s+m+1 || c == colE) amt+=100;
		} 
		return amt;
	}

	public void displyAvailableSeats(){
		System.out.println("Business class");
		for (int i=1; i<=rowB; i++) {
			for (int j=1; j<=colB; j++) {
				String s = Integer.toString(i)+"_"+(char)(j+64);
				if(!taken(1, s))
					System.out.print(s+" ");
			}
			System.out.println();
		}
		System.out.println("Economy class");
		for (int i=1; i<=rowE; i++) {
			for (int j=1; j<=colE; j++) {
				String s = Integer.toString(i)+"_"+(char)(j+64);
				if(!taken(2, s))
					System.out.print(s+" ");
			}
			System.out.println();
		}		
	}

	public String toString(){
		return flightName;
	}

	public void cancelTicket(String seat){
		System.out.println(booked);
		System.out.println(seat);
		booked.remove(seat);
		System.out.println(booked);
	}

}