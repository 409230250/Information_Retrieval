/* 
 * Assignment 2
 * Chen, Andy K : 45168779
 * Lin, Junjie : 25792830
 * Samtani, Chirag V: 63279154
 * Derian, Fransiskus : 82691258
 * 
 */

package ir.assignments.three.b;

import ir.assignments.three.a.Frequency;
import java.util.Comparator;

// I got the idea from StackOverflow: http://stackoverflow.com/questions/2784514/sort-arraylist-of-custom-objects-by-property
// Comparator has a boolean with false by default, so if we get positive number => switch; negative => stay; 0 => okay.

public class WordFrequencyComparator implements Comparator<Frequency>{
	@Override 
	public int compare(Frequency f1, Frequency f2){
		if (f1.getFrequency() > f2.getFrequency()){        // Compare the frequency number
			return -1;									  // if f1 has a bigger frequency, then stay.
		}
		else if (f1.getFrequency() < f2.getFrequency()){	// if f1 has a smaller friquency, then move down.
			return 1;
		}
		else{                                              // if the text frequency is the same as the other, then order them alphabetically 
			return f1.getText().compareTo(f2.getText());	// if true (f1 is bigger), then move down
		}
	}
}
