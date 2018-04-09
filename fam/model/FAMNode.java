package org.architecturemining.fam.model;

import java.awt.geom.Point2D;

/**Because Module and Feature both need the same kind of information and behavior the FAMnode
 * generalization has been introduced. 
 * 
 * @author Nick
 */
public abstract class FAMNode {
	
	private String name;
	private String id;
	private Point2D origin;
	private int width;
	private int height;	
	
	/**Constructors for a FAMnode
	 * @param id		id of the node
	 * @param name		name of the node
	 * @param origin	origin are the (x,y) coordinates of the node on th screen stored in an Point2D
	 * @param width		width determines how wide the node should appear on the screen
	 * @param height	height determines how high the node should appear on the screen
	 */
	public FAMNode()
	{
	}
	
	public FAMNode(String id, String name) 
	{
		setId(id);
		setName(name);
	}
	
	public FAMNode(Point2D.Double origin, int width, int height)
	{
		this.origin = origin;
		this.width = width;
		this.height = height;	
	}
	

	public FAMNode(String id, String name, Point2D origin, int width, int height)
	{
		this.id = id;
		this.name = name;
		this.origin = origin;
		this.width = width;
		this.height = height;	
	}
	
	//getters and setters
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getId()
	{
		return this.id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}

	public Point2D getOrigin() {
		return this.origin;
	}
	
	public void setOrigin(Point2D origin) {
		this.origin = origin;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
}
