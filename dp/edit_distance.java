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
	static int solve(String from,String to,int i,int j,int[][] dp){
        if(j==to.length() && i!=from.length()){
            return from.length()-i;
        }
        if(j!=to.length() && i==from.length()){
            return to.length()-j;
        }
        if(j==to.length() && i==from.length()){
            return 0;
        }
        if(dp[i][j]!=-1){
            return dp[i][j];
        }
        int result;

        if(from.charAt(i)==to.charAt(j)){
            result=solve(from,to,i+1,j+1,dp);
        }else{
            int insert=1+solve(from,to,i,j+1,dp);
            int remove=1+solve(from,to,i+1,j,dp);
            int repl=1+solve(from,to,i+1,j+1,dp);
            result=Math.min(insert,Math.min(remove,repl));
        }
        dp[i][j]=result;
        return result;
    }
    static int solveFromDown(String from,String to){
        int[][] dp=new int[from.length()+2][to.length()+2];
        for(int i=0;i<from.length()+1;i++){
            dp[i][0]=i;
        }
        for(int i=0;i<to.length()+1;i++){
            dp[0][i]=i;
        }
        for(int i=1;i<=from.length();i++){
            for(int j=1;j<=to.length();j++){
                if(from.charAt(i-1)==to.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }else{
                    dp[i][j]=Math.min(1+dp[i][j-1], Math.min(1+dp[i-1][j], 1+dp[i-1][j-1]));
                }

            }
        }
        return dp[from.length()][to.length()];
    }
    public static void main(String[] args) {
        try {
            FastReader in=new FastReader();
            FastWriter out = new FastWriter();
            String from=in.nextLine();
            String to=in.nextLine();
            //System.out.println(from+" "+to);
            // int[][] dp=new int[from.length()+1][to.length()+1];
            // for(int[] r:dp){
            //     Arrays.fill(r, -1);
            // }
            // out.println(solve(from, to, 0, 0,dp));
            out.println(solveFromDown(from,to));
            out.close();
        } catch (Exception e) {
            return;
        }
    }
}
