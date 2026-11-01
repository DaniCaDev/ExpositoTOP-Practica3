package top;

/**
 * \brief Representación auxiliar de una ruta de TOPTW.
 *
 * Contiene datos básicos asociados a una ruta.
 */
public class TOPTWRoute {
    int predecessor;
    int succesor;
    int id;
    
    TOPTWRoute() {
        
    }
    
    TOPTWRoute(int pre, int succ, int id) {
        this.predecessor = pre;
        this.succesor = succ;
        this.id = id;
    }
    
    public int getPredeccesor() {
        return this.predecessor;
    }
    
    public int getSuccesor() {
        return this.succesor;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setPredeccesor(int pre) {
        this.predecessor = pre;
    }
    
    public void setSuccesor(int suc) {
        this.succesor = suc;
    }
    
    public void setId(int id) {
        this.id = id;
    }
}
