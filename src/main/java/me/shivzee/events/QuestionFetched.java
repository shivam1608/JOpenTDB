package me.shivzee.events;

import me.shivzee.util.Question;

import java.util.List;

public interface QuestionFetched {
    void onQuestionFetched(List<Question> questions);
}
