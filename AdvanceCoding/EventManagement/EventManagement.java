import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class EventManagement{
	static final Scanner sc = new Scanner(System.in);
	static boolean [] taken = new boolean[20];
	static int totalEvents = 0;

	public static void main(String[] args) {
	
		List<Pair> events = new ArrayList<>();
		for (int i=0; i<20; i++) {
			String str = sc.nextLine();
			if(str.equals("Q")) // Q for quit 
				break;
			totalEvents++;
			boolean find = false;
			int time = 0;
			String [] arr = str.split(" ");

			for (int j=0; j<arr.length; j++) {
				if(arr[j].equals("lightning")){
					time = 5;
					find = true;
					break;
				}
			}

			for (int j=0; j<arr.length && !find; j++) {
				if(arr[j].charAt(0) >= 49 && arr[j].charAt(0) <= 57){
					time = Integer.parseInt(arr[j].substring(0, 1));
					for (int k = 1; k<arr[j].length(); k++) {
						if(arr[j].charAt(k) >= 48 && arr[j].charAt(k) <= 57){
							time *= 10;
							time += Integer.parseInt(arr[j].substring(k, k+1));
						}
						else {
							find = true;
							break;
						}						
					}
				}
				if(find) break;
			}

			events.add(new Pair(str, time));

		}	
		Arrays.fill(taken, false);
		int day = 1;

		while(totalEvents > 0){
			int time = 9*60;
			System.out.println("Schedule for Day: "+(day++));
			TimeManage tmanage = new TimeManage(events);
			
			List<Pair> morn = tmanage.manage("morn");
			totalEvents -= morn.size();

			for (int i=0; i<morn.size(); i++) {
				int hours = time/60;
				int min = time%60;
				String h = Integer.toString(hours);
				String m = Integer.toString(min);
				if(hours < 10) h = "0"+h;
				if(min < 10) m = "0"+m;
				System.out.println(h+":"+m+" AM "+morn.get(i));
				time += morn.get(i).time;
			}
			System.out.println("12:00 PM LUNCH");


			List<Pair> even = tmanage.manage("even");
			totalEvents -= even.size();
			time = 60;

			for (int i=0; i<even.size(); i++) {
				int hours = time/60;
				int min = time%60;
				String h = Integer.toString(hours);
				String m = Integer.toString(min);
				if(hours < 10) h = "0"+h;
				if(min < 10) m = "0"+m;
				System.out.println(h+":"+m+" PM "+even.get(i));
				time += even.get(i).time;
			}
			if(time/60 == 5)
				System.out.println("05:00 PM Networking hands-on");
			else if(time/60 < 4){
				System.out.println("04:00 PM Networking hands-on");
			}
			else {
				int hours = time/60;
				int min = time%60;
				String h = Integer.toString(hours);
				String m = Integer.toString(min);
				if(hours < 10) h = "0"+h;
				if(min < 10) m = "0"+m;
				System.out.println(h+":"+m+" PM "+"Networking hands-on");				
			}

			System.out.println();
		}

	}
}

class Pair{
	String event;
	int time;
	public Pair(String event, int time){
		this.event = event;
		this.time = time;
	}

	public String toString(){
		return (this.event);
	}
}