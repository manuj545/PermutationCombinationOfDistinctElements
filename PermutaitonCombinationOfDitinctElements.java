/*
 * Classname : PermutaitonCombinationOfDitinctElements
 */


/**
 *  
         This class implements the Algorithm for visiting all permutations/combinations of k objects from a set of n distinct objects, numbered 1..n. 
         The program takes input from the console/terminal/stdin and prints output to stdout.
          Input specification:
   			Each line of input has 3 integers: n, k, and v.
   			n is between 3 and 1000;  k is an integer between 0 and n; v is in {0,1,2,3}.

   		   Output specification:
   			if v = 0, the program outputs one line with 2 integers: the number of permutations of k objects out of n distinct objects, and the time taken
   			by the program in milliseconds (rounded to integer).  Even though the
    		program is not printing the permutations, it should visit all permutations.

   			if v = 1, the program again outputs one line with 2 integers, like the above case, except it outputs the number of combinations of k out of n.

   			if v = 2 (verbose output version of v=0), the program should output all the actual permutations, one line at a time, and then the output of v=0.

   			if v = 3 (verbose output of v=1), output all the actual combinations,and then the output of v=1.	

 *
 * @ImplemntationDetails
 * 		to generate the Permutation of k objects from n objects, we have generated first the combination of the k objects 
 * 		and then find permutations of the resulting combination
 *
 * @version      
         1.0, 13 Sep 2014  
 * @author          
        Manuj Singh
 */
public class PermutaitonCombinationOfDitinctElements
{

	/**  
	k represents the objects that has to be permuted out of n
	 */
	static int k ;

	/**  
	count keeps track of the total number of permutation or combinations
	 */
	static long count = 0;


	/**  
	"v" indicates the mode
	 For details see the output specification at the top of this class
	 */
	static int v;


	public static void main(String[] args) {

		// getting the input from the command line
		int n = Integer.parseInt(args[0]);
		k = Integer.parseInt(args[1]);
		v = Integer.parseInt(args[2]); 

		if(n<3 || n>1000)
		{
			System.out.println("n should be between 3 and 1000");			
		}
		else if(k<0 || k>n)
		{
			System.out.println("k should be between 0 and n");
		}
		else if (v!=0 && v!=1 && v!=2 && v!=3)
		{
			System.out.println("v should be in {0,1,2,3}");
		}
		else
		{
			// array to store the permutations
			int[] A = new int[n];
			long start = System.currentTimeMillis();

			//calling the comb function to generate combination
			Comb(A,n,k);
			long last = System.currentTimeMillis();
			System.out.println( count +"," + (last-start) );
		}
	}

	/**
	 * this methods generated combination of the elements
	 * some locations of A have been taken by i+1.....n
	 * we need to select k objects from 1 ...i
	 *
	 * @param A 		the array which contains the elements
	 * 
	 * @param i			index of ith element
	 * 
	 * @param b				index of second element
	 * 
	 */
	static void  Comb(int[] A, int i,int k)
	{
		// there is only 1 way of choosing nothing. so just visit the combination
		if(k == 0)
		{
			// print all the elements
			if(PermutaitonCombinationOfDitinctElements.v == 1)
			{
				visitCombination(A,i,0);
			}
			else
			{
				visitCombination(A,i,1);
			}
		}
		// 0 ways of choosing k objects from n if k > n
		else if (i < k)
		{
			return;
		}
		else
		{
			//choose ith object
			A[i-1] = i;				// i-1 because the index is zero based

			//  find combination of k-1 objects from i-1 objects
			Comb(A,i-1,k-1);

			//do not choose ith object
			A[i-1] = 0;

			// find combination of k objects from i-1 objects
			Comb(A,i-1,k);
		}
	}
	static void visitCombination(int[] A, int startIndex, int verbose)
	{
		if (verbose > 0) 
		{
			int i = startIndex;
			int NoOfElementsVisited = 0;
			int[] array = new int[k];
			int j =0;
			while(NoOfElementsVisited < k )
			{
				if (A[i] > 0) 
				{
					// if we have to find permutations then create another array and send this combination to another method which will generate permutation
					if(v == 0 || v == 2)
					{
						array[j] = A[i];  
						j++;
					}

					// if only combination are needed print combination
					if(v == 3)
					{
						System.out.print(A[i] + " ");
					}
					NoOfElementsVisited++;

				} 
				i++;
			}

			// if we have to find permutation , then permute the generated combination
			if(v == 0 || v == 2)
			{
				Perm(array,k);
			}
			// printing an empty line
			if(v == 1 || v == 3)
			{
				System.out.println();
			}



		}

		// incrementing the count of the combination
		if(v == 1 || v == 3)
		{
			count++;
		}
	}

	/**
	 * this methods generated permutations of the elements
	 *
	 * @param A 		the array which contains the elements
	 * 
	 * @param no		number of elements
	 * 
	 */
	static void  Perm(int[] A,int no)
	{
		int noOfElements = no;
		// creating an auxillary array
		int[] Aux = new int[noOfElements];
		Perm(A,Aux,noOfElements,noOfElements);
	}

	/**
	 * helper method to generate permutations of the elements
	 *
	 * @preCondition :  i+1...n have been entered into A
	 *					We need to generate permutation of 1....i
	 * @param A 				the array which contains the elements
	 * 
	 * @param Aux				Aux array to store the permutations
	 * 
	 * @param i					index of ith element
	 * 
	 * @param noOfElements		total no of elements
	 */
	private static void  Perm(int[] A,int[] Aux, int i, int noOfElements)
	{
		// if there is no more items
		if(i == 0)
		{
			// print all the elements
			if(PermutaitonCombinationOfDitinctElements.v == 2)
			{
				visitPermutaion(Aux,noOfElements,1);
			}
			else
			{
				visitPermutaion(Aux,noOfElements,0);
			}

		}
		else
		{
			// placing item i in some vacant spot of Auxilary array A
			for(int k =0 ;k < noOfElements ;k++)
			{
				if(Aux[k] == 0)
				{
					Aux[k] = A[i-1];
					// permute over the rest of the items
					Perm(A,Aux,i-1,noOfElements);

					Aux[k] = 0;
				}
			}
		}
	}
	static void visitPermutaion(int[] A, int n, int verbose)
	{
		if (verbose > 0) 
		{
			for(int i = 0; i < n; i++) {
				if (A[i] > 0) 
				{
					System.out.print(A[i] + " ");
				}
			}

			System.out.println();


		}

		// incrementing the   number of the permutations
		count++;
	}

}
