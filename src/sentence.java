import java.util.List;

public class sentence {
    private WordTaker wt;
    private String sentence;
    private probability prob;
    private String charactor_sub,charactor_obj,sub,prdc,obj,tense,advb,atb_sub,atbt_sub,atbt_sub_charactor,atb_obj,atbt_obj,atbt_obj_charactor,intj="";//random
    private String SUB,OBJ,ATBT_SUB,ATBT_OBJ,ATB_SUB,ATB_OBJ="";//final-decided by message
    private String message;

    public String makeSentence(String message){
        this.message=message;
        int mode;
        // 1:����     2������
        if(prob.aBoolean(70)){
            mode=1;
        }else mode=2;

        return sentence=choose(mode);
    }
    private String SimpleSentence(){
        int mode;
        // 3�������    4����ͨ��
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
                rt=rt+ATB_SUB+ATBT_SUB+SUB+tense+latter2[mode]+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"��";
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

        if(prob.aBoolean(40)) advb=wt.getConst("advb").getVal();
        else advb="";

        atb_sub="";
        if(prob.aBoolean(30)) atb_sub=wt.getConst("atb").getVal();
        atbt_sub="";
        if(prob.aBoolean(40))atbt_sub=atbt_sub+wt.getConst("atbt").getVal();
        atb_obj="";
        if(prob.aBoolean(30)) atbt_obj=wt.getConst("atb").getVal();
        atbt_obj="";
        if(prob.aBoolean(40))atbt_obj=wt.getConst("atbt").getVal();
        atbt_sub_charactor="";
        if(prob.aBoolean(40)) atbt_sub_charactor=wt.getConst("atbt").getVal();
        atbt_obj_charactor="";
        if(prob.aBoolean(40)) atbt_obj_charactor=wt.getConst("atbt").getVal();

        if(prob.aBoolean(5)) intj=wt.getConst("intj").getVal();
        else intj="";
    }

    private void MessageHandler(){

    }

    private String Declarative(){
        String rt="";
        getWords();
        MessageHandler();
        rt=ATB_SUB+ATBT_SUB+SUB+tense+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+intj+"��";
        return rt;
    }
    private String Question(){
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
        getWords();
        MessageHandler();
        String rt="";
        String[] strings2={"��","��","����","������"};
        switch (prob.anInt(3)){
            case 0:
                if(prob.aBoolean(50)) {
                    if(prob.aBoolean(50))rt=rt+"��";
                    rt=rt+OBJ+"̫"+ATBT_OBJ+"��";
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
    private String Ba(){
        getWords();
        MessageHandler();
        String rt=ATB_SUB+ATBT_SUB+SUB+tense+advb+"��"+ATB_OBJ+ATBT_OBJ+OBJ+prdc+"��";
        return rt;
    }
    private String Bei(){
        getWords();
        MessageHandler();
        String rt=ATB_SUB+ATBT_SUB+SUB+tense+"��"+ATB_OBJ+ATBT_OBJ+OBJ+advb+prdc+"��";
        return rt;
    }
    private String delete_de(String str){
        int length=str.length();
        return str.substring(0,length-2);
    }
}
