
public class Timer {

	long longStart=0, longEnd=0;
	
	public void setStart() {
		longStart = System.nanoTime();
	}
	
	public void setEnd(){
		longEnd = System.nanoTime();
	}
	
	public long getElapsedNano() {
		
		//if both start and end times are set, return diff, else return -1
		if(longStart > 0 && longEnd > 0) {
			return (longEnd- longStart);
		}
		else {
			return -1;
		}
	}
}
