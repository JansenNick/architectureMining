package org.architecturemining.fam.graphics;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;

import org.architecturemining.fam.graphics.Window.FamPanel;
import org.architecturemining.fam.graphics.Window.ListenForButton;
import org.architecturemining.fam.model.Feature;
import org.architecturemining.fam.model.FunctionalArchitectureModel;

/**Handles all logic regarding the components in the fam panel. Holds a list of FButtons to store all the features of the fam data model.
 * FButtons is an extension of a JButton, the only difference is that it can store an feature.
 * 
 * @author Nick
 *
 */
public class FamPanelComponents {
	
	ArrayList<FButton> featureButtonList;
	
	/**Adds all the elements of the fam data model to the fam panel. It does this by first looping through all modules stored in the FAM.
	 * And then looping through all the features stored in each module. For each feature a new FButton is created, of which the bounds,
	 * feature, actionListener, name and text are set. Each button is added to the fam panel.
	 * 
	 * @param fam		data model of the functional architecture model
	 * @param famPanel	panel to which the components need to be added
	 * @param window	top level JFrame(Window) to which the components are added, required of the action listeners.
	 */
	public void addFamPanelComponents(FunctionalArchitectureModel fam, FamPanel famPanel, Window window) {
			
			famPanel.setLayout(null);
			
			featureButtonList = new ArrayList<FButton>();
			
			int counter = 0;
			
			ListenForButton lForButton = window.new ListenForButton();
			
			//loop through modules
			for(int i = 0 ; i < fam.getListModules().size(); i++) {
				//loop through features
				for(int j = 0; j < fam.getListModules().get(i).getFeatureList().size(); j++) {
		
					featureButtonList.add(new FButton());
					
					Feature feature = fam.getListModules().get(i).getFeatureList().get(j);
					
					featureButtonList.get(counter).setBounds(
							(int) 	feature.getOrigin().getX(),
							(int) 	feature.getOrigin().getY(), 
									feature.getWidth(), 
									feature.getHeight()
							);
					featureButtonList.get(counter).setFeature(feature);
					featureButtonList.get(counter).addActionListener(lForButton);
					//adds text to button
					featureButtonList.get(counter).setText(feature.getId() + ": " + feature.getName());
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