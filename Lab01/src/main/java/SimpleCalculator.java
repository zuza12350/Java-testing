import java.util.List;

public class SimpleCalculator {
    public int add(int a, int b){
        return a+b;
    }
    public int subtract(int a, int b){
        return a-b;
    }
    public int divide(int a,int b){
        if (b == 0)
            throw new IllegalArgumentException("Can't divide by zero!");
        return a / b;
    }
    public int multiply(int a, int b){
        return a*b;
    }
    public float average(List<Integer> nums){
        float sum = 0;
        for(int num:nums) {
            sum += num;
        }
        return sum / nums.size();
    }
    public int factorial(int num) {
        if (num==0)
            return 1;
        else
            return (num*factorial(num-1));
    }

}
