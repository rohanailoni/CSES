import java.util.*;
import java.io.*;

public class Main{
    static class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br=new BufferedReader(new InputStreamReader(System.in));
        }
        String next(){
            while(st==null || !st.hasMoreTokens()){
                try {
                    st=new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt(){
            return Integer.parseInt(next());
        }
        long nextLong(){
            return Long.parseLong(next());
        }
        double nextDouble(){
            return Double.parseDouble(next());
        }
        String nextLine(){
            String str="";
            try {
                str=br.readLine().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }
    }
    static class FastWriter {
		private final BufferedWriter bw;

		public FastWriter() {
			this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
		}

		public void print(Object object) throws IOException {
			bw.append("" + object);
		}

		public void println(Object object) throws IOException {
			print(object);
			bw.append("\n");
		}

		public void close() throws IOException {
			bw.close();
		}
	}
    static int[] string_to_array(String[] arr){
        int[] ans=new int[arr.length];
        for(int i=0;i<arr.length;i++){
            ans[i]=Integer.parseInt(arr[i]);
        }
        return ans;
    }
    static int[] extract(int number){

        int num = number; 
        int count = 0;

        
        while (num > 0) {
            num /= 10;
            count++;
        }
        
        int[] digitArray = new int[count]; // Create an array to store the digits

        num = number; // Reset num to the original number

        // Extract and store digits in the array
        for (int i = count - 1; i >= 0; i--) {
            digitArray[i] = num % 10;
            num /= 10;
        }
        return digitArray;
    }
    static int solve(int n){
        if(n<10){
            return 1;
        }
        int[] dp=new int[n+1];
        Arrays.fill(dp,n+2);
        dp[0]=Integer.MAX_VALUE;
        for(int i=1;i<10;i++){
            dp[i]=1;
        }
        for(int i=10;i<=n;i++){
            int[] digits=extract(i);
            int min=Integer.MAX_VALUE;
            //System.out.println(Arrays.toString(digits));
            for(int j=0;j<digits.length;j++){
                int rem=i-digits[j];
                //System.out.println(rem);
                if(rem>=0){min=Math.min(1+dp[rem],min);}
            }
            //System.out.println(min);
            if(min==Integer.MAX_VALUE){
                dp[i]=0;
            }else{
                dp[i]=min;
            }
            
        }
        //System.out.println(Arrays.toString(dp));
        return dp[n];
    }
	static int solvetoDown(int n,int[] dp){
        
        if(n<10){
            return 1;
        }
        if(dp[n]!=-1){
            return dp[n];
        }
        int min=Integer.MAX_VALUE;
        int[] digits=extract(n);
        for(int i=0;i<digits.length;i++){
            if(digits[i]!=0){
                min=Math.min(1+solvetoDown(n-digits[i], dp),min);
            }
            
        }
        dp[n]=min;
        return dp[n];

    }
    public static void main(String[] args) {
        try {
            FastReader in=new FastReader();
            FastWriter out = new FastWriter();
    
			int n=Integer.parseInt(in.nextLine());
			int[] dp=new int[n+1];
            Arrays.fill(dp,-1);
			out.println(solvetoDown(n,dp));	
			
            
            out.close();
        } catch (Exception e) {
            return;
        }
    }
}
