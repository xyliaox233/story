import java.io.*;
import java.util.*;

public class WordTaker {
    private probability pro=new probability();

    public sentence_const getConst(String mode){
        ArrayList<String>list=new ArrayList<>();
        ArrayList<String>LIST=new ArrayList<>();
        sentence_const res=null;
        prdc rs=null;
        atb rt=null;

        switch (mode){
            case "atb":
                res=new atb();
                rt=(atb) res;
                list=fileHandler("lib/word/num.txt");
                LIST=fileHandler("lib/word/mea.txt");
                break;
            case "obj":
                list=fileHandler("lib/word/n.txt");
                res=new obj();
                break;
            case "sub":
                list=fileHandler("lib/word/n.txt");
                res=new sub();
                break;
            case "atbt":
                list=fileHandler("lib/word/adj.txt");
                res=new atbt();
                break;
            case "advb":
                list=fileHandler("lib/word/adv.txt");
                res=new advb();
                break;
            case "intj":
                list=fileHandler("lib/word/intj.txt");
                res=new intj();
                break;
            case "prdc":
                res=new prdc();
                rs=(prdc)res;
                int n=pro.anInt(5);
                rs.setTense(pro.anInt(3));
                switch (n){
                    case 0:
                        list=fileHandler("lib/word/v/sp.txt");
                        rs.setSp(true);
                        break;
                    case 1:
                        list=fileHandler("lib/word/v/dur/v.txt");
                        rs.setDur(true);
                        rs.setTrans(false);
                        break;
                    case 2:
                        list=fileHandler("lib/word/v/dur/vt.txt");
                        rs.setDur(true);
                        rs.setTrans(true);
                        break;
                    case 3:
                        list=fileHandler("lib/word/v/ndur/v.txt");
                        rs.setDur(false);
                        rs.setTrans(false);
                        break;
                    case 4:
                        list=fileHandler("lib/word/v/ndur/vt.txt");
                        rs.setDur(false);
                        rs.setTrans(true);
                        break;
                }
                break;
        }
        int size=list.size();
        String val=list.get(pro.anInt(size));
        if(mode.equals("prdc")){
            rs.setVal(val);
            return rs;
        }
        else if(mode.equals("atb")){
            int SIZE=LIST.size();
            rt.setNum(list.get(pro.anInt(size)));
            rt.setMea(LIST.get(pro.anInt(SIZE)));
            rt.setVal(rt.getNum()+rt.getMea());
            return rt;
        }
        res.setVal(val);
        return res;
    }

    public String getSps(){
        String path="lib/special/sentence.txt";
        ArrayList<String> list=fileHandler(path);
        return list.get(pro.anInt(list.size()));
    }

    public String getEnv_name(boolean daily){
        String path;
        if(daily) path="lib/scene/daily scene.txt";
        else path="lib/scene/non daily scene.txt";
        ArrayList<String> list=fileHandler(path);
        return list.get(pro.anInt(list.size()));
    }

    public String getEnv(String name){
        String path="lib/scene/"+name+"/env.txt";
        ArrayList<String> list=fileHandler(path);
        return list.get(pro.anInt(list.size()));
    }

    public String getEnvEvent(String name,int num){
        String path="lib/scene/"+name+"/"+num+".txt";
        ArrayList<String> list=fileHandler(path);
        return list.get(pro.anInt(list.size()));
    }

    public String getChara(String name){
        String path="lib/scene/"+name+"/chara.txt";
        ArrayList<String> list=fileHandler(path);
        return list.get(pro.anInt(list.size()));
    }

    public String getCharaEvent(String chara){
        String path="lib/character/"+chara+".txt";
        ArrayList<String> list=fileHandler(path);
        return list.get(pro.anInt(list.size()));
    }

    public ArrayList<String> fileHandler(String path){
        ArrayList<String> list=new ArrayList<>();
        try {
            File file = new File(path);
            BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(file),"gbk"));
            String string;
            while ((string=reader.readLine())!=null){
                list.add(string);
            }
            reader.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return list;
    }
}
