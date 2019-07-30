import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class StoryCreator {
    private ArrayList<String> story=null;
    private sentence sentence=new sentence();
    private probability prob=new probability();
    private char number;
    private ArrayList<String>chara,event,env=null;
    private String[] charactors={"",""};

    public ArrayList<String> choose(int mode,char number){
        this.number=number;
        if (mode==0)default_scene();
        else special_scene();
        return story;
    }

    private void default_scene(){
    }
    private void special_scene(){
        String catalogue,event,chara,env;
        if(prob.aBoolean(60))catalogue="lib/scene/daily scene.txt";
        else catalogue="lib/scene/daily scene.txt";
        ArrayList<String> names_of_scene=fileHandler(catalogue);
        String name=names_of_scene.get(prob.anInt(names_of_scene.size()));
        event="lib/scene/"+name+"/"+number+".txt";
        chara="lib/scene/"+name+"/chara.txt";
        env="lib/scene/"+name+"/env.txt";

        this.event=fileHandler(event);
        this.chara=fileHandler(chara);
        this.env=fileHandler(env);

        int random1,random2;

        do{
            random1=prob.anInt(this.chara.size());
            random2=prob.anInt(this.chara.size());
        }while (random1!=random2);

        if(number>'0')charactors[0]=this.chara.get(random1);
        if(number>'1')charactors[1]=this.chara.get(random2);

        if(number=='0')story.add("今天，我来到了"+name+"。");
        else if (number=='1')story.add("今天，我在"+name+"碰到了"+charactors[0]+"。");
        else if (number=='2')story.add("今天，我在"+name+"碰到了"+charactors[0]+"和"+charactors[1]+"。");

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

}

