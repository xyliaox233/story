import java.util.ArrayList;

public class WordReplacer {
    private WordTaker wt=new WordTaker();
    private probability pro=new probability();
    private String[] constnames1={"\\(advb\\)","\\(atb\\)","\\(atbt\\)","\\(intj\\)","\\(sub\\)","\\(obj\\)","\\(vt\\)","\\(v\\)","\\(say\\)","\\(env\\)","\\(x\\)","\\(npc\\)"};
    private String[] constnames2={"advb","atb","atbt","intj","sub","obj","prdc","prdc","say","env","x","npc"};
    private String[] constnames3={"(advb)","(atb)","(atbt)","(intj)","(sub)","(obj)","(vt)","(v)","(say)","(env)","(x)","(npc)"};

    public String replaceAll(String str, ArrayList<String> cha,String env){
        sentence stc=new sentence();
        sentence_const sc;
        ArrayList<String> chara=new ArrayList<>();
        if(cha!=null){
            for(int j=0;j<cha.size();j++){
                chara.add(cha.get(j));
            }
        }
        while (check(str)){
            for(int i=0;i<constnames1.length;i++){
                if(str.indexOf(constnames3[i])==-1) continue;
                if(i<=2){
                    sc=wt.getConst(constnames2[i]);
                    String replaced=sc.getVal();
                    if(pro.aBoolean(50))replaced="";
                    str=str.replaceFirst(constnames1[i],replaced);
                }
                else if(i>=3&&i<=5){
                    sc=wt.getConst(constnames2[i]);
                    str=str.replaceFirst(constnames1[i],sc.getVal());
                }
                else{
                    switch (i){
                        case 6:
                            str=str.replaceFirst(constnames1[i],wt.getVt());
                            break;
                        case 7:
                            str=str.replaceFirst(constnames1[i],wt.getV());
                            break;
                        case 8:
                            str=str.replaceFirst(constnames1[i],stc.makeSentence("default"));
                            break;
                        case 9:
                            if(env==null) break;
                            str=str.replaceFirst(constnames1[i],wt.getEnv(env));
                            break;
                        case 10:
                            if(chara==null||chara.size()==0) break;
                            int replaced=pro.anInt(chara.size());
                            str=str.replaceFirst(constnames1[i],chara.get(replaced));
                            chara.remove(replaced);
                            break;
                        case 11:
                            if(env==null) break;
                            str=str.replaceFirst(constnames1[i],wt.getNpc(env));
                            break;
                    }
                }
            }
        }
        return str;
    }

    private boolean check(String str){
        for(int i=0;i<constnames3.length;i++){
            if(str.indexOf(constnames3[i])!=-1) return true;
        }
        return false;
    }
}

