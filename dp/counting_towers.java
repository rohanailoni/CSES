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
	private static long solve(int n){
        long[][] dp=new long[n+1][2];
        dp[1][0]=1;
        dp[1][1]=1;
        int MOD=(int)1e9+7;
        for(int i=2;i<=n;i++){
            dp[i][0]=((dp[i-1][0]*4L)%MOD +dp[i-1][1])%MOD;
            dp[i][1]=(dp[i-1][0] + (dp[i-1][1]*2L)%MOD)%MOD;
        }
        //System.out.println(Arrays.deepToString(dp));
        return (dp[n][0]+dp[n][1])%MOD;
    }
    public static void main(String[] args) {
        try {
            FastReader in=new FastReader();
            FastWriter out = new FastWriter();
            int testCases=in.nextInt();
            List<Long>answer=new ArrayList<>();
            int Mxn=(int)1e6+6;
            long[][] dp=new long[Mxn+1][2];
            dp[1][0]=1;
            dp[1][1]=1;
            int MOD=(int)1e9+7;
            for(int i=2;i<=Mxn;i++){
                dp[i][0]=((dp[i-1][0]*4L)%MOD +dp[i-1][1])%MOD;
                dp[i][1]=(dp[i-1][0] + (dp[i-1][1]*2L)%MOD)%MOD;
            }
            while(testCases-- > 0){
				int n=Integer.parseInt(in.nextLine());
				answer.add((dp[n][0]+dp[n][1])%MOD);
				//System.out.println(n);
			}
            for(Long s:answer){
				out.println(s);
            }
            out.close();
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
    }
}
