package idmx3RestFrontEnd;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import idmx3RestFrontEnd.view.DefaultViewContainer;
import idmx3RestFrontEnd.annotation.Controllers;
import idmx3RestFrontEnd.annotation.Controller;
import idmx3RestFrontEnd.annotation.Listener;
import idmx3RestFrontEnd.annotation.Listeners;
import idmx3RestFrontEnd.swing.builder.SwingBuilder;
import idmx3RestFrontEnd.swing.builder.layout.GridBagConstraintsBuilder;
import idmx3RestFrontEnd.swing.rowColumn.Report;


@Controllers({
	@Controller(type=MyDoggyController.class,pattern="report")
	
	
	
	
})
@Listeners({
	@Listener(
		type=MyDoggyListener.class,id="report"
	)
})
public class MyDoggyView extends DefaultViewContainer{
	public static final String ID = "MyDoggyViewID";
	public MyDoggyView(){
	
			super("SwingBuilderViewId",
				new SwingBuilder().layout(new BorderLayout()).component(
						new SwingBuilder().
						layout(new GridBagLayout()).
						 /* (1) label-component */
							label(new GridBagConstraintsBuilder().row(0).col(0).gridWidth(2).build()).
								setName("fromLabel").setText("From").swingBuilder().
							button(new GridBagConstraintsBuilder().
										row(6).col(0).gridWidth(3).anchor(GridBagConstraints.EAST).fill(GridBagConstraints.NONE).insets(20,0,0,0).build()).
									setName("customApplicationBtn").setText("Custom Application").setPreferredSize(new Dimension(180,25)).swingBuilder().
							button(new GridBagConstraintsBuilder().
										row(7).col(0).gridWidth(3).anchor(GridBagConstraints.EAST).fill(GridBagConstraints.NONE).insets(20,0,0,0).build()).
										setName("certification").setText("Certification").setPreferredSize(new Dimension(180,25)).swingBuilder().

							dynamicTable(Report.getInstance(),new GridBagConstraintsBuilder().
									row(8).col(0).gridWidth(3).anchor(GridBagConstraints.EAST).fill(GridBagConstraints.NONE).insets(20,0,0,0).build()).
									setName("report").setPreferredSize(new Dimension(25,25)).swingBuilder().
									
							getTarget()
								,JPanel.class,BorderLayout.NORTH).
							swingBuilder().setPreferredSize(new Dimension(400,0)).getTarget());
		
	}
}