import java.util.Calendar;

public class sentence {
    private WordTaker wt;
    private String sentence;
    private probability prob;
    private String sub,prdc,obj,tense,advb,atbt,intj;

    public String makeSentence(){
        int mode;
        // 1:单句     2：复句
        if(prob.aBoolean(70)){
            mode=1;
        }else mode=2;

        return sentence=choose(mode);
    }
    private String SimpleSentence(){
        int mode;
        // 3：特殊句    4：普通句
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
    private void getWords(){
        sub=wt.getConst("sub").getVal();
        prdc p= ( prdc ) wt.getConst("prdc");
        prdc=p.getVal();
        if(p.isTrans()){
            obj=wt.getConst("obj").getVal();
        }else obj="";

        switch (p.getTense()){
            case 0:
                tense="曾经";
                if(!p.isSp()){
                    if(p.isDur())prdc=prdc+"着";
                    else {
                        String[] strings={"了","过"};
                        prdc=prdc+strings[prob.anInt(strings.length)];
                    }
                }
                break;
            case 2:
                tense="将";
                if(!p.isSp()){
                    if(p.isDur())prdc=prdc+"着";
                    else {
                        String[] strings={"会","要"};
                        tense=tense+strings[prob.anInt(strings.length)];
                    }
                }
                break;
            case 1:
                if(p.isDur()){
                    tense="正在";
                    prdc=prdc+"着";
                }
        }

        if(prob.aBoolean(40)) advb=wt.getConst("advb").getVal();
        else advb="";
        if(prob.aBoolean(40)) {}


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
