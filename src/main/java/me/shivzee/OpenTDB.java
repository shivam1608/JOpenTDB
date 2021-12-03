/**
 * @auther Shivzee
 * GitHub : Shivam1608
 * Discord : Shivam#8010
 * You're Free to use this code
 */


package me.shivzee;

import me.shivzee.atributes.Category;
import me.shivzee.atributes.Difficulty;
import me.shivzee.atributes.Encoding;
import me.shivzee.atributes.Type;
import me.shivzee.events.QuestionFetched;
import me.shivzee.exceptions.TokenNotFoundException;
import me.shivzee.io.IO;
import me.shivzee.util.Question;
import me.shivzee.util.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class to Wrap OpenTDB API https://opentdb.com
 */
public class OpenTDB {

    private String sessionToken;

    /**
     * Default Usage
     */
    public OpenTDB(){
        this.sessionToken = "";
    }

    /**
     * Constructor to use with Token
     * @param sessionToken Use Session Class to Create one Session.create();
     */
    public OpenTDB(String sessionToken){
        this.sessionToken = sessionToken.equals("")?"":"&token="+sessionToken;
    }

    /**
     * Set your custom Session Token Refer to OPENTDB Api docs to Know more
     * @param sessionToken The Session Token to Set
     */
    public void setSessionToken(String sessionToken){
        this.sessionToken = sessionToken.equals("")?"":"&token="+sessionToken;
    }

    /**
     * Get the Current Using Session Token
     * @return the current using session Token
     */
    public String getSessionToken(){
        return this.sessionToken.equals("")?"":this.sessionToken.replace("&token=" , "");
    }

    private Question questionUtility(JSONObject object){
        Category category = Category.findAny(object.get("category").toString());
        Type type = Type.valueOf(object.get("type").toString().toUpperCase());
        Difficulty difficulty = Difficulty.valueOf(object.get("difficulty").toString().toUpperCase());
        String question = object.get("question").toString();
        String answer = object.get("correct_answer").toString();
        JSONArray array = (JSONArray) object.get("incorrect_answers");
        List<String> options = new ArrayList<>();
        Object [] oArray = array.toArray();
        for(Object option : oArray){
            options.add(option.toString());
        }
        return new Question(category , type , difficulty , question , answer , options);
    }

    /**
     * (Synchronous) Get the Trivia Questions
     *
     * @param limit Limit to number of questions to fetch
     * @param category The Category of the Question Ex Category.SCIENCE
     * @param type The Type of Question Ex Type.BOOLEAN
     * @param encoding The Encoding in which Question is Returned Ex Encoding.BASE64
     * @param difficulty The Difficulty of the Question Ex Difficulty.HARD
     * @return The List of Questions For Trivia
     * @throws TokenNotFoundException When Token is Either Invalid or Empty
     */
    public List<Question> getTrivia(int limit , Category category , Type type , Encoding encoding , Difficulty difficulty) throws TokenNotFoundException{
        limit = Math.min(limit , 50);
        try{
            Response response = IO.requestGET(Config.baseUrl+"/api.php?amount=" + limit + "&category=" + category.getId() + "&difficulty=" + difficulty.getDifficulty() + "&type=" + type.getType() + "&encode=" + encoding.getEncoding());
            JSONObject jsonObject = (JSONObject) Config.parser.parse(response.getResponse());
            int responseCode = Integer.parseInt(jsonObject.get("response_code").toString());
            System.out.println(response.getResponse());
            switch (responseCode){
                case 3:{
                    throw new TokenNotFoundException("Invalid Token Specified");
                }
                case 4:{
                    throw new TokenNotFoundException("No More Questions Left For That Token");
                }
            }
            JSONArray array = (JSONArray) jsonObject.get("results");
            Object [] jsonArray = array.toArray();
            List<Question> questions = new ArrayList<>();
            for (Object object : jsonArray){
                questions.add(questionUtility((JSONObject) object));
            }

            return questions;

        }catch (ParseException parseException){
            System.out.println("|NO LOGGER| JSON Response Parse Error! " + parseException);
        }
        return Collections.emptyList();
    }

    /**
     * (Synchronous) Get the Trivia Questions
     *
     * @return 10 Questions For Trivia
     * @throws TokenNotFoundException When Token is Empty or Invalid
     */
    public List<Question> getTrivia() throws TokenNotFoundException {
        return getTrivia(10 , Category.RANDOM , Type.RANDOM , Encoding.DEFAULT , Difficulty.RANDOM);
    }

    /**
     * (Synchronous) Get the Trivia Questions
     *
     * @param limit the number of questions to fetch
     * @return Questions for Trivia Equal to Limit
     * @throws TokenNotFoundException When Token is Invalid or Empty
     */
    public List<Question> getTrivia(int limit) throws TokenNotFoundException {
        limit = Math.min(limit , 50);
        return getTrivia(limit , Category.RANDOM , Type.RANDOM , Encoding.DEFAULT , Difficulty.RANDOM);
    }

    /**
     * (Asynchronous) Get the Trivia Questions
     *
     * @param limit The Number Of Questions to Fetch
     * @param category The Category of the Question Ex Category.SCIENCE
     * @param type The Type of Question Ex Type.BOOLEAN
     * @param encoding The Encoding in which Question is Returned Ex Encoding.BASE64
     * @param difficulty The Difficulty of the Question Ex Difficulty.HARD
     * @param questionFetched The QuestionFetched Implementation
     *                        <code>
     *                        // Using Lambda
     *                        getTriviaAsync((questions)->{ for (Question question : questions) { question.getQuestion() } });
     *                        </code>
     */
    public void getTriviaAsync(int limit , Category category , Type type , Encoding encoding , Difficulty difficulty , QuestionFetched questionFetched){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    List<Question> trivia = getTrivia(limit, category, type, encoding, difficulty);
                    questionFetched.onQuestionFetched(trivia);
                } catch (TokenNotFoundException e) {
                    System.out.println("INVALID TOKEN FOUND!");
                }
                questionFetched.onQuestionFetched(Collections.emptyList());
            }
        }).start();
    }

    /**
     * (Asynchronous) Get the Trivia Questions
     * @param limit The Number of Questions to Fetch
     * @param questionFetched The QuestionFetched Implementation
     */
    public void getTriviaAsync(int limit , QuestionFetched questionFetched){
        getTriviaAsync(limit , Category.RANDOM , Type.RANDOM , Encoding.DEFAULT , Difficulty.RANDOM , questionFetched);
    }

    /**
     * (Asynchronous) Get the Trivia Questions
     * @param questionFetched The QuestionFetched Implementation
     */
    public void getTriviaAsync(QuestionFetched questionFetched){
        getTriviaAsync(10 , questionFetched);
    }

    /**
     * Returns 1 Question for Trivia (Yea its technically useless)
     * @return 1 Question for Trivia
     * @throws TokenNotFoundException When Token is Either Empty or Invalid
     */
    public Question quickTrivia() throws TokenNotFoundException {
        return getTrivia(1).get(0);
    }

    /**
     * Callbacks 1 Question for Trivia (Yes its technically more useless)
     * @param questionFetched The Question Fetched implementation
     */
    public void quickTriviaAsync(QuestionFetched questionFetched){
        getTriviaAsync(1 , questionFetched);
    }

    /**
     * Get the Total Number of Questions in the Server Database
     * @return The Total Count in Integer
     */
    public int getTotalCount(){
        try{
            Response response = IO.requestGET(Config.baseUrl + "/api_count_global.php");
            JSONObject json = (JSONObject) Config.parser.parse(response.getResponse());
            json = (JSONObject) Config.parser.parse(json.get("overall").toString());
            return Integer.parseInt(json.get("total_num_of_questions").toString());
        }
        catch (ParseException parseException){
            System.out.println("|NO LOGGER| JSON Response Parse Error! " + parseException);
        }
        return 0;
    }


}
