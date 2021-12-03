package me.shivzee.util;

import me.shivzee.atributes.Category;
import me.shivzee.atributes.Difficulty;
import me.shivzee.atributes.Type;

import java.util.List;

public class Question {
    private final Category category;
    private final Type type;
    private final Difficulty difficulty;
    private final String question;
    private final String answer;
    private final List<String> options;

    public Question(Category category, Type type, Difficulty difficulty, String question, String answer, List<String> options) {
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.answer = answer;
        this.options = options;
    }

    /**
     * Get the Category of the Question
     * @return Category
     * @see me.shivzee.atributes.Category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Get the Type of the Question
     * @return Type
     * @see me.shivzee.atributes.Type
     */
    public Type getType() {
        return type;
    }

    /**
     * Get the Difficulty of the Question
     * @return Difficulty
     * @see me.shivzee.atributes.Difficulty
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Get the Question String
     * @return Question as String
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Get the Answer to the Question
     * @return String
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Get the Other Wrong Answers as Options
     * @return String
     */
    public List<String> getOptions() {
        return options;
    }
}
