package xadrez;

public class Cheque {
    protected boolean cheque;
    protected int qtdeCheques;

    public Cheque(boolean cheque, int qtdeCheques) {
        this.cheque = cheque;
        this.qtdeCheques = qtdeCheques;
    }
    public Cheque() {
    }
    public boolean getCheque() {
        return cheque;
    }
    public void setCheque(boolean cheque) {
        this.cheque = cheque;
    }
    public int getQtdeCheques() {
        return qtdeCheques;
    }
    public void setQtdeCheques(int qtdeCheques) {
        this.qtdeCheques = qtdeCheques;
    }

    
}
