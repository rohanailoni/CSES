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
    static SortedMap<Integer,Boolean>map;
	public static void solve(int[] arr,int n,int index,int sum){
        if(index==n){
            if(!map.containsKey(sum) && sum!=0){
                map.put(sum, true);
            }
            return;
        }
        if(index+1<=n){
            solve(arr, n, index+1, sum);
            solve(arr, n, index+1, sum+arr[index]);
        }
        return;
    }
    public static void BottomUp(int[] arr,int n){
        int max_sum=(int)Math.pow(10, 5);
        Boolean[][] dp=new Boolean[n+1][max_sum+1];
        for(Boolean[] x:dp){
            Arrays.fill(x, false);
        }
        dp[0][0]=true;
        for(int i=1;i<=n;i++){
            for(int current=0;current<=max_sum;current++){
                dp[i][current]=dp[i-1][current];
                int pre=current-arr[i-1];
                if(pre>=0 && dp[i-1][pre]==true){
                    dp[i][current]=true;
                }
            }
        }
        ArrayList<Integer>ans=new ArrayList<>();
        for(int i=1;i<=max_sum;i++){
            if(dp[n][i]==true){
                ans.add(i);
            }
        }
        System.out.println(ans.size());
        for(int i=0;i<ans.size();i++){
            System.out.print(ans.get(i)+" ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        try {
            FastReader in=new FastReader();
            FastWriter out = new FastWriter();
            
            
			int n=Integer.parseInt(in.nextLine());
			int[] arr=string_to_array(in.nextLine().split(" "));
            //map=new TreeMap<Integer,Boolean>();
			//solve(arr, n, 0, 0);
            BottomUp(arr, n);
            
            out.close();
        } catch (Exception e) {
            //System.out.println(e.printStackTrace());
            e.printStackTrace();
            return;
        }
    }
}
