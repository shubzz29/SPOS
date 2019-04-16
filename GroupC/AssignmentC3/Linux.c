#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<string.h>

int main(int argc,char* argv[]){
	
	if(argc == 1){
		printf("\n Invalid Argument Count !! ");
		return 0;
	}
	
	if(strcmp(argv[1],"ps")==0){
	
		char *ps[] = {"ps",NULL};
		
		execvp(ps[0], ps);
	}//process status

	else if(strcmp(argv[1],"join")==0){

		if(argc != 4){

			printf("Invalid Argument Count !! ");
			return 0;
		}

		char *join[] = {"join", argv[2], argv[3], NULL};

		execvp(join[0], join);				
	}

	else if(strcmp(argv[1],"fork")==0){

		printf("PID of Parent Process is : %d \n", getpid());

		pid_t pid = fork();

		if(pid == 0){

			printf("PID of Child Forked Process : %d \n", getpid());		
			return 0;
		}
		
		printf("PID of Process at End : %d \n", getpid());
	}

	else if(strcmp(argv[1],"wait")==0){

		pid_t pid = fork();

		int LocalVariable = 0;
		int GlobalVariable = 0;
		int status = 0;

		if(pid == 0){

			LocalVariable++;
			GlobalVariable++;

			printf("PID of Child Process : %d \n", getpid());
			printf("Child Process :: \n LocalVariable : %d \n GlobalVariable : %d \n", LocalVariable, GlobalVariable);
		}

		else if(pid>0){
	
			printf("PID of Parent Process : %d \n", getpid());
			
			LocalVariable = 10;
			GlobalVariable = 20;

			printf("Parent Process :: \n LocalVariable : %d \n GlobalVariable : %d\n", LocalVariable, GlobalVariable);

			printf("Child Process Exit code %d ",WIFEXITED(status));

			wait(&status);		
		}
	}

	else if(strcmp(argv[1],"exec")==0){

			char *parameters[argc-1];

			for(int i=0; i<argc ; i++){

				parameters[i-2] = argv[i];
			}

			parameters[argc-2] = NULL;

			execvp(parameters[0],parameters);			
	}
	
	return 0;
}
