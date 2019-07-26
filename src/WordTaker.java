import java.io.*;
import java.util.*;

public class WordTaker {
    public sentence_const getConst(String mode){
        ArrayList<String> list =new ArrayList<>();
        probability pro=new probability();
        sentence_const res=null;
        prdc rs=null;
        switch (mode){
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
            case "int":
                list=fileHandler("lib/word/int.txt");
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
        res.setVal(val);
        return res;
    }

    private ArrayList<String> fileHandler(String path){
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

    public static void main(String[] args) {
        WordTaker wt=new WordTaker();
        sentence_const sc=wt.getConst("prdc");
        System.out.println(sc.getVal());
    }
}
