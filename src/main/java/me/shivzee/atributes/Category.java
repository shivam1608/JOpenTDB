package me.shivzee.atributes;

import me.shivzee.Config;
import me.shivzee.io.IO;
import me.shivzee.util.Response;
import org.json.simple.JSONObject;

public enum Category {
    GENERAL_KNOWLEDGE("9" , "General Knowledge"),
    ENTERTAINMENT_BOOKS("10" , "Entertainment: Books"),
    ENTERTAINMENT_FILM("11" , "Entertainment: Film"),
    ENTERTAINMENT_MUSIC("12" , "Entertainment: Music"),
    ENTERTAINMENT_THEATERS("13" , "Entertainment: Musicals & Theatres"),
    ENTERTAINMENT_TELEVISION("14" , "Entertainment: Television"),
    ENTERTAINMENT_VIDEO_GAMES("15" , "Entertainment: Video Games"),
    ENTERTAINMENT_BOARD_GAMES("16" , "Entertainment: Board Games"),
    SCIENCE_AND_NATURE("17" , "Science & Nature"),
    SCIENCE_COMPUTERS("18" , "Science: Computers"),
    SCIENCE_MATHEMATICS("19" , "Science: Mathematics"),
    MYTHOLOGY("20" , "Mythology"),
    SPORTS("21" , "Sports"),
    GEOGRAPHY("22" , "Geography"),
    HISTORY("23" , "History"),
    POLITICS("24" , "Politics"),
    ART("25" , "Art"),
    CELEBRITIES("26" , "Celebrities"),
    ANIMALS("27" , "Animals"),
    VEHICLES("28" , "Vehicles"),
    ENTERTAINMENT_COMICS("29" , "Entertainment: Comics"),
    SCIENCE_GADGETS("30" , "Science: Gadgets"),
    ENTERTAINMENT_ANIME("31" , "Entertainment: Japanese Anime & Manga"),
    ENTERTAINMENT_ANIMATIONS("32" , "Entertainment: Cartoon & Animations"),
    RANDOM("0" , "");

    private final String id;
    private final String name;

    private Category(String id , String name){
        this.id = id;
        this.name = name;
    }

    public String getId(){
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public static Category findAny(String name){
        for(Category category : Category.values()){
            if (category.getName().equals(name)){
                return category;
            }
        }
        return Category.RANDOM;
    }

    public int getEasyCount(){
        try{
            Response response = IO.requestGET(Config.baseUrl + "/api_count.php?category=" + getId());
            JSONObject json = (JSONObject) Config.parser.parse(response.getResponse());
            json = (JSONObject) Config.parser.parse(json.get("category_question_count").toString());
            return Integer.parseInt(json.get("total_easy_question_count").toString());
        }catch (Exception e){
            System.out.println("|NO LOGGER| Exception "+e);
        }
        return 0;
    }

    public int getMediumCount(){
        try{
            Response response = IO.requestGET(Config.baseUrl + "/api_count.php?category=" + getId());
            JSONObject json = (JSONObject) Config.parser.parse(response.getResponse());
            json = (JSONObject) Config.parser.parse(json.get("category_question_count").toString());
            return Integer.parseInt(json.get("total_medium_question_count").toString());
        }catch (Exception e){
            System.out.println("|NO LOGGER| Exception "+e);
        }
        return 0;
    }

    public int getHardCount(){
        try{
            Response response = IO.requestGET(Config.baseUrl + "/api_count.php?category=" + getId());
            JSONObject json = (JSONObject) Config.parser.parse(response.getResponse());
            json = (JSONObject) Config.parser.parse(json.get("category_question_count").toString());
            return Integer.parseInt(json.get("total_hard_question_count").toString());
        }catch (Exception e){
            System.out.println("|NO LOGGER| Exception "+e);
        }
        return 0;
    }

    public int getTotalCount(){
        try{
            Response response = IO.requestGET(Config.baseUrl + "/api_count.php?category=" + getId());
            JSONObject json = (JSONObject) Config.parser.parse(response.getResponse());
            json = (JSONObject) Config.parser.parse(json.get("category_question_count").toString());
            return Integer.parseInt(json.get("total_question_count").toString());
        }catch (Exception e){
            System.out.println("|NO LOGGER| Exception "+e);
        }
        return 0;
    }

}
