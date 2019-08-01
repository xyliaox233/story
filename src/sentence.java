import java.util.ArrayList;

public class sentence {
    private WordTaker wt=new WordTaker();
    private WordReplacer wr=new WordReplacer();
    private probability prob=new probability();
    private String sub="",prdc="",obj="",tense="",advb="",atb_sub="",atbt_sub="",atb_obj="",atbt_obj="",intj="";//random
    private String SUB,OBJ="",ATBT_SUB="",ATBT_OBJ="",ATB_SUB="",ATB_OBJ="";//final-decided by message
    private String message;
    private int mode;
    //1-单句 2-复句 3-特殊句 4-普通句 5-陈述句 6-疑问句 7-感叹句 8-反问句 9-比较句 10-把 11-被 12-环境事件 13-角色事件
    ArrayList<String[]> save=new ArrayList<>();

    public String makeSentence(String message){
        this.message=message;
        // 1:单句     2：复句
        if(prob.aBoolean(70)){
            mode=1;
        }else mode=2;

        return choose(mode);
    }
    private String SimpleSentence(){
        // 3：特殊句    4：普通句
        if(prob.aBoolean(20)){
            mode=3;
        }else mode=4;
        return choose(mode);

    }
    private String Special(){
        String rt=wt.getSps();
        rt=wr.replaceAll(rt,null,null);
        return rt;
    }
    private String CompositeSentence(){
        String rt="";
        int mode=0;
        String[] former1={"一边","不但","因为","虽然","不管"};
        String[] latter1={"一边","而且","所以","但是","都"};
        String[] former2={"一旦","尽管","不论","如果"};
        String[] latter2={"就","都","都","就"};
        String[] former3={"因为","之所以"};
        String[] latter3={"所以","是因为"};
        switch (prob.anInt(3)){
            case 0:
                mode=prob.anInt(former1.length);
                getWords();
                MessageHandler();
                rt=ATB_SUB+ATBT_SUB+SUB+tense+former1[mode]+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"，";
                getWords();
                MessageHandler();
                rt=rt+latter1[mode]+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"。";
                break;
            case 1:
                mode=prob.anInt(former2.length);
                getWords();
                MessageHandler();
                rt=former2[mode]+ATB_SUB+ATBT_SUB+SUB+tense+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"，";
                getWords();
                MessageHandler();
                rt=rt+ATB_SUB+ATBT_SUB+SUB+latter2[mode]+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"。";
                break;
            case 2:
                mode=prob.anInt(former3.length);
                getWords();
                MessageHandler();
                rt=former3[mode]+ATB_SUB+ATBT_SUB+SUB+tense+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"，";
                getWords();
                MessageHandler();
                rt=rt+latter3[mode]+ATB_SUB+ATBT_SUB+SUB+tense+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"。";
                break;
        }
        return rt;
    }
    private String choose(int mode){
        String rt="";
        switch (mode){
            case 1:
                return SimpleSentence();
            case 2:
                return CompositeSentence();
            case 3:
                return Special();
            case 4:
                int probability=prob.anInt(100);
                int from,to=0;

                to=to+60;
                if(probability<to)rt=Declarative();

                from=to;to=to+5;
                if(from<=probability&&probability<to)rt=Exclamatory();

                from=to;to=to+5;
                if (from<=probability&&probability<to)rt=Question();

                from=to;to=to+3;
                if(from<=probability&&probability<to)rt=Ask_Back();

                from=to;to=to+7;
                if(from<=probability&&probability<to)rt= Compare();

                from=to;to=to+10;
                if (from<=probability&&probability<to)rt=Ba();

                from=to;to=to+10;
                if(from<=probability&&probability<to)rt= Bei();

                return rt;
        }
        return null;
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

        advb="";
        if(prob.aBoolean(40)) advb=wt.getConst("advb").getVal();

        atb_sub="";
        if(prob.aBoolean(30)) atb_sub=wt.getConst("atb").getVal();
        atbt_sub="";
        if(prob.aBoolean(40))atbt_sub=atbt_sub+wt.getConst("atbt").getVal();
        atb_obj="";
        if(prob.aBoolean(30)) atb_obj=wt.getConst("atb").getVal();
        atbt_obj="";
        if(prob.aBoolean(40))atbt_obj=wt.getConst("atbt").getVal();

        intj="";
        if(prob.aBoolean(5)) intj=wt.getConst("intj").getVal();
    }

    private void MessageHandler(){
        SUB=sub;OBJ=obj;ATBT_OBJ=atbt_obj;ATBT_SUB=atbt_sub;ATB_OBJ=atb_obj;ATB_SUB=atb_sub;
        String isChara_sub="", isChara_obj="";

        if(OBJ.equals("")){
            ATB_OBJ="";ATBT_OBJ="";
        }
        if(!message.equals("default")){
            String[] strings=message.split(":");
            switch (strings[0]){
                case "sub":
                    SUB=strings[1];
                    if(strings[2].equals("chara")){
                        ATB_SUB="";
                        isChara_sub="chara";
                    }
                    break;
                case "obj":
                    OBJ=strings[1];
                    if(strings[2].equals("chara")){
                        ATB_OBJ="";
                        isChara_obj="chara";
                    }
                    break;
            }
        }

        String[] sub_obj={SUB,isChara_sub,OBJ,isChara_obj};
        save.add(0,sub_obj);
    }

    private String Declarative(){
        mode=5;
        getWords();
        MessageHandler();
        return ATB_SUB+ATBT_SUB+SUB+tense+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+intj+"。";
    }
    private String Question(){
        mode=6;
        String rt="";
        getWords();
        MessageHandler();
        int mode=prob.anInt(8);
        switch (mode){
            case 0:
                rt=ATB_SUB+ATBT_SUB+SUB+tense+"在哪里"+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"呢？";
                break;
            case 1:
                rt="是什么"+tense+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"呢？";
                break;
            case 2:
                rt=ATB_SUB+ATBT_SUB+SUB+tense+advb+prdc+"什么呢？";
                break;
            case 3:
                rt=ATB_SUB+ATBT_SUB+SUB+tense+"是怎么"+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"的？";
                break;
            case 4:
                rt="为什么"+ATB_SUB+ATBT_SUB+SUB+tense+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"？";
                break;
            case 5:
                rt=ATB_SUB+ATBT_SUB+SUB+"什么时候"+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"呢？";
                break;
            case 6:
                rt="多少"+ATBT_SUB+SUB+tense+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"？";
                break;
            case 7:
                rt=ATB_SUB+ATBT_SUB+SUB+tense+advb+prdc+"多少"+ATBT_OBJ+OBJ+"？";
                break;
        }
        return rt;
    }
    private String Exclamatory(){
        mode=7;
        getWords();
        MessageHandler();
        String rt="";
        String[] strings2={"。","！","！！","！！！"};
        switch (prob.anInt(3)){
            case 0:
                if(prob.aBoolean(50)) {
                    if(prob.aBoolean(50))rt=rt+"这";
                    rt=rt+OBJ+"太"+delete_de(ATBT_OBJ)+"了";
                }
                else {
                    String[] strings1={"真","好"};
                    if(prob.aBoolean(50)){
                        rt=rt+"这";
                        if(prob.aBoolean(50))rt=rt+ATB_OBJ;
                    }
                    rt=rt+ATBT_OBJ+OBJ+strings1[prob.anInt(strings1.length)]+delete_de(ATBT_SUB)+intj+strings2[prob.anInt(strings2.length)];
                }
                break;
            case 1:
                rt=SUB+intj+strings2[prob.anInt(strings2.length)];
                break;
            case 2:
                rt=intj+strings2[prob.anInt(strings2.length)];
                break;
        }
        return rt;
    }
    private String Ask_Back(){
        mode=8;
        getWords();
        MessageHandler();
        String rt="";
        int mode=prob.anInt(2);
        switch (mode){
            case 0:
                rt=ATB_SUB+ATBT_SUB+SUB+"难道"+tense+"不"+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"吗？";
                break;
            case 1:
                rt=ATB_SUB+ATBT_SUB+SUB+"难道"+tense+"没有"+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"吗？";
                break;
            case 2:
                rt=ATB_SUB+ATBT_SUB+SUB+"怎么就"+tense+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"了？";
                break;
            case 3:
                rt=ATB_SUB+ATBT_SUB+SUB+tense+"不是没"+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"吗？";
                break;
        }
        return rt;
    }
    private String Compare(){
        mode=9;
        String rt="";
        getWords();
        MessageHandler();
        int mode=prob.anInt(5);
        switch (mode){
            case 0:
                rt=ATB_SUB+SUB+"比"+ATBT_OBJ+OBJ+delete_de(wt.getConst("atbt").getVal());
                break;
            case 1:
                rt=ATB_SUB+SUB+"不比"+ATBT_OBJ+OBJ+delete_de(wt.getConst("atbt").getVal());
                break;
            case 2:
                rt=ATB_SUB+SUB+"就像"+ATBT_OBJ+OBJ+"一样"+delete_de(wt.getConst("atbt").getVal());
                break;
            case 3:
                rt=ATB_SUB+SUB+"还不如"+ATBT_OBJ+OBJ+delete_de(wt.getConst("atbt").getVal());
                break;
            case 4:
                rt=ATB_SUB+SUB+"和"+ATBT_OBJ+OBJ+"一样"+delete_de(wt.getConst("atbt").getVal());
                break;
        }
        return rt;
    }
    private String Ba(){
        mode=10;
        getWords();
        MessageHandler();
        String rt=ATB_SUB+ATBT_SUB+SUB+tense+advb+"把"+ATB_OBJ+ATBT_OBJ+OBJ+prdc+"。";
        return rt;
    }
    private String Bei(){
        mode=11;
        getWords();
        MessageHandler();
        String rt=ATB_SUB+ATBT_SUB+SUB+tense+"被"+ATB_OBJ+ATBT_OBJ+OBJ+advb+prdc+"。";
        return rt;
    }

    public String makeEnvSentence(String env,ArrayList<String> chara){
        mode=12;
        String string=wt.getEnvEvent(env,chara.size());
        return wr.replaceAll(string,chara,env);
    }
    public String makeCharaSentence(String chara){
        mode=13;
        String string=wt.getCharaEvent(chara);
        return wr.replaceAll(string,null,null);
    }

    private String delete_de(String str){
        int length=str.length();
        return str.substring(0,length-2);
    }

    public String getSUB() {
        return SUB;
    }

    public String getOBJ() {
        return OBJ;
    }

    public int getMode() {
        return mode;
    }
}
