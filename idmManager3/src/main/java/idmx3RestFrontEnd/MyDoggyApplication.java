package idmx3RestFrontEnd;

import idmx3RestFrontEnd.annotation.View;
import idmx3RestFrontEnd.annotation.Views;
import idmx3RestFrontEnd.annotation.ViewsPerspective;
import idmx3RestFrontEnd.core.DefaultApplication;
import idmx3RestFrontEnd.view.perspective.PerspectiveConstraint;

//@ViewsPerspective(MyDoggyPerspective.class)
@Views(@View(type=MyDoggyView.class,position=PerspectiveConstraint.RIGHT))
public class MyDoggyApplication extends DefaultApplication{
	
	
	
}
