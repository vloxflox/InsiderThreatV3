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

public class DateNode{

    protected DateEntry dateEntry;
    private   Histogram histogram;
    
    
    //frame escolhido pelo user para analisar o banco de dados
    protected List<PcNode> analysisFrame;
    
    //frame escolhido pelo user para analisar o banco de dados   
    protected List<PcNode> after;
    protected List<PcNode> before;
    
    public DateNode(DateEntry dateEntry){
        this.dateEntry = dateEntry;
        histogram = new Histogram();
        
        analysisFrame = new ArrayList<PcNode>();
        before        = new ArrayList<PcNode>();
        after         = new ArrayList<PcNode>();
        
    }

    
    public void addChild(ActivityEntry activityEntry){
        //verifica se está antes ou depois do local de armazenamento
        int position = dateEntry.TimeLocation(activityEntry);
        
        PcNode node = new PcNode(activityEntry);
        histogram.update(activityEntry); 
        
        //checa se há redundância no nó correspondente antes de criar um nó correspondente ao PC da atividade
        
        if(position == 0){           
            if(!isRedundant(analysisFrame, activityEntry ) ){
                analysisFrame.add(node);
                
            }
            
        }else if(position == -1){
            if(!isRedundant(before, activityEntry ) ){
                before.add(node);
            }
            
        }else if(position == 1){
            if(!isRedundant(after, activityEntry ) ){
                after.add(node);
            }
            
        }
    }
    
    //já existe um node com o ID do pc pedido pelo ActovityEntry no tempo pedido
    public boolean isRedundant(List<PcNode> pcContainer,ActivityEntry activityEntry){
        for (PcNode current : pcContainer) {
            if(current.contains(activityEntry)){
                return true;
            }
        }
        return false;
    }
    
    public PcNode findSon(ActivityEntry activityEntry){
        
        //verifica se está antes ou depois do local de armazenamento
        int position = dateEntry.TimeLocation(activityEntry);
        
        //checa se há redundância no nó correspondente antes de criar um nó correspondente ao PC da atividade
        
        if(position == 0){
            return findFrameSon(analysisFrame, activityEntry );
            
        }else if(position == -1){
            return findFrameSon(before       , activityEntry );
            
        }else if(position == 1){
            return findFrameSon(after        , activityEntry );
            
        }
        return null;
    }
    
    //encontra o nó dentro do time frame desejado
    private PcNode findFrameSon(List<PcNode> pcContainer,ActivityEntry activityEntry){
        for (PcNode current : pcContainer) {
            if(current.contains(activityEntry)){
                return current;
            }
        }
        return null;
    }
    



    public void show(){
        dateEntry.show();
        histogram.show();
    }
    
}