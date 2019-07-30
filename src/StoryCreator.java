import java.util.ArrayList;

public class StoryCreator {
    private WordTaker wt=new WordTaker();
    private ArrayList<String> story=null;
    private sentence sentence=new sentence();
    private probability prob=new probability();
    private boolean has_charactor=false;

    public ArrayList<String> choose(int mode,boolean has_charactor){
        this.has_charactor=has_charactor;
        if (mode==0)default_scene();
        else special_scene();
        return story;
    }

    private void default_scene(){
    }
    private void special_scene(){
        String env,chara="";
        env=wt.getEnv_name(prob.aBoolean(60));
        if(has_charactor)chara=wt.getChara(env);

        if(!has_charactor){
            story.add("今天，我来到了"+env+"。");
        }
        else {
            story.add("今天，我在"+env+"遇见了"+chara+"。");
        }

    }

}

