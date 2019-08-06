import java.util.ArrayList;

public class WordReplacer {
    private WordTaker wt=new WordTaker();
    private probability pro=new probability();
    private String[] constnames1={"\\("+"advb"+"\\)","\\("+"atb"+"\\)","\\("+"atbt"+"\\)","\\)"+"intj"+"\\)","\\("+"sub"+"\\)","\\("+"obj"+"\\)","\\("+"prdc"+"\\)","\\("+"say"+"\\)","\\("+"env"+"\\)","\\("+"x"+"\\)"};
    private String[] constnames2={"advb","atb","atbt","intj","sub","obj","prdc","say","env","x"};

    public String replaceAll(String str, ArrayList<String> chara,String env){
        sentence stc=new sentence();
        sentence_const sc;
        while (check(str)){
            for(int i=0;i<constnames1.length;i++){
                if(i<=6){
                    sc=wt.getConst(constnames2[i]);
                    str=str.replaceFirst(constnames1[i],sc.getVal());
                }
                else{
                    switch (i){
                        case 7:
                            str=str.replaceFirst(constnames1[i],stc.makeSentence("default"));
                            break;
                        case 8:
                            if(env==null) break;
                            str=str.replaceFirst(constnames1[i],wt.getEnv(env));
                            break;
                        case 9:
                            if(chara==null) break;
                            int replaced=pro.anInt(chara.size());
                            str=str.replaceFirst(constnames1[i],chara.get(replaced));
                            chara.remove(replaced);
                            break;
                    }
                }
            }
        }
        return str;
    }

    private boolean check(String str){
        for(int i=0;i<constnames1.length;i++){
            if(str.indexOf(constnames1[i])!=-1) return true;
        }
        return false;
    }
}

