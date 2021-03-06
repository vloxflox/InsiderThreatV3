/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insiderthreatv3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eric
 */

//computador onde as observações estão sendo feitas (nível 3)
public class PcNode {
    
    //IMPLEMENTAR HISTOGRAMA AQUI
    protected String id;
    protected List<ActivityEntry> httpEntries;
    protected List<ActivityEntry> logonEntries;
    protected List<ActivityEntry> deviceEntries;

    
    //recebe uma entry e adiciona ao registro
    PcNode(ActivityEntry activityEntry) {
        id = activityEntry.getPc();
        
        httpEntries   = new ArrayList<ActivityEntry>();
        logonEntries  = new ArrayList<ActivityEntry>();
        deviceEntries = new ArrayList<ActivityEntry>();
        
    }
    
    
    public void addChild(ActivityEntry activityEntry){
        
        if( activityEntry.getOrigin().equals("http")) {
            httpEntries.add(activityEntry);
            return;
        }
        if( activityEntry.getOrigin().equals("logon")) {
            logonEntries.add(activityEntry);
            return;
        }
        if( activityEntry.getOrigin().equals("device")) {
            deviceEntries.add(activityEntry);
            return;
        }
    }
    
    public boolean contains(ActivityEntry activityEntry){
        return id.equals(activityEntry.getPc() );
    }
    

}

