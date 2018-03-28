package org.architecturemining.fam.graphics;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;

import org.architecturemining.fam.graphics.Window.FamPanel;
import org.architecturemining.fam.graphics.Window.ListenForButton;
import org.architecturemining.fam.model.FunctionalArchitectureModel;

public class FamPanelComponents {
	
	ArrayList<FButton> featureButtonList;
	
	public void addFamPanelComponents(FunctionalArchitectureModel fam, FamPanel famPanel, Window window) {
			
			famPanel.setLayout(null);
			
			featureButtonList = new ArrayList<FButton>();
			
			int counter = 0;
			
			ListenForButton lForButton = window.new ListenForButton();
			
			for(int i = 0 ; i < fam.getListModules().size(); i++) {
				
				for(int j = 0; j < fam.getListModules().get(i).getFeatureList().size(); j++) {
		
					featureButtonList.add(new FButton());
					
					featureButtonList.get(counter).setBounds(
							 (int) fam.getListModules().get(i).getFeatureList().get(j).getOrigin().getX(),
										(int) fam.getListModules().get(i).getFeatureList().get(j).getOrigin().getY(), 
							fam.getListModules().get(i).getFeatureList().get(j).getWidth(), 
							fam.getListModules().get(i).getFeatureList().get(j).getHeight()
							);
					featureButtonList.get(counter).setFeature(fam.getListModules().get(i).getFeatureList().get(j));
					featureButtonList.get(counter).addActionListener(lForButton);
					featureButtonList.get(counter).setName(fam.getListModules().get(i).getFeatureList().get(j).getName());
					featureButtonList.get(counter).setText(fam.getListModules().get(i).getFeatureList().get(j).getName());
					famPanel.add(featureButtonList.get(counter));
				
					counter++;
				}
			}
			
			famPanel.setBorder( BorderFactory.createLineBorder(Color.black));
	}
	
	public ArrayList<FButton> getFeatureButtonList(){
		return featureButtonList;
	}
	
}