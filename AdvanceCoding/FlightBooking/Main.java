import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FilenameFilter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;

public class Main{
	static Map<String, Flight> flights = new HashMap<>();
	static List<String> fls = new ArrayList<>();
	static Map<Integer, Booking> bookings = new HashMap<>();
	static int uniq = 1;
	static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		initialFlights();
		while(true){
			System.out.println("Select the Option: ");
			System.out.println("1. Booking");
			System.out.println("2. Booking Cancel");
			System.out.println("3. All Available seats");
			System.out.println("4. Display Seat numbers for which meal is ordered");
			System.out.println("5. Display summary for Bookings");
			System.out.println("6. Exit");
			int n = sc.nextInt();

			switch(n){
				case 1:
					seatBooking();
					break;
				case 2:
					cancelSeat();
					break;
				case 3:
					System.out.println("Available Seats");
					for (Map.Entry<String,Flight> i: flights.entrySet()) {
						i.getValue().displyAvailableSeats();
						System.out.println("=============================================================");
					}
					break;
				case 4:
					for (Map.Entry<Integer, Booking> boo: bookings.entrySet()) {
						boo.getValue().displayMealSeats();
					}
					System.out.println("=============================================================");
					break;
				case 5:
					displayBookingIds();
					int bookid = sc.nextInt();
					bookings.get(bookid).displaySummary();
					break;
				case 6:
					sc.close();
					return;
				default:
					System.out.println("Please select the correct Option");
					break;
			}
		}
	}

	public static void displayFlights(){
		System.out.println("Available Flights");
		int c = 1;
		for (Map.Entry<String, Flight> i: flights.entrySet()) {
			System.out.println((c++)+". "+i.getKey());
		}		
	}

	public static void displayBookingIds(){
		for (Map.Entry<Integer, Booking> i: bookings.entrySet()) {
			System.out.println("ID : "+i.getKey());
		}		
	}

	public static void initialFlights(){
		File file = new File(".");
		FilenameFilter filter = new FilenameFilter() {
	        @Override
	        public boolean accept(File f, String name) {
	            return name.endsWith(".txt");
	        }
		};

		String [] pathnames = file.list(filter);
		for (String k: pathnames) {
//			System.out.println(k);
			try{			
				FileReader fr = new FileReader(k);
//				BufferedReader br = new BufferedReader(fr);
				int chr;
				List<Integer> dls = new ArrayList<>();
				int prev = 0;
				while((chr=fr.read()) != -1){
					if(chr >= 48 && chr <= 57){
						prev *= 10;
						prev += Integer.parseInt(Character.toString((char)chr));
					}
					else {
						if(prev != 0)dls.add(prev);
						prev = 0;
					}
				}
//				System.out.println(dls);
				Flight f = new Flight(k.substring(0, k.length()-4), dls.get(3), dls.get(7), dls.get(0), dls.get(1), dls.get(2), dls.get(4), dls.get(5), dls.get(6));
				flights.put(k.substring(0, k.length()-4), f);
				fls.add(k.substring(0, k.length()-4));
			}		
			catch(Exception e){
				System.out.println(e);
			}
		}
	}

	public static void seatBooking(){
		Booking b = new Booking();
		System.out.println("Enter Number of seats you want to book");
		int num = sc.nextInt();
		for (int i=1; i<=num; i++) {						
			displayFlights();
			System.out.println("Select Flight : ");
			int fl = sc.nextInt();
			System.out.println("Select Class : ");
			System.out.println("1. Business Class\n2. Economy Class");
			int t = sc.nextInt();
			System.out.println("Enter Seat Number : ");
			String seat = sc.next();
			Flight f = flights.get(fls.get(fl-1));
			while(f.taken(t, seat)){
				System.out.println("Seat is not Available");
				System.out.println("Enter another Seat Number : ");
				seat = sc.next();
			}
			System.out.println("Enter the Name : ");
			String name = sc.next();
			System.out.println("Enter the Age : ");
			int age = sc.nextInt();
			System.out.println("Do you order meals?\n1. Yes\n2. No");
			boolean meals = (sc.nextInt()==1?true:false); 
			Ticket tic = new Ticket(name, age, f.toString(), t, seat, f.getSeatAmount(t, seat));
			b.book(tic, meals);
			f.book(t, seat);
		}
		bookings.put(uniq++, b);
		System.out.println("Successfully booked");
		System.out.println("=============================================================");
	}

	public static void cancelSeat(){
		System.out.println("Select Booking ID : ");
		displayBookingIds();
		int bid = sc.nextInt();
		Booking bookng = bookings.get(bid);
		for(Ticket tick: bookng.getTickets()){
			String [] details = tick.toString().split("_");
			flights.get(details[0]).cancelTicket(details[1]+"_"+details[2]+"_"+details[3]);
		}
		System.out.println("You should pay "+bookng.cancel()+" for cancelation");
		bookings.remove(bid);
		System.out.println("Successfully Booking cancel");
		System.out.println("=============================================================");
	}
}