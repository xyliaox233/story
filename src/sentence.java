import java.util.ArrayList;

public class sentence {
    private WordTaker wt=new WordTaker();
    private WordReplacer wr=new WordReplacer();
    private probability prob=new probability();
    private String sub="",prdc="",obj="",tense="",advb="",atb_sub="",atbt_sub="",atb_obj="",atbt_obj="",intj="";//random
    private String SUB,OBJ="",ATBT_SUB="",ATBT_OBJ="",ATB_SUB="",ATB_OBJ="";//final-decided by message
    private String message="default";
    private int mode;
    //1-���� 2-���� 3-����� 4-��ͨ�� 5-������ 6-���ʾ� 7-��̾�� 8-���ʾ� 9-�ȽϾ� 10-�� 11-�� 12-�����¼� 13-��ɫ�¼� 14-��
    ArrayList<String[]> save=new ArrayList<>();

    public static void main(String[] args) {
        sentence sentence=new sentence();
        for(int i=0;i<=50;i++){
            System.out.println(sentence.makeSentence("default"));
        }
    }

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
        if(prob.aBoolean(10)){
            mode=3;
        }else mode=4;
        return choose(mode);

    }
    private String Special(){
        getWords();
        String rt=wt.getSps();
        rt=wr.replaceAll(rt,null,null);
        return rt;
    }
    private String CompositeSentence(){
        String rt="";
        switch (prob.anInt(5)){
            case 0://һ�ߡ���һ�ߡ���
                getWords();
                if(prdc.contains("��")||prdc.contains("��"))prdc=delete_de(prdc);
                rt=ATB_SUB+ATBT_SUB+SUB+tense+"һ��"+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"��";
                getWords();
                rt=rt+"һ��"+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"��";
                break;
            case 1://�����������ҡ�������������������
                String[] former1={"����","����"};
                String[] latter1={"����","��"};
                int index1=prob.anInt(former1.length);
                switch (prob.anInt(3)){
                    case 0:
                        getWords();MessageHandler();
                        if(tense.equals("����")||tense.contains("��"))tense="";
                        rt=ATB_SUB+ATBT_SUB+SUB+tense+former1[index1]+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"��";
                        getWords();MessageHandler();
                        rt=rt+latter1[index1]+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"��";
                        break;

                    case 1:
                        getWords();MessageHandler();
                        if(tense.equals("����")||tense.contains("��"))tense="";
                        rt=ATB_SUB+ATBT_SUB+SUB+tense+former1[index1]+"��"+wt.getConst("atbt").getVal()+"��"+latter1[index1]+"��"+wt.getConst("atbt").getVal()+"��";
                        break;
                    case 2:
                        getWords();MessageHandler();
                        if(tense.equals("����")||tense.contains("��"))tense="";
                        rt=ATB_SUB+ATBT_SUB+SUB+tense+former1[index1]+"��"+delete_de(wt.getConst("atbt").getVal())+"��"+latter1[index1]+"��"+delete_de(wt.getConst("atbt").getVal())+"��";
                        break;
                }
                break;
            case 2://�����Ƿ񡭡���Ӧ�á���
                switch (prob.anInt(2)) {
                    case 0:
                        getWords();
                        rt = "����" + ATB_SUB + ATBT_SUB +SUB+ "�Ƿ�" +advb + prdc + ATB_OBJ + ATBT_OBJ + OBJ + "��";
                        getWords();
                        rt=rt+ATB_SUB+ATBT_SUB+SUB+"��Ӧ��"+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"��";
                        break;
                    case 1:
                        getWords();
                        rt=ATB_SUB + ATBT_SUB +SUB+"�����Ƿ�"+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"��";
                        getWords();
                        rt=rt+"��Ӧ��"+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"��";
                        break;
                }
                break;
            case 3://һ�������͡�������������͡��������¡���Ҳ���ᡭ������ʹ����Ҳ���ᡭ��
                String[] former3={"һ��","���","����","��ʹ"};
                String[] latter3={"��","��","Ҳ����","Ҳ����"};
                int index3=prob.anInt(former3.length);
                switch (prob.anInt(2)){
                    case 0:
                        getWords();
                        rt=former3[index3]+ATB_SUB+ATBT_SUB+SUB+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"��";
                        getWords();
                        rt=rt+ATB_SUB+ATBT_SUB+SUB+latter3[index3]+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"��";
                        break;
                    case 1:
                        getWords();
                        rt=ATB_SUB+ATBT_SUB+SUB+former3[index3]+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"��";
                        getWords();
                        rt=rt+ATB_SUB+ATBT_SUB+SUB+latter3[index3]+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"��";
                        break;
                }
                break;
            case 4://��Ϊ�������ԡ�����֮���ԡ�������Ϊ����
                String[] former4={"��Ϊ","֮����"};
                String[] latter4={"����","����Ϊ"};
                int index4=prob.anInt(former4.length);
                getWords();
                rt = former4[index4] + ATB_SUB + ATBT_SUB + SUB + tense+advb + prdc + ATB_OBJ + ATBT_OBJ + OBJ + "��";
                getWords();
                rt=rt+latter4[index4]+ATB_SUB+ATBT_SUB+SUB+tense+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"��";
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

                to=to+35;
                if(probability<to)rt=Declarative();

                from=to;to=to+5;
                if(from<=probability&&probability<to)rt=Exclamatory();

                from=to;to=to+5;
                if (from<=probability&&probability<to)rt=Question();

                from=to;to=to+10;
                if(from<=probability&&probability<to)rt=Ask_Back();

                from=to;to=to+10;
                if(from<=probability&&probability<to)rt= Compare();

                from=to;to=to+10;
                if (from<=probability&&probability<to)rt=Ba();

                from=to;to=to+10;
                if(from<=probability&&probability<to)rt= Bei();

                from=to;to=to+15;
                if(from<=probability&&probability<to)rt= Sp();

                return rt;

        }
        return null;
    }
    private void getWords(){
        sub=wt.getConst("sub").getVal();
        prdc p= ( prdc ) wt.getConst("prdc");

        advb="";
        if(prob.aBoolean(40)) advb=wt.getConst("advb").getVal();

        atb_sub="";
        if(prob.aBoolean(15)) atb_sub=wt.getConst("atb").getVal();
        atbt_sub="";
        if(prob.aBoolean(40))atbt_sub=atbt_sub+wt.getConst("atbt").getVal();
        atb_obj="";
        if(prob.aBoolean(15)) atb_obj=wt.getConst("atb").getVal();
        atbt_obj="";
        if(prob.aBoolean(40))atbt_obj=wt.getConst("atbt").getVal();

        prdc=p.getVal();
        if(p.isTrans()){
            obj=wt.getConst("obj").getVal();
        }else obj="";
        if(p.isSp()){
            obj=wt.getConst("obj").getVal();
            advb="";
        }

        switch (p.getTense()){
            case 0:
                if(prob.aBoolean(30)){
                    String[] strings={"����","��ǰ"};
                    tense=strings[prob.anInt(2)];
                }
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
                if(prob.aBoolean(30))tense="��";
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
                    if(prob.aBoolean(30))tense="����";
                    else tense="";
                    prdc=prdc+"��";
                }
        }

        intj="";
        if(prob.aBoolean(15)) intj=wt.getConst("intj").getVal();
        MessageHandler();
    }

    private void MessageHandler(){
        SUB=sub;OBJ=obj;ATBT_OBJ=atbt_obj;ATBT_SUB=atbt_sub;ATB_OBJ=atb_obj;ATB_SUB=atb_sub;
        String isChara_sub="", isChara_obj="";

        if(OBJ.equals("")){
            ATB_OBJ="";ATBT_OBJ="";
        }
        if(!message.equals("default")){
            String[] strings=message.split(":");
        //    for (int i=0;i<strings.length;i++) System.out.print(strings[i]+"; ");
        //    System.out.println();
            switch (strings[0]){
                case "sub":
                    SUB=strings[1];
                    if(strings[strings.length-1].equals("chara")){
                        ATB_SUB="";
                        isChara_sub="chara";
                    }
                    break;
                case "obj":
                    if(!OBJ.equals("")&&!strings[1].equals(""))OBJ=strings[1];
                    if(strings[strings.length-1].equals("chara")){
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
        getWords();while (prdc.equals("��"))getWords();
        String rt="";
        String[] strings={"Ȼ��","����","����","��ô","����","�����Ļ�","����","��ʵ","����","Ҳ��"};
        if(prob.aBoolean(50))rt=strings[prob.anInt(strings.length)];
        return rt+ATB_SUB+ATBT_SUB+SUB+tense+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+intj+"��";
    }
    private String Question(){
        mode=6;
        String rt="";
        getWords();
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
    public String Exclamatory(){
        mode=7;
        getWords();
        String rt="";
        String[] strings2={"��","��","����","������"};
        if (prob.aBoolean(50)){
            this.intj=wt.getConst("intj").getVal();
        }
        switch (prob.anInt(3)){
            case 0:
                if(prob.aBoolean(50)) {
                    if(prob.aBoolean(50))rt=rt+"��";
                    rt=rt+SUB+"̫"+delete_de(wt.getConst("atbt").getVal())+"��"+strings2[prob.anInt(strings2.length)];
                }
                else {
                    String[] strings1={"��","��"};
                    if(prob.aBoolean(50)){
                        rt=rt+"��";
                        if(prob.aBoolean(50))rt=rt+wt.getConst("atbt").getVal();
                    }
                    rt=rt+ATBT_SUB+SUB+strings1[prob.anInt(strings1.length)]+delete_de(wt.getConst("atbt").getVal())+intj+strings2[prob.anInt(strings2.length)];
                }
                break;
            case 1:
                rt=ATBT_SUB+SUB+wt.getConst("intj").getVal()+strings2[prob.anInt(strings2.length)];
                break;
            case 2:
                rt=wt.getConst("intj").getVal()+strings2[prob.anInt(strings2.length)];
                break;
        }
        return rt;
    }
    private String Ask_Back(){
        mode=8;
        getWords();
        while (obj.equals(""))getWords();
        String rt="";
        int mode=prob.anInt(6);
        switch (mode){
            case 0:
                rt=ATB_SUB+ATBT_SUB+SUB+"�ѵ�����"+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"��";
                break;
            case 1:
                rt=ATB_SUB+ATBT_SUB+SUB+"�ѵ�û��"+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"��";
                break;
            case 2:
                rt=ATB_SUB+ATBT_SUB+SUB+"��ô��"+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"�ˣ�";
                break;
            case 3:
                rt=ATB_SUB+ATBT_SUB+SUB+"����û��"+advb+prdc+ATB_OBJ+ATBT_OBJ+OBJ+"��";
                break;
            case 4:
                if(prdc.equals("��"))prdc="";
                rt=ATB_SUB+ATBT_SUB+SUB+"�ѵ�����"+ATB_OBJ+ATBT_OBJ+OBJ+"��";
                break;
            case 5:
                if(prdc.equals("��"))prdc="";
                rt=ATB_SUB+ATBT_SUB+SUB+"�ѵ�����"+ATBT_OBJ+"��";
                break;
        }
        return rt;
    }
    private String Compare(){
        mode=9;
        String rt="";
        getWords();
        while (obj.equals(""))getWords();
        String[] strings={"Ȼ��","����","����","��ô","����","�����Ļ�","����","��ʵ","����","Ҳ��"};
        if(prob.aBoolean(40))rt=strings[prob.anInt(strings.length)];
        int mode=prob.anInt(5);
        switch (mode){
            case 0:
                rt=ATB_SUB+SUB+"��"+atbt_obj+obj+delete_de(wt.getConst("atbt").getVal())+"��";
                break;
            case 1:
                rt=ATB_SUB+SUB+"����"+atbt_obj+obj+delete_de(wt.getConst("atbt").getVal())+"��";
                break;
            case 2:
                rt=ATB_SUB+SUB+"����"+atbt_obj+obj+"һ��"+delete_de(wt.getConst("atbt").getVal())+"��";
                break;
            case 3:
                rt=ATB_SUB+SUB+"������"+atbt_obj+obj+delete_de(wt.getConst("atbt").getVal())+"��";
                break;
            case 4:
                rt=ATB_SUB+SUB+"��"+atbt_obj+obj+"һ��"+delete_de(wt.getConst("atbt").getVal())+"��";
                break;
        }
        return rt;
    }
    private String Ba(){
        mode=10;
        String rt="";
        getWords();
        while (obj.equals("")||prdc.equals("��"))getWords();
        String[] strings={"Ȼ��","����","����","��ô","����","�����Ļ�","����","��ʵ","����","Ҳ��"};
        if(prob.aBoolean(40))rt=strings[prob.anInt(strings.length)];
        rt=rt+ATB_SUB+ATBT_SUB+SUB+tense+advb+"��"+ATB_OBJ+ATBT_OBJ+OBJ+prdc+"��";
        return rt;
    }
    private String Bei(){
        mode=11;
        String rt="";
        getWords();
        while (obj.equals("")||prdc.equals("��")) getWords();
        String[] strings={"�����Ļ�","Ȼ��","����","����","��ô","����","����","��ʵ","����","Ҳ��"};
        if(prob.aBoolean(40))rt=strings[prob.anInt(strings.length)];
        rt=rt+ATB_SUB+ATBT_SUB+SUB+tense+"��"+ATB_OBJ+ATBT_OBJ+OBJ+advb+prdc+"��";
        return rt;
    }

    private String Sp(){
        mode=14;
        getWords();while (obj.equals(""))getWords();
        if(tense.equals("����"))tense="";
        String rt="";
        String[] strings={"Ȼ��","����","����","��ô","����","�����Ļ�","����","��ʵ","����","Ҳ��"};
        if(prob.aBoolean(40))rt=strings[prob.anInt(strings.length)];
        switch (prob.anInt(2)){
            case 0:
                rt=rt+ ATB_SUB+ATBT_SUB+SUB+tense+prdc+ATB_OBJ+ATBT_OBJ+OBJ+intj+"��";
                break;
            case 1:
                rt=rt+ ATB_SUB+ATBT_SUB+SUB+tense+prdc+ATBT_OBJ+intj+"��";
                break;
        }
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
        return str.substring(0,length-1);
    }


    public int getMode() {
        return mode;
    }
}
