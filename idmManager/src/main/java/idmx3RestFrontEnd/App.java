package idmx3RestFrontEnd;

import idmx3RestFrontEnd.core.DefaultApplication;
import idmx3RestFrontEnd.core.DefaultApplicationLauncher;

public class App extends DefaultApplication
{
    public static void main( String[] args )
    {
    	 try {
 			new DefaultApplicationLauncher().execute(App.class);
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
    }
}
