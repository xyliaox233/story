import java.util.ArrayList;

public class StoryCreator {
    private ArrayList<String> story=null;
    private WordTaker wt=new WordTaker();
    private sentence sentence=new sentence();
    private probability prob=new probability();
    private char number;
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
        ArrayList<String> names_of_scene=wt.fileHandler(catalogue);
        String name=names_of_scene.get(prob.anInt(names_of_scene.size()));

        chara=wt.getChara(name);
        env=wt.getEnvEvent();

        int random1,random2;

        do{
            random1=prob.anInt(this.chara.size());
            random2=prob.anInt(this.chara.size());
        }while (random1==random2);

        if(number>'0')charactors[0]=this.chara.get(random1);
        if(number>'1')charactors[1]=this.chara.get(random2);

        if(number=='0')story.add("今天，我来到了"+name+"。");
        else if (number=='1')story.add("今天，我在"+name+"碰到了"+charactors[0]+"。");
        else if (number=='2')story.add("今天，我在"+name+"碰到了"+charactors[0]+"和"+charactors[1]+"。");

    }
}

