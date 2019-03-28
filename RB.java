package rubronegra;

/** Classe que implementa operações basicas de uma árvore rubro-negra
 * @author Esther Barbara
 */
public class RB {
    private No raiz;
    
    /** Inicia a busca por um elemento
     * @param key a ser buscada
     * @return true se foi encontrado, false se não
     */
    public boolean busca(int key){
        return busca(key, raiz) != null;
    }
    
    /** Inicia a busca por um elemento a partir de um no
     * @param key a ser buscada
     * @param no o no que inicia uma sub-arvore a ser buscada
     * @return true se foi encontrado, false se não
     */
    private No busca(int key, No no){
        if (no == null || no.getKey()==key){
            return no;
        }
        else if(key<no.getKey()){
            return busca(key, no.getLeft());
        }
        else {
            return busca(key, no.getRight());
        }
    }
    
    public void insere(int key){
        insere(key, raiz, null, null, new RefInt(1));
    }
    private void insere(int key, No no, No pai, No avo, RefInt a){
        if(no==null){
            if(raiz==null){
                raiz = new No(key, No.Cor.NEGRO);
            }
            else if(key<pai.getKey()){
                pai.setLeft(new No(key, No.Cor.RUBRO));
            }
            else if(key>pai.getKey()){
                pai.setRight(new No(key, No.Cor.RUBRO));
            }
        }
        else if(key!=pai.getKey()){
            No prox;
            if(key<pai.getKey())
                prox = no.getLeft();
            else
                prox = no.getRight();
            insere(key, prox, no, pai, a);
            if(a.get()==1){
                rotacao(prox, no, pai, avo, a);
            }
            else if(a.get()==0){
                a.set(1);
            }
        }
    }
    
    private void rotacao(No no, No pai, No avo, No bisa, RefInt a){
        a.set(2);
        if(pai.isRubro()) {
            No tio = avo.getLeft();
            if(avo.getLeft() == pai){
                tio = avo.getRight();
            }
            if(tio.isRubro()){
                pai.setNegro();
                tio.setNegro();
                avo.setRubro();
                a.set(0);
            }
            else{
                avo.setRubro();
                if(no == pai.getLeft()){
                    if(pai == avo.getLeft()){
                        rotacaoDireita(bisa, bisa.getRight() == avo);
                        pai.setNegro();
                        avo.setRubro();
                    }
                    else{
                        rotacaoDuplaEsquerda(bisa, bisa.getRight() == avo);
                        no.setNegro();
                        avo.setRubro();
                    }
                }
                else{
                    if(pai == avo.getLeft()){
                        rotacaoDuplaDireita(bisa, bisa.getRight() == avo);
                        no.setNegro();
                        avo.setRubro();
                    }
                    else{
                        rotacaoEsquerda(bisa, bisa.getRight() == avo);
                        pai.setNegro();
                        avo.setRubro();
                    }
                }
            }
        }
        raiz.setNegro();
    }
    
    private void rotacaoDireita(No Pai, boolean isRightNode){
        No toUp, toDown;
        if(isRightNode){
            toDown = Pai.getRight();
            Pai.setRight(toDown.getLeft());
        }
        else{
            toDown = Pai.getLeft();
            Pai.setLeft(toDown.getLeft());
        }
        toUp = toDown.getLeft();

        toDown.setLeft(toUp.getRight());
        toUp.setRight(toDown);
        
    }
    private void rotacaoEsquerda(No Pai, boolean isRightNode){
        No toUp, toDown;
        if(isRightNode){
            toDown = Pai.getRight();
            Pai.setRight(toDown.getRight());
        }
        else{
            toDown = Pai.getLeft();
            Pai.setLeft(toDown.getRight());
        }
        toUp = toDown.getRight();
        
        toDown.setRight(toUp.getLeft());
        toUp.setLeft(toDown);
    }
    private void rotacaoDuplaDireita(No Pai, boolean isRightNode){
        No toDown, toUp;
        if(isRightNode){
            toDown = Pai.getRight();
            Pai.setRight(toDown.getLeft().getRight());
        }
        else{
            toDown = Pai.getLeft();
            Pai.setLeft(toDown.getLeft().getRight());
        }
        toUp = toDown.getLeft().getRight();
        
        toDown.getLeft().setRight(toUp.getLeft());
        toUp.setLeft(toDown.getLeft());
        
        toDown.setLeft(toUp.getRight());
        toUp.setRight(toDown);
    }
    private void rotacaoDuplaEsquerda(No Pai, boolean isRightNode){
        No toDown, toUp;
        if(isRightNode){
            toDown = Pai.getRight();
            Pai.setRight(toDown.getRight().getLeft());
        }
        else{
            toDown = Pai.getLeft();
            Pai.setLeft(toDown.getRight().getLeft());
        }
        toUp = toDown.getRight().getLeft();
        
        toDown.getRight().setLeft(toUp.getRight());
        toUp.setRight(toDown.getRight());
        
        toDown.setRight(toUp.getLeft());
        toUp.setLeft(toDown);
    }
    
    private void remove(int key){
        No no = busca(key, raiz);
        if (no != null){
            if (no.getLeft() != null && no.getRight() != null) {
                No rem = maxPai(no.getLeft());
                no.setKey(rem.getRight().getKey());
                rem.setRight(null);
            }
            else{
                if(no.isRubro())
                    removeNo(no.getKey());
            }
        }
    }
    private void removeNo(int key){
        
    }
    
    /** Pega o no com a maior key de uma arvore
     * @param  r  a raiz da sub-arvore
     * @return  o no com a key maxima
     */
    private No maxPai(No no){
        if(max(no)!=null){
            int key = max(no).getKey();
            return maxPai(no, key);
        }
        return null;
    }
    
    private No maxPai(No no, int key){
        if(no.getRight().getKey()==key){
            return no;
        }
        else{
            return busca(key, no.getRight());
        }
    }
    
    private No max(No r){
        if (r!=null) {
            if (r.getRight()==null)
                return r;
            else
                return max(r.getRight());
        }
        return null;
    }
}
