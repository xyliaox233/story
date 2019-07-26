import java.util.Calendar;

public class sentence {
    public WordTaker wt;
    private String sentence;
    private probability prob;

    public String makeSentence(){
        int mode;
        // 1:µ•æ‰     2£∫∏¥æ‰
        if(prob.aBoolean(70)){
            mode=1;
        }else mode=2;

        return sentence=choose(mode);
    }
    private String SimpleSentence(){
        int mode;
        // 3£∫Ãÿ ‚æ‰    4£∫∆’Õ®æ‰
        if(prob.aBoolean(20)){
            mode=3;
        }else mode=4;
        return choose(mode);

    }
    private String Special(){
        String rt="";
        return rt;
    }
    private String CompositeSentence(){
        String rt="";
        return rt;
    }
    private String choose(int mode){
        switch (mode){
            case 1:
                return SimpleSentence();
            case 2:
                return CompositeSentence();
            case 3:
                return Special();
            case 4:
                int mode_Common=prob.anInt(6);
                switch (mode_Common){
                    case 0:
                        return Declarative();
                    case 1:
                        return Question();
                    case 2:
                        return Exclamatory();
                    case 3:
                        return Ask_Back();
                    case 4:
                        return Ba();
                    case 5:
                        return Bei();
                }
            default:
                return null;
        }
    }
    private String Declarative(){
        String rt="";
        return rt;
    }
    private String Question(){
        String rt="";
        return rt;
    }
    private String Exclamatory(){
        String rt="";
        return rt;
    }
    private String Ask_Back(){
        String rt="";
        return rt;
    }
    private String Ba(){
        String rt="";
        return rt;
    }
    private String Bei(){
        String rt="";
        return rt;
    }
}
