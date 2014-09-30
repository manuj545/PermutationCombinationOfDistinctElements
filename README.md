PermutationCombinationOfDistinctElements
========================================

enumeration of permutations and combinations of set of distinct numbers and showing output depending upon the verbose mode. 

This file contains a JAVA class implementing enumeration of permutations and combinations of set of distinct numbers and showing output depending upon the verbose mode. 

The program accepts three command line arguments:
1. First argument is total number of objects (n), n is between 3 and 1000
2. Second argument is for number of objects that needs to be permuted out of total objects (k), 0<= k <= n.
3. Third argument is for the verbose mode (v), it can be {0,1,2,3}.

a) if v = 0, the program outputs one line with 2 integers: the number of permutations of k objects out of n distinct objects, and the time taken by the program in milliseconds (rounded to integer). 

b)if v = 1, the program again outputs one line with 2 integers, like the above case, except it outputs the number of combinations of k out of n.

c) if v = 2 (verbose output version of v=0), the program should output all the actual permutations, one line at a time, and then the output of v=0.

d) if v = 3 (verbose output of v=1), output all the actual combinations,and then the output of v=1.
