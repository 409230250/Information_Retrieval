

Junjie Lin (25792830)
CS121 Assignment 1

Folder A
	1. Frequency.java
		DO NOT MODIFY THIS CLASS
		
		
	2. Utilities.java

		Function tokenizeFile:
			It returns a list of strings that is made by a-zA-Z0-9 only, no space, empty string, or any punctuation.
			IOException would be throw if input file does not exist.
			I also write another error exception to catch all other errors.
		
		Function printFrequencies:
			It prints each item from the input list from the first one to last one.
			If the input list is empty, then totalItemCount and uniqueItemCount are 0.
			For X-gram count, the "X" is based on the amount of word in the first item from the input list.
				If input list is ["a", "b"], then X would be "item" since the first element has one word.
				If input list is ["a a", "b"], then X would be "2" because the first element has two words.
			For printing, the space I give the Frequency's text is the longest text length in the list plus 5 empty spaces. I give the frequency 3 spaces.
				In such way, I want the output to be organized, and the frequencies can be line up.     
			Before I move on to part B, I created a main function to test my part A, and it words, so I just commented.
			
			
Folder B
	3. WordFrequencyComparator.java
		
		Function compare:
			Part B and C would call this function.
			I learn it from StackOverflow.
			It compares two objects; compare the frequency number first, and if the frequencies are the same, then order them alphabetically.
			I first came up with my own sorting algorithm when I was writing the computeWordFrequencies, but I calculated the running time is not efficient, O(n^2).
				Then I asked a question on Piazza if there is a better way to do this, and someone suggested that I can use Comparator.
			

	4. WordFrequencyCounter.java
		
		Function computeWordFrequencies:
			For the order, I follow the instruction.
			I get the idea of using map or HashMap data structure from posts on piazza, learned from StackOverflow as well.
			Sorting Algorithm is explained above. (I just commented my own sorting algorithm.)
			
			
Folder C		
	5. TwoGramFrequencyCounter.java
		
		Function computeTwoGramFrequencies:
			This function is basically similar to the Function WordFrequencyCounter.
			Not much to say for this one, following the instruction
		
		
Folder D
	6. analysis.txt
		I am not good at writing pseudocode, so I just copy and paste my function and do an analysis here.
		
		
	7. PalindromeFrequencyComparator.java
		
		Function compare:
			I use the same idea I learn from StackOverflow.
			I make it the way so that it can order the object by "Length of string > frequency > alphabetical"
			
	
	8. PalindromeFrequencyCounter.java
		
		Function computePalindromeFrequencies:
			I got the idea from posts on Piazza. I use two for loop to iterate each word from the input list. 
				First, check if the current word is a palindrome(If yes, add to the map), then in the inner loop add the next word to the current word and check again. And then add the next word again, and so on.
				Once it completes check the first word, then in the outer loop, move to the second word, and repeat the previous step.
			I have learned how to check a string if it is a palindrome in ICS45J by Professor Wang. I would check if the string's first half is the same as second half.
			
		
		
		

