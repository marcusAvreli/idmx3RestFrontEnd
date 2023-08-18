package idmx3RestFrontEnd;

import idmx3RestFrontEnd.core.DefaultApplicationLauncher;

public class App 
{
    public static void main( String[] args )
    {
    	 try {
 			new DefaultApplicationLauncher().execute(MyDoggyApplication.class);
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    }
}
