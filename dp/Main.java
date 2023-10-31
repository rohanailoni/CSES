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
    private static int solve(int m,int n){
        int[][] dp;
        if(m>n){
            dp=new int[m+1][m+1];
        }else{
            dp=new int[n+1][n+1];
        }
        int k=0;
        for(int i=1;i<=(m>n?m:n);i++){
            dp[1][i]=k;
            k++;
        }
        k=0;
        for(int i=1;i<=(m>n?m:n);i++){
            dp[i][1]=k;
            k++;
        }
        for(int i=2;i<=m;i++){
            for(int j=2;j<=n;j++){
                if(i==j){
                    dp[i][j]=0;
                }else{
                    if(i*2==j || j*2==i){
                        dp[i][j]=1;
                    }else{
                        int min=Integer.MAX_VALUE;
                        //check vertically 
                        for(k=1;k<i;k++){
                            min=Math.min(dp[k][j]+dp[i-k][j], min);
                        }
                        for(k=1;k<j;k++){
                            min=Math.min(min,dp[i][k]+dp[i][j-k]);
                        }
                        min++;
                        dp[i][j]=min;
                        dp[j][i]=min;
                        //check horizontally
                    }
                }
            }
        }
        //System.out.println(Arrays.deepToString(dp));
        return dp[m][n];
    }
	
    public static void main(String[] args) {
        try {
            FastReader in=new FastReader();
            FastWriter out = new FastWriter();
            
           
			int[] arr=string_to_array(in.nextLine().split(" "));
			out.println(solve(arr[0], arr[1]));	
			
            out.close();
        } catch (Exception e) {
            return;
        }
    }
}
