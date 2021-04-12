public class player {
    String words = "";
    String name;
    int score = 0;

    public void addWord(String word){
        if(words.equals("")){
            words = words + word;
        }  
        else{
            words = words + ", " + word;
        }
    }

    public void setName(String n){
        name = n;
    }

    public String getName(){
        return name;
    }

    public String getWords(){
        return words;
    }
    
    public void addScore(){
        score++;
    }
    public int getScore(){
        return score;
    }
    
}
