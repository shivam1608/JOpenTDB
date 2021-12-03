package me.shivzee.atributes;

public enum Difficulty {
    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard"),
    RANDOM("");

    private final String difficulty;

    private Difficulty(String difficulty){
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
