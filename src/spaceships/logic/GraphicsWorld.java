/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceships.logic;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 *
 * @author johancarlsson
 */
public class GraphicsWorld {
    List<IGraphicItem> allGraphicItems;
    
    public GraphicsWorld()
    {
        allGraphicItems = new ArrayList<IGraphicItem>();
    }
    
    public List<IGraphicItem> getGraphicItems(){
        return allGraphicItems;
    }
    
    public void addGraphicItem(IGraphicItem graphicItem){
        allGraphicItems.add(graphicItem);
    }
    
    public void removeGraphicItem(IGraphicItem graphicItem) {
        allGraphicItems.remove(graphicItem);
    }
    
}
