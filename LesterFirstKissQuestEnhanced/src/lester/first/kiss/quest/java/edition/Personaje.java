package lester.first.kiss.quest.java.edition;

import java.io.Serializable;

public class Personaje implements Serializable {
    private int energia;
    private int dinero;
    private int nv_carisma;
    private static final long serialVersionUID = 1L;
    
    public Personaje(int energia, int dinero, int nv_carisma){
        this.energia = energia;
        this.dinero = dinero;
        this.nv_carisma = nv_carisma;
    }
    
    public int getEnergia(){
        return energia;
    }
    
    public void setEnergia(int energia){
        this.energia = energia;
    }
    
    public int getDinero(){
        return dinero;
    }
    
    public void setDinero(int dinero){
        this.dinero = dinero;
    }
    
    public int getNv_carisma(){
        return nv_carisma;
    }
    
    public void setNv_carisma(int nv_carisma){
        this.nv_carisma = nv_carisma;
    }
    
    public void sumEnergia(int num){
        int sum_energia = energia + num;

        if (sum_energia >= 100){
            setEnergia(100);
        }
        else{
            setEnergia(sum_energia);
        }
    }
    
    public void resEnergia(int num){
        int res_energia = energia - num;
        setEnergia(res_energia);
    }
    
    public void sumDinero(int num){
        int sum_dinero = dinero + num;
        setDinero(sum_dinero);
        
    }
    
    public void resDinero(int num){
        int res_dinero = dinero - num;
        setDinero(res_dinero);
    }
    
    public void sumCarisma(int num){
        int sum_carisma = nv_carisma + num;
        if (sum_carisma >= 10){
            setNv_carisma(10);
        }
        else{
            setNv_carisma(sum_carisma);
        }
    }
    
    public void resCarisma(int num){
        int res_carisma = nv_carisma - num;
        setNv_carisma(res_carisma);
    }
}
