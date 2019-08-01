import java.util.ArrayList;

public class sentence {
    private WordTaker wt=new WordTaker();
    private WordReplacer wr=new WordReplacer();
    private probability prob=new probability();
    private String sub="",prdc="",obj="",tense="",advb="",atb_sub="",atbt_sub="",atb_obj="",atbt_obj="",intj="";//random
    private String SUB,OBJ="",ATBT_SUB="",ATBT_OBJ="",ATB_SUB="",ATB_OBJ="";//final-decided by message
    private String message;
    private int mode;
    //1-���� 2-���� 3-����� 4-��ͨ�� 5-������ 6-���ʾ� 7-��̾�� 8-���ʾ� 9-�ȽϾ� 10-�� 11-�� 12-�����¼� 13-��ɫ�¼�
    ArrayList<String[]> save=new ArrayList<>();

    public String makeSentence(String message){
        this.message=message;
        // 1:����     2������
        if(prob.aBoolean(70)){
            mode=1;
        }else mode=2;

        return choose(mode);
    }
    private String SimpleSentence(){
        // 3�������    4����ͨ��
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
        String[] former1={"һ��","����","��Ϊ","��Ȼ","����"};
        String[] latter1={"һ��","����","����","����","��"};
        String[] former2={"һ��","����","����","���"};
        String[] latter2={"��","��","��","��"};
        String[] former3={"��Ϊ","֮����"};
        String[] latter3={"����","����Ϊ"};
        switch (prob.anInt(3)){
            case 0:
                mode=prob.anInt(former1.length);
                getWords();
                MessageHandler();
                rt=ATB_SUB+ATBT_SUB+SUB+tense+former1[mode]+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"��";
                getWords();
                MessageHandler();
                rt=rt+latter1[mode]+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"��";
                break;
            case 1:
                mode=prob.anInt(former2.length);
                getWords();
                MessageHandler();
                rt=former2[mode]+ATB_SUB+ATBT_SUB+SUB+tense+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"��";
                getWords();
                MessageHandler();
                rt=rt+ATB_SUB+ATBT_SUB+SUB+latter2[mode]+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"��";
                break;
            case 2:
                mode=prob.anInt(former3.length);
                getWords();
                MessageHandler();
                rt=former3[mode]+ATB_SUB+ATBT_SUB+SUB+tense+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"��";
                getWords();
                MessageHandler();
                rt=rt+latter3[mode]+ATB_SUB+ATBT_SUB+SUB+tense+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"��";
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
                if(prob.aBoolean(50))tense="����";
                else tense="";
                if(!p.isSp()){
                    if(p.isDur())prdc=prdc+"��";
                    else {
                        String[] strings={"��","��"};
                        prdc=prdc+strings[prob.anInt(strings.length)];
                    }
                }
                break;
            case 2:
                if(prob.aBoolean(50))tense="��";
                else tense="";
                if(!p.isSp()){
                    if(p.isDur())prdc=prdc+"��";
                    else {
                        String[] strings={"��","Ҫ"};
                        tense=tense+strings[prob.anInt(strings.length)];
                    }
                }
                break;
            case 1:
                if(p.isDur()){
                    if(prob.aBoolean(50))tense="����";
                    else tense="";
                    prdc=prdc+"��";
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
        return ATB_SUB+ATBT_SUB+SUB+tense+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+intj+"��";
    }
    private String Question(){
        mode=6;
        String rt="";
        getWords();
        MessageHandler();
        int mode=prob.anInt(8);
        switch (mode){
            case 0:
                rt=ATB_SUB+ATBT_SUB+SUB+tense+"������"+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"�أ�";
                break;
            case 1:
                rt="��ʲô"+tense+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"�أ�";
                break;
            case 2:
                rt=ATB_SUB+ATBT_SUB+SUB+tense+advb+prdc+"ʲô�أ�";
                break;
            case 3:
                rt=ATB_SUB+ATBT_SUB+SUB+tense+"����ô"+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"�ģ�";
                break;
            case 4:
                rt="Ϊʲô"+ATB_SUB+ATBT_SUB+SUB+tense+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"��";
                break;
            case 5:
                rt=ATB_SUB+ATBT_SUB+SUB+"ʲôʱ��"+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"�أ�";
                break;
            case 6:
                rt="����"+ATBT_SUB+SUB+tense+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"��";
                break;
            case 7:
                rt=ATB_SUB+ATBT_SUB+SUB+tense+advb+prdc+"����"+ATBT_OBJ+OBJ+"��";
                break;
        }
        return rt;
    }
    private String Exclamatory(){
        mode=7;
        getWords();
        MessageHandler();
        String rt="";
        String[] strings2={"��","��","����","������"};
        switch (prob.anInt(3)){
            case 0:
                if(prob.aBoolean(50)) {
                    if(prob.aBoolean(50))rt=rt+"��";
                    rt=rt+OBJ+"̫"+delete_de(ATBT_OBJ)+"��";
                }
                else {
                    String[] strings1={"��","��"};
                    if(prob.aBoolean(50)){
                        rt=rt+"��";
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
                rt=ATB_SUB+ATBT_SUB+SUB+"�ѵ�"+tense+"��"+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"��";
                break;
            case 1:
                rt=ATB_SUB+ATBT_SUB+SUB+"�ѵ�"+tense+"û��"+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"��";
                break;
            case 2:
                rt=ATB_SUB+ATBT_SUB+SUB+"��ô��"+tense+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"�ˣ�";
                break;
            case 3:
                rt=ATB_SUB+ATBT_SUB+SUB+tense+"����û"+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"��";
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
                rt=ATB_SUB+SUB+"��"+ATBT_OBJ+OBJ+delete_de(wt.getConst("atbt").getVal());
                break;
            case 1:
                rt=ATB_SUB+SUB+"����"+ATBT_OBJ+OBJ+delete_de(wt.getConst("atbt").getVal());
                break;
            case 2:
                rt=ATB_SUB+SUB+"����"+ATBT_OBJ+OBJ+"һ��"+delete_de(wt.getConst("atbt").getVal());
                break;
            case 3:
                rt=ATB_SUB+SUB+"������"+ATBT_OBJ+OBJ+delete_de(wt.getConst("atbt").getVal());
                break;
            case 4:
                rt=ATB_SUB+SUB+"��"+ATBT_OBJ+OBJ+"һ��"+delete_de(wt.getConst("atbt").getVal());
                break;
        }
        return rt;
    }
    private String Ba(){
        mode=10;
        getWords();
        MessageHandler();
        String rt=ATB_SUB+ATBT_SUB+SUB+tense+advb+"��"+ATB_OBJ+ATBT_OBJ+OBJ+prdc+"��";
        return rt;
    }
    private String Bei(){
        mode=11;
        getWords();
        MessageHandler();
        String rt=ATB_SUB+ATBT_SUB+SUB+tense+"��"+ATB_OBJ+ATBT_OBJ+OBJ+advb+prdc+"��";
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
