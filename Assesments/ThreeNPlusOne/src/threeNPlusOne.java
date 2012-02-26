//import java.util.HashMap;

public class threeNPlusOne {
	public static int[] lengthList = new int[1000000];
	public int current = 0;
	
	public static void main(String[] args) {
		int lowerLimit = Integer.parseInt(args[0]);
		int upperLimit = Integer.parseInt(args[1]);
		
		int max = 0;
		int length = 0;
		
		for (int i = lowerLimit; i<= upperLimit; i++){
			length = lengthCalc(i);
			if (length > max){
				max = length;
			}
		}
		System.out.println(lowerLimit + " " + upperLimit + " " + max);
	}

	public static int lengthCalc(long i){
		long n = i;
		int result = 0;
		int sequenceLength = (Integer) (n < 1000000 ? lengthList[(int) n] : 0);
		if (sequenceLength > 0){
			result = sequenceLength;
		}
		else
		{
			if (n == 1){ //1
				result = 1;
			}
			else if ((n%2) != 1){ //even
				result = 1 + lengthCalc(n/2);
			}
			else { //odd
				result = 1 + lengthCalc(3*n+1);
			}
			if (n < 1000000){
				lengthList[(int) n]= result;
			}
		}
		return result;
	}
}
