package javaapplicationmanageimage;

public class Loader implements Runnable{
    private boolean isStop = false;
    private int a=0;
    
    @Override public void run(){          
        isStop = false;
        a=0;
        while(!isStop){
            System.out.println(a);
            try{
            Thread.sleep(1000);
            }catch(Exception e){}
            a++;
        }

        
    }    
    public void stop(){
        System.out.printf("Total time is %d seconds\n",a);
        isStop = true;      
    }
}
