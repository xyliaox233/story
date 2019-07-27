public class sentence {
    private WordTaker wt;
    private String sentence;
    private probability prob;
    private String charactor_sub,charactor_obj,sub,prdc,obj,tense,advb,atbt_sub,atbt_sub_charactor,atbt_obj,atbt_obj_charactor,intj="";//random
    private String SUB,OBJ,ATBT_SUB,ATBT_OBJ="";//final-decided by message
    private String message;

    public String makeSentence(String message){
        this.message=message;
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
                if(prob.aBoolean(50))tense="曾经";
                else tense="";
                if(!p.isSp()){
                    if(p.isDur())prdc=prdc+"着";
                    else {
                        String[] strings={"了","过"};
                        prdc=prdc+strings[prob.anInt(strings.length)];
                    }
                }
                break;
            case 2:
                if(prob.aBoolean(50))tense="将";
                else tense="";
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
                    if(prob.aBoolean(50))tense="正在";
                    else tense="";
                    prdc=prdc+"着";
                }
        }

        if(prob.aBoolean(40)) advb=wt.getConst("advb").getVal();
        else advb="";

        atbt_sub="";
        if(prob.aBoolean(30)) atbt_sub=atbt_sub+wt.getConst("atb");
        if(prob.aBoolean(40))atbt_sub=atbt_sub+wt.getConst("atbt");
        atbt_obj="";
        if(prob.aBoolean(30)) atbt_obj=atbt_obj+wt.getConst("atb");
        if(prob.aBoolean(40))atbt_obj=atbt_obj+wt.getConst("atbt");
        atbt_sub_charactor="";
        if(prob.aBoolean(40)) atbt_sub_charactor=atbt_sub_charactor+wt.getConst("atbt");
        atbt_obj_charactor="";
        if(prob.aBoolean(40)) atbt_obj_charactor=atbt_obj_charactor+wt.getConst("atbt");

        if(prob.aBoolean(5)) intj=wt.getConst("intj").getVal();
        else intj="";
    }

    private void MessageHandler(){

    }

    private String Declarative(){
        String rt="";
        getWords();
        MessageHandler();
        rt=ATBT_SUB+SUB+tense+advb+prdc+ATBT_OBJ+OBJ+intj+"。";
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
