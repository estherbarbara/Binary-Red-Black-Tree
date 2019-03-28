package rubronegra;

/**
 * @author Esther Barbara
 */
public class No {
    private No left;
    private No right;
    private int key;
    
    public enum Cor{RUBRO, NEGRO, NN};
    private Cor cor;
    
    public No(){   
    }
    public No(int key, Cor cor){
        this.key = key;
        this.cor = cor;
    }
    
    boolean isRubro(){
        return cor == Cor.RUBRO;
    }
    boolean isNegro(){
        return cor == Cor.NEGRO;
    }
    boolean isCorNN(){
        return cor == Cor.NN;
    }
    public void setNegro(){
        cor = Cor.NEGRO;
    }
    public void setRubro(){
        cor = Cor.RUBRO;
    }
    public void setCorNN(){
        cor = Cor.NN;
    }
    public void setKey(int key){
        this.key = key;
    }
    public int getKey(){
        return key;
    }
    public void setLeft(No no){
        left = no;
    }
    public void setRight(No no){
        right = no;
    }

    public No getLeft() {
        return left;
    }

    public No getRight() {
        return right;
    }
}
