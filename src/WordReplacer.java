import java.util.ArrayList;

public class WordReplacer {
    private WordTaker wt=new WordTaker();
    private probability pro=new probability();
    private sentence stc=new sentence();
    private String[] constnames={"(advb)","(atb)","(atbt)","(intj)","(sub)","(obj)","(prdc)","(say)","(env)","(x)"};

    public String replaceAll(String str, ArrayList<String> chara,String env){
        sentence_const sc;
        while (check(str)){
            for(int i=0;i<constnames.length;i++){
                if(i<=6){
                    sc=wt.getConst(constnames[i]);
                    str=str.replaceFirst(constnames[i],sc.getVal());
                }
                else{
                    switch (i){
                        case 7:
                            str=str.replaceFirst(constnames[i],stc.makeSentence(null));
                            break;
                        case 8:
                            if(env==null) break;
                            str=str.replaceFirst(constnames[i],wt.getEnv(env));
                            break;
                        case 9:
                            if(chara==null) break;
                            int replaced=pro.anInt(chara.size());
                            str=str.replaceFirst(constnames[i],chara.get(replaced));
                            chara.remove(replaced);
                            break;
                    }
                }
            }
        }
        return str;
    }

    private boolean check(String str){
        for(int i=0;i<constnames.length;i++){
            if(str.indexOf(constnames[i])!=-1) return true;
        }
        return false;
    }
}

