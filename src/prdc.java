public class prdc extends sentence_const{
    private boolean dur;//duration
    private int tense;//0 stands for pst, 1 stands for prs, 2 stands for ftr
    private boolean trans;//transitivity
    private boolean sp;

    public void setDur(boolean dur) {
        this.dur = dur;
    }

    public void setTrans(boolean trans) {
        this.trans = trans;
    }

    public void setTense(int tense) {
        this.tense = tense;
    }

    public int getTense() {
        return tense;
    }

    public boolean isDur() {
        return dur;
    }

    public boolean isTrans() {
        return trans;
    }

    public void setSp(boolean sp) {
        this.sp = sp;
    }

    public boolean isSp() {
        return sp;
    }
}
