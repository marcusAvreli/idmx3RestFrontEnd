package idmx3RestFrontEnd;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import idmx3RestFrontEnd.view.DefaultViewContainer;

public class MyDoggyView extends DefaultViewContainer{
	public static final String ID = "MyDoggyViewID";
	public MyDoggyView(){
		super(ID,new JPanel());
	}
}