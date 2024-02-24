/*
 * following parallel binary search algorithm for series a1, a2...an sorted in increasing order such that n mod 10 = 0
 * Element to be searched is e
 *  a) Create n/10 threads t1, t2,..,tn/10
 *  b) Distribute the numbers among threads such that ti will have numbers ai, ai+1, ....a2i-1
 *  c) Distribute the element e to all threads
 *  d) Each thread searches the element e in its sub-array using binary search algorithm
*/

package q6;

public class Main {
    
}
