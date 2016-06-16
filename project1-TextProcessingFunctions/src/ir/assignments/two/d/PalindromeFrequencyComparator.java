package ir.assignments.two.d;

import ir.assignments.two.a.Frequency;
import java.util.Comparator;

// I got the idea from StackOverflow: http://stackoverflow.com/questions/2784514/sort-arraylist-of-custom-objects-by-property

public class PalindromeFrequencyComparator implements Comparator<Frequency> {
   @Override
   public int compare(Frequency f1, Frequency f2) {
      if (f1.getText().length() < f2.getText().length()) {return 1;}        // First, compare the Frequency's text (Length of string: longest one go first)

      else if (f1.getText().length() > f2.getText().length()) {return -1;}
         
      else {                                                               // Second, if the text length are the same, then compare the frequency number (frequency )
         if (f1.getFrequency() < f2.getFrequency()) {return 1;}
         
         else if (f1.getFrequency() > f2.getFrequency()) {return -1;}
         
         else {                                                            // Third, if the text length and frequency are the same as the other, then we order it alphabetically (alphabetical)
            return f1.getText().compareTo(f2.getText());
         }
      }  
   }
}