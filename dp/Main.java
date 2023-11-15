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
    static int solveBottomUp(int n,int[] arr){
        int[] dp=new int[n+1];
        dp[n-1]=1;
        //System.out.println(Arrays.toString(dp));
        for(int i=n-2;i>=0;i--){
            //System.out.println(i);
            int max=0;
            for(int j=i+1;j<n;j++){
                if(arr[i]<arr[j]){
                    max=Math.max(max,dp[j]);
                }
                
            }
            dp[i]=1+max;
        }
        
        return findMax(dp);
    }
    public static int findMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Input array is empty or null.");
        }
    
        int max = arr[0]; // Initialize max to the first element
    
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
    
        return max;
    }
	
    public static void main(String[] args) {
        try {
            FastReader in=new FastReader();
            FastWriter out = new FastWriter();
            
           
			int n=Integer.parseInt(in.nextLine());
			int[] arr=string_to_array(in.nextLine().split(" "));
            
			out.println(solveBottomUp(n,arr));
            
            out.close();
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
    }
}
