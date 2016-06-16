package ir.assignments.two.b;

import ir.assignments.two.a.Frequency;
import java.util.Comparator;

// I got the idea from StackOverflow: http://stackoverflow.com/questions/2784514/sort-arraylist-of-custom-objects-by-property

public class WordFrequencyComparator implements Comparator<Frequency>{
	@Override 
	public int compare(Frequency f1, Frequency f2){
		if (f1.getFrequency() > f2.getFrequency()){        // Compare the frequency number
			return -1;
		}
		else if (f1.getFrequency() < f2.getFrequency()){
			return 1;
		}
		else{                                              // if the text frequency is the same as the other, then order them alphabetically 
			return f1.getText().compareTo(f2.getText());	
		}
	}
}
