1) To measure the execution times, I built the program with this 
functionality from the beginning. All of the sorting algorithms 
sort in place, and return the time in miliseconds which is given 
by calling System.nanoTime(); and setting it to a long called start, 
and then calling it again before it returns, and subtracting the 
current nanoTime from the start time to get the elapsed time. 
Then it's divided by 1 million to get miliseconds instead of nanoseconds. 
I simply ran sortComparison.java 3 times using each of the files 3 times each. 

The files are listed below:

numbers.txt : 10 lines long, from the provided download

10k.txt : 10,000 lines long, created using OpenAI ChatGPT 5, 

with prompt : "can you create a test text file with one integer 
per line, of 10000 lines, that i can use to test sorting algorithms. 
call it 10k.txt"

100k.txt : 100,000 lines long, given by the provided download

2) Testing Results: I tested on my laptop after restarting it 
and runnning the tests one after another, with no other programs 
open other than my IDE.

n = 10
Test-----Bubble Sort--------Merge Sort
1        0.002ms            0.006ms
2        0.003ms            0.012ms
3        0.002ms            0.008ms

AVERAGE: 0.00233ms          0.00866ms

n = 10,000
Test-----Bubble Sort--------Merge Sort
1        110.738ms          2.022ms
2        118.563ms          2.765ms
3        112.534ms          2.763ms

AVERAGE: 113.972ms          2.517ms

n = 100,000
Test-----Bubble Sort--------Merge Sort
1        11429.816ms        26.03ms
2        11426.728ms        23.945ms
3        11573.616ms        18.916ms

AVERAGE: 11476.720ms        22.963ms

3) The measurement accuracy should be down to the nearest nanosecond, 
as it is provided by System.nanoTime(). I do divide it in order to 
display milliseconds, but the accuracy should still be close to 
nanosecond precision. It may be very slightly off due to when it was 
called and the CPU clock cycles it took but it should be considered 
to be close enough for our purposes.

4) In order to determine the closeness of the time it took to sort 
versus the actual results, we can calculate using the approximate 
clock speed of my processor. 

Looking at the clock speed of my processor in task manager when 
I run the bubbleSort algorithm, it seems to hover around 2.2 GHz. 
This means it's making 2.2 x 10^9 cycles per second. if we assume
that each operation  takes about 5 clock cycles (I cant know this 
for sure it is just a guess), then the expected number of operations 
per second is 4.4 x 10^8. Since Bubble Sort has a theoretical time 
complexity of O(n^2), we can estimate a time for each of the n values 
by performing n^2 and then dividing by the operations per second. 
Then I will convert that result to ms so we can directly compare.

n--------n^2-----estimated time (s)--estimated time (ms)
1e1      1e2     2.3e-7s             0.00023ms           
1e4      1e8     0.3s                300ms
1e5      1e10    22.3s               22300ms

Now the same thing for Merge Sort (instead of O(n^2) its O(n logn)):

n--------nlogn---------estimated time (s)--estimated time (ms)
1e1      33.219        7.5e-8s             7.5e-5ms          
1e4      132877.123    3.0e-4s             3.0ms
1e5      1660964.047   3.7e-3s             37ms


Comparing this estimated time with the collected data:

Bubble
n-------estimate------actual------difference---%difference
1e1     0.00023ms     ~0.00233ms  +0.0021ms    +163.0%
1e4     300ms         ~113.982ms  -186.018ms   -89.87%
1e5     22300ms       ~11476.7ms  -10823.3ms   -64.09%

Merge
n-------estimate------actual------difference---%difference
1e1     7.5e-5ms      ~0.00866ms  +0.0086ms    +196.6%
1e4     3.0ms         ~2.517ms    -0.4830ms    -17.51%
1e5     37ms          ~22.963ms   -14.037ms    -46.81%

I think it would be fair to remove the n = 10 case, as the % differences are 
above 100%. So comparing the other values, the estimate is close enough to at 
least be the same power of 10 that I would consider it to closely follow the 
expected distributions.
