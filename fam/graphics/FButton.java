package org.architecturemining.fam.graphics;

import javax.swing.JButton;

import org.architecturemining.fam.model.Feature;

/**Extension of a JButton so feature can get stored in a buttons object.
 * 
 * @author Nick
 */
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