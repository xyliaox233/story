import java.io.*;
import java.util.ArrayList;

public class sentence{
    ArrayList<String>noun=new ArrayList<>();
    ArrayList<String>verb_t=new ArrayList<>();
    ArrayList<String>verb_i=new ArrayList<>();
    ArrayList<String>sigh_=new ArrayList<>();
    ArrayList<String> adj_= new ArrayList<>();
    ArrayList<String> adv_= new ArrayList<>();
    ArrayList<String> used_n = new ArrayList<>();
    ArrayList<String> used_v_t = new ArrayList<>();
    ArrayList<String> used_v_i = new ArrayList<>();
    public static void main(String[] args) {
        new sentence().go();
    }
    public void go(){
        fileHandler(noun,"名词.txt");
        fileHandler(verb_t,"及物动词.txt");
        fileHandler(verb_i,"不及物动词.txt");
        fileHandler(sigh_,"语气词.txt");
        fileHandler(adj_,"形容词.txt");
        fileHandler(adv_,"副词.txt");
        for (int i=0;i<30;i++) {
            String obj;
            String verb;
            String sub;
            String adj_obj;
            String adj_sub;
            String adv;
            String sigh;
            if(random_boolean(60)){
                adj_obj=adj_.get(random_index(adj_.size()));
            }else adj_obj="";
            if(random_boolean(50)) {
                obj=noun.get(random_index(noun.size()));
                used_n.add(obj);
            }else {obj="我";if(random_boolean(50))adj_obj="";}
            if(random_boolean(75)){
                verb=verb_t.get(random_index(verb_t.size()));
                used_v_t.add(verb);
                if(random_boolean(60)){
                    adj_sub=adj_.get(random_index(adj_.size()));
                }else adj_sub="";
                sub=noun.get(random_index(noun.size()));
                used_n.add(sub);
            }else {
                verb=verb_i.get(random_index(verb_i.size()));
                used_v_i.add(verb);
                sub="";
                adj_sub="";
            }
            if(random_boolean(60)&&!verb.contains("是")){
                adv=adv_.get(random_index(adv_.size()));
            }else adv="";
            if(random_boolean(40)&&!verb.contains("是")){
                verb=verb+"着";
            }
            if(random_boolean(30)){
                sigh=sigh_.get(random_index(sigh_.size()));
            }else sigh="";
            System.out.println(adj_obj+obj+adv+verb+adj_sub+sub+sigh+"。");
            if(random_boolean(5)){
                System.out.println("真是好啊。");
            }
            if(random_boolean(5)){
                System.out.println("我的天啊。");
            }
        }
    }
    public void fileHandler(ArrayList<String> list,String path){
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
    }
    public boolean random_boolean(int percentage){
        return Math.random()*100<=percentage;
    }
    public int random_index(int size){
        return (int)(Math.ceil(Math.random()*(size-1)));
    }
}
