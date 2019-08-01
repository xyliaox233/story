import java.util.ArrayList;

public class StoryCreator {
    private WordTaker wt=new WordTaker();
    private ArrayList<String> story=new ArrayList<>();
    private sentence sentence=new sentence();
    private probability prob=new probability();
    private boolean has_charactor=false;
    private int length;

    public ArrayList<String> choose(int mode,boolean has_charactor,int length){
        this.length=length;
        this.has_charactor=has_charactor;
        if (mode==0)default_scene();
        else special_scene();
        return story;
    }

    private void default_scene(){
    }
    private void special_scene(){
        String env,chara="";
        ArrayList<String> chara_list=new ArrayList<>();
        chara_list.add("我");
        env=wt.getEnv_name(prob.aBoolean(60));
        if(has_charactor){
            chara=wt.getChara(env);
            chara_list.add(chara);
        }

        if(!has_charactor){
            story.add("今天，我来到了"+env+"。");
        }
        else {
            story.add("今天，我在"+env+"遇见了"+chara+"。");
        }

        for (int i=0;i<length;i++){
            String main="";
            String[] sub_or_obj={"sub:","obj:"};
            int probability=prob.anInt(100);
            int from,to=0;
            to=to+60;
            if(probability<to){
                String message=sub_or_obj[prob.anInt(2)]+":"+chara_list.get(prob.anInt(chara_list.size()))+":chara";
                main=sentence.makeSentence(message);
            }
            from=to;to=to+20;
            if(from<=probability&&probability<to)main=sentence.makeCharaSentence(chara);
            from=to;to=to+20;
            if(from<=probability&&probability<to)main=sentence.makeEnvSentence(env,chara_list);
            story.add(main);

            boolean expand=prob.aBoolean(60);
            if(expand) {
                if(sentence.getMode()!=3&&sentence.getMode()!=7&&sentence.getMode()!=12&&sentence.getMode()!=13) {
                    for (int j = 0; j <= prob.anInt(2); j++) {
                        ArrayList<String> strings=new ArrayList<>();
                        if(sentence.getMode()==2){
                            strings.add(sentence.save.get(0)[0]+":"+sentence.save.get(0)[1]);
                            strings.add(sentence.save.get(1)[0]+":"+sentence.save.get(1)[1]);
                            if(!sentence.save.get(0)[1].equals(""))strings.add(sentence.save.get(0)[2]+":"+sentence.save.get(0)[3]);
                            if(!sentence.save.get(0)[1].equals(""))strings.add(sentence.save.get(1)[2]+":"+sentence.save.get(1)[3]);
                        }else {
                            strings.add(sentence.save.get(0)[0]);
                            if(!sentence.save.get(0)[1].equals(""))strings.add(sentence.save.get(0)[1]);
                        }
                        story.add(sentence.makeSentence(sub_or_obj[prob.anInt(2)]+strings.get(prob.anInt(strings.size()))));
                    }
                }
            }
        }

    }

}

