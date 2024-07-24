
//Recursion -->TLE
// tc- O(2^n)  sc-O(n) auxillary stack space
class Solution{

    static boolean f(int ind,int target,int[] arr)
    {
        if(target==0) return true;
        
        if(ind==0) return (arr[0]==target);
        
        boolean nottake=f(ind-1,target,arr);
        
        boolean take=false;
        
        if(target>=arr[ind]) take=f(ind-1,target-arr[ind],arr);
        
        return take || nottake;
    }
    static Boolean isSubsetSum(int N, int arr[], int sum){
        // code here
        return f(N-1,sum,arr);
    }
}


// memoization 
// tc- o(n*target)  sc-o(n*target)*O(n)  -------->n auxillary stack space which can be improved


class Solution{

    static boolean f(int ind,int target,int[] arr,int[][] dp)
    {
        if(target==0) return true;
        
        if(ind==0) return (arr[0]==target);
        
        
         if (dp[ind][target] != -1)
            return dp[ind][target] == 0 ? false : true;
        
        boolean nottake=f(ind-1,target,arr,dp);
        
        boolean take=false;
        
        if(target>=arr[ind]) take=f(ind-1,target-arr[ind],arr,dp);
        
        dp[ind][target]=nottake || take ?1:0;
        return take || nottake;
    }
    static Boolean isSubsetSum(int N, int arr[], int sum){
        // code here
        int dp[][]=new int[N][sum+1];
        
        for(int[] row:dp)
        {
            Arrays.fill(row,-1);
        }
        
        
        return f(N-1,sum,arr,dp);
    }
}



// tabulation  
// TC- O(N*K)  SC-O(N*K)




class Solution{

    
    static Boolean isSubsetSum(int N, int arr[], int k){
        boolean dp[][] = new boolean[N][k + 1];
        
        
        for (int i = 0; i < N; i++) {
            dp[i][0] = true;
        }

        
        if (arr[0] <= k) {
            dp[0][arr[0]] = true;
        }

        
        for (int ind = 1; ind < N; ind++) {
            for (int target = 1; target <= k; target++) {
                
                boolean notTaken = dp[ind - 1][target];
                
               
                boolean taken = false;
                if (arr[ind] <= target) {
                    taken = dp[ind - 1][target - arr[ind]];
                }
                
                
                dp[ind][target] = notTaken || taken;
                
            }
        }

        // The final result is stored in the bottom-right cell of the DP table
        return dp[N - 1][k];
    }
}


// *********Space optimization*********

// TC-O(N*K)
// SC- O(K)  ek hi external array using to store only one row at a time 



class Solution{

    
    static Boolean isSubsetSum(int n, int arr[], int k){
         boolean prev[] = new boolean[k + 1];

        prev[0] = true;
        if (arr[0] <= k) {
            prev[arr[0]] = true;
        }
        for (int ind = 1; ind < n; ind++) {
           
            boolean cur[] = new boolean[k + 1];
            cur[0] = true;
            
            for (int target = 1; target <= k; target++) {
               
                boolean notTaken = prev[target];
                boolean taken = false;
                if (arr[ind] <= target) {
                    taken = prev[target - arr[ind]];
                }
               
                cur[target] = notTaken || taken;
            }
            
           
            prev = cur;
        }

        
        return prev[k];
    }
}