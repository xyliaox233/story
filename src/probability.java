public class probability {
    public boolean aBoolean(int prob){
        double random=prob*Math.random();
        if(random<prob){
            return true;
        }else return false;
    }
    public int anInt(int range){
        double random=range*Math.random();
        return (int)random;
    }
}
