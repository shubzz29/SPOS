import java.util.*;

public class shec {
	Scanner in = new Scanner(System.in);
	public void fcfs(){
		System.out.println("Enter number of processes: ");
		int n = in.nextInt();
		process[] p_fcfs = new process[n];
		for(int i=0;i<n;i++)
		{
			p_fcfs[i] = new process();
		}
		System.out.println("Enter Burst Time for each process: ");
		for(int i=0;i<n;i++)
		{
			p_fcfs[i].bt = in.nextInt();
		}
		//calc wt
		p_fcfs[0].wt = 0;
		for(int i=1;i<n;i++)
		{
			p_fcfs[i].wt = p_fcfs[i-1].bt+p_fcfs[i-1].wt;
		}
		//calc tat
		for(int i=0;i<n;i++)
		{
			p_fcfs[i].tat = p_fcfs[i].bt+p_fcfs[i].wt;
		}
		//calc avg
		float avg_wt=0,avg_tat=0;
		for(int i=0;i<n;i++)
		{
			avg_wt += p_fcfs[i].wt;
			avg_tat += p_fcfs[i].tat;
		}
		avg_wt /= n;
		avg_tat /= n;
		
		System.out.println("process_No.  Burst_Time  Waiting_Time  Turn_Around_Time\n");
		for(int i=0;i<n;i++)
		{
			System.out.println((i+1)+"\t\t"+p_fcfs[i].bt+"\t\t"+p_fcfs[i].wt+"\t\t"+p_fcfs[i].tat+"\n");
		}
		System.out.println("\n\nAvg_wt: "+avg_wt+"\nAvg_tat: "+avg_tat+"\n");
	}
	
	public void fcfs_at(){
		System.out.println("Enter number of processes: ");
		int n = in.nextInt();
		process[] p_fcfs = new process[n];
		for(int i=0;i<n;i++)
		{
			p_fcfs[i] = new process();
		}
		System.out.println("Enter Burst Time for each process: ");
		for(int i=0;i<n;i++)
		{
			p_fcfs[i].bt = in.nextInt();
		}
		System.out.println("Enter Arrival Time for each process: ");
		for(int i=0;i<n;i++)
		{
			p_fcfs[i].at = in.nextInt();
		}
		//calc wt
		p_fcfs[0].wt = 0;
		for(int i=1;i<n;i++)
		{
			p_fcfs[i].wt = p_fcfs[i-1].bt+p_fcfs[i-1].wt-p_fcfs[i].at;
		}
		//calc tat
		for(int i=0;i<n;i++)
		{
			p_fcfs[i].tat = p_fcfs[i].bt+p_fcfs[i].wt;
		}
		//calc avg
		float avg_wt=0,avg_tat=0;
		for(int i=0;i<n;i++)
		{
			avg_wt += p_fcfs[i].wt;
			avg_tat += p_fcfs[i].tat;
		}
		avg_wt /= n;
		avg_tat /= n;
		
		System.out.println("process_No.  Burst_Time  Arrival_Time  Waiting_Time  Turn_Around_Time\n");
		for(int i=0;i<n;i++)
		{
			System.out.println((i+1)+"\t\t"+p_fcfs[i].bt+"\t\t"+p_fcfs[i].at+"\t\t"+p_fcfs[i].wt+"\t\t"+p_fcfs[i].tat+"\n");
		}
		System.out.println("\n\nAvg_wt: "+avg_wt+"\nAvg_tat: "+avg_tat+"\n");
	}
	
	public void sjf_np(){
		System.out.println("Enter number of processes: ");
		int n = in.nextInt();
		process[] p_sjf = new process[n];
		for(int i=0;i<n;i++)
		{
			p_sjf[i] = new process();
		}
		System.out.println("Enter Burst Time for each process: ");
		for(int i=0;i<n;i++)
		{
			p_sjf[i].bt = in.nextInt();
			p_sjf[i].wt = 0;
		}
		//calc wt
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(p_sjf[i].bt>p_sjf[j].bt)
					p_sjf[i].wt += p_sjf[j].bt;
			}
		}
		//calc tat
		for(int i=0;i<n;i++)
		{
			p_sjf[i].tat = p_sjf[i].wt+p_sjf[i].bt;
		}
		//calc avg
		float avg_wt=0,avg_tat=0;
		for(int i=0;i<n;i++)
		{
			avg_wt += p_sjf[i].wt;
			avg_tat += p_sjf[i].tat;
		}
		avg_wt /= n;
		avg_tat /= n;
		System.out.println("process_No.  Burst_Time  Waiting_Time  Turn_Around_Time\n");
		for(int i=0;i<n;i++)
		{
			System.out.println((i+1)+"\t\t"+p_sjf[i].bt+"\t\t"+p_sjf[i].wt+"\t\t"+p_sjf[i].tat+"\n");
		}
		System.out.println("\n\nAvg_wt: "+avg_wt+"\nAvg_tat: "+avg_tat+"\n");
	}
	
	public static void sort_at(process[] p_srtf, int n)
	{
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n-i-1;j++)
			{
				if(p_srtf[j].at>p_srtf[j+1].at)
				{
					process temp = p_srtf[j];
					p_srtf[j] = p_srtf[j+1];
					p_srtf[j+1] = temp;
				}
			}
		}
	}
	
	public static void sort_bt(process[] pq, int n)
	{
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n-i-1;j++)
			{
				if(pq[j].bt>pq[j+1].bt)
				{
					process temp = pq[j];
					pq[j] = pq[j+1];
					pq[j+1] = temp;
				}
			}
		}
	}
	
	public static boolean dec(process[] pq, int n)
	{
		int k=0;
		while(pq[k].bt!=0)
		{
			k++;
			if(k==n)
			{
				return false;
			}
		}	
		
		pq[k].bt--;
		for(int i=0;i<n;i++)
		{
			if(pq[i].at!=0)
			{
				pq[i].at--;
			}
			if(pq[i].at==0&&i!=0)
			{
				pq[i].wt++;
			}
		}
		return true;
	}
	
	public void srtf(){
		System.out.println("Enter number of processes: ");
		int n = in.nextInt();
		process[] p_srtf = new process[n];
		for(int i=0;i<n;i++)
		{
			p_srtf[i] = new process();
		}
		System.out.println("Enter Burst Time for each process: ");
		for(int i=0;i<n;i++)
		{
			p_srtf[i].bt = in.nextInt();
			p_srtf[i].rt = p_srtf[i].bt;
		}
		System.out.println("Enter Arrival Time for each process: ");
		for(int i=0;i<n;i++)
		{
			p_srtf[i].at = in.nextInt();
		}
		//calc wt
        int complete = 0, t = 0, minm = Integer.MAX_VALUE; 
        int shortest = 0, finish_time; 
        boolean check = false; 
        while (complete != n) { 
       
            // Find process with minimum 
            // remaining time among the 
            // processes that arrives till the 
            // current time
            for (int j = 0; j < n; j++)  
            { 
                if ((p_srtf[j].at <= t) && 
                  (p_srtf[j].rt < minm) && p_srtf[j].rt > 0) { 
                    minm = p_srtf[j].rt; 
                    shortest = j; 
                    check = true; 
                } 
            } 
       
            if (check == false) { 
                t++; 
                continue; 
            } 
       
            // Reduce remaining time by one 
            p_srtf[shortest].rt--; 
       
            // Update minimum 
            minm = p_srtf[shortest].rt; 
            if (minm == 0) 
                minm = Integer.MAX_VALUE; 
       
            // If a process gets completely 
            // executed 
            if (p_srtf[shortest].rt == 0) { 
       
                // Increment complete 
                complete++; 
                check = false; 
       
                // Find finish time of current 
                // process 
                finish_time = t + 1; 
       
                // Calculate waiting time 
                p_srtf[shortest].wt = finish_time - p_srtf[shortest].bt - p_srtf[shortest].at; 
       
                if (p_srtf[shortest].wt < 0) 
                    p_srtf[shortest].wt = 0; 
            } 
            // Increment time 
            t++; 
        } 
		//calc tat
		for (int i = 0; i < n; i++) 
            p_srtf[i].tat = p_srtf[i].bt + p_srtf[i].wt;
		//calc avg
		float avg_wt=0,avg_tat=0;
		for(int i=0;i<n;i++)
		{
			avg_wt += p_srtf[i].wt;
			avg_tat += p_srtf[i].tat;
		}
		avg_wt /= n;
		avg_tat /= n;
		
		System.out.println("process_No.  Burst_Time  Waiting_Time  Turn_Around_Time\n");
		for(int i=0;i<n;i++)
		{
			System.out.println((i+1)+"\t\t"+p_srtf[i].bt+"\t\t"+p_srtf[i].wt+"\t\t"+p_srtf[i].tat+"\n");
		}
		System.out.println("\n\nAvg_wt: "+avg_wt+"\nAvg_tat: "+avg_tat+"\n");
	}

	public void round_robin(){
		System.out.println("Enter number of processes:");
		int n = in.nextInt();
		System.out.println("Enter Quantum Size: ");
		int window = in.nextInt();
		process[] p_rr = new process[n];
		for(int i=0;i<n;i++)
		{
			p_rr[i] = new process();
		}
		System.out.println("Enter burst times:");
		for(int i=0;i<n;i++)
		{
			p_rr[i].bt = in.nextInt();
			p_rr[i].rt = p_rr[i].bt;
			p_rr[i].tat = 0;
		}
		//calc tat
		int k=0,itr=0,complete=0;
		System.out.println("\n");
		while(complete!=n)
		{
			if(p_rr[k].rt>window)
			{
				p_rr[k].rt -= window;
				itr += window;
			}
			else if(p_rr[k].rt!=0)
			{
				itr += p_rr[k].rt;
				p_rr[k].rt = 0;
				p_rr[k].tat = itr;
				complete++;
			}
			k++;
			if(k==n)
				k = 0;
		}
		//calc wt
		for(int j=0;j<n;j++)
		{
			p_rr[j].wt = p_rr[j].tat - p_rr[j].bt;
		}
		//calc avg
		float avg_wt=0,avg_tat=0;
		for(int i=0;i<n;i++)
		{
			avg_wt += p_rr[i].wt;
			avg_tat += p_rr[i].tat;
		}
		avg_wt /= n;
		avg_tat /= n;
		
		System.out.println("process_No.  Burst_Time  Waiting_Time  Turn_Around_Time\n");
		for(int i=0;i<n;i++)
		{
			System.out.println((i+1)+"\t\t"+p_rr[i].bt+"\t\t"+p_rr[i].wt+"\t\t"+p_rr[i].tat+"\n");
		}
		System.out.println("\n\nAvg_wt: "+avg_wt+"\nAvg_tat: "+avg_tat+"\n");
	}
	
	public void priority(){
		System.out.println("Enter number of processes: ");
		int n = in.nextInt();
		process[] p_pri = new process[n];
		for(int i=0;i<n;i++)
		{
			p_pri[i] = new process();
		}
		System.out.println("Enter Burst Time for each process: ");
		for(int i=0;i<n;i++)
		{
			p_pri[i].bt = in.nextInt();
			p_pri[i].wt = 0;
		}
		System.out.println("Enter Priority for each process: ");
		for(int i=0;i<n;i++)
		{
			p_pri[i].pri = in.nextInt();
		}
		//calc wt
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n-i-1;j++)
			{
				if(p_pri[j].pri<p_pri[j+1].pri)
				{
					process temp = p_pri[j];
					p_pri[j] = p_pri[j+1];
					p_pri[j+1] = temp;
				}
			}
		}
		p_pri[0].wt = 0;
		for(int i=1;i<n;i++)
		{
			p_pri[i].wt = p_pri[i-1].bt+p_pri[i-1].wt;
		}
		//calc tat
		for(int i=0;i<n;i++)
		{
			p_pri[i].tat = p_pri[i].bt+p_pri[i].wt;
		}
		//calc avg
		float avg_wt=0,avg_tat=0;
		for(int i=0;i<n;i++)
		{
			avg_wt += p_pri[i].wt;
			avg_tat += p_pri[i].tat;
		}
		avg_wt /= n;
		avg_tat /= n;
		
		System.out.println("process_No.  Burst_Time  Waiting_Time  Turn_Around_Time\n");
		for(int i=0;i<n;i++)
		{
			System.out.println((i+1)+"\t\t"+p_pri[i].bt+"\t\t"+p_pri[i].wt+"\t\t"+p_pri[i].tat+"\n");
		}
		System.out.println("\n\nAvg_wt: "+avg_wt+"\nAvg_tat: "+avg_tat+"\n");
	}
	
	public static void main(String[] arg)
	{
		shec ob = new shec();
		/*System.out.println("First Come First Serve without Arrival Time:\n");
		ob.fcfs();
		System.out.println("\n\nFirst Come First Serve with Arrival Time:\n");
		ob.fcfs_at();
		System.out.println("\n\nShortest Job First(Non-Preemptive):\n");
		ob.sjf_np();
		System.out.println("\n\nShortest Remaining Job First(Shortest Job First Preemptive):\n");
		ob.srtf();
		System.out.println("\n\nRound Robin:\n");
		ob.round_robin();
		*/System.out.println("\n\nPriority(Non-Preemptive):\n");
	        ob.priority();
		
	}
}
