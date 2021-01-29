import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TimeManage{
	List<Pair> events;
	Map<Integer, Pair> taken = new HashMap<>();
	List<Integer> temp = new ArrayList<>();
	List<Pair> out = new ArrayList<>();
	int low = Integer.MIN_VALUE;

	public TimeManage(List<Pair> events){
		this.events = events;
	}

	public List<Pair> manage(String morev){
		int tar = 0;
		low = Integer.MIN_VALUE;
		out.clear();

		if(morev.equals("morn")) tar = 3*60;
		else tar = 4*60;

		bestTiming(0, 0, tar, events.size());
		int val = 0;
		for (int i : temp) {
			if(i<events.size()){
				out.add(events.get(i));
			}
		}
		for(int i: temp) if(i<events.size()){events.remove(i);}
			
		return out;
	}

	private void bestTiming(int s, int sum, int tar, int n){
		if(low < sum && sum <= tar){
			low = sum;
			temp.clear();
			temp.addAll(taken.keySet());
		}

		if(sum >= tar) return;

		if(s == n) return;

		for (int i=s; i<n; i++) {
			if(!taken.containsKey(i) && sum+events.get(i).time <= tar){
				taken.put(i, events.get(i));
				bestTiming(i+1, sum+events.get(i).time, tar, n);
				taken.remove(i);
			}
		}
	}
}