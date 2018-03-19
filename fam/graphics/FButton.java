package org.architecturemining.fam.graphics;

import javax.swing.JButton;

import org.architecturemining.fam.model.Feature;

@SuppressWarnings("serial")
class FButton extends JButton{
		
		Feature feature;

		public void setFeature(Feature f) {
			feature = f;
		}
		
		public Feature getFeature() {
			return feature;
		}
}