package com.example.lista4.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class QuizQuestion implements Parcelable {
    private String question;
    private List<String> answers;
    private Integer rightAnswerIndex;

    public QuizQuestion(String question, List<String> answers, Integer rightAnswerIndex) {
        this.question = question;
        this.answers = answers;
        this.rightAnswerIndex = rightAnswerIndex;
    }

    public QuizQuestion() {
    }

    protected QuizQuestion(Parcel in) {
        setQuestion(in.readString());
        setAnswers(in.createStringArrayList());
        setRightAnswerIndex(in.readInt());
    }

    public static final Creator<QuizQuestion> CREATOR = new Creator<QuizQuestion>() {
        @Override
        public QuizQuestion createFromParcel(Parcel in) {
            return new QuizQuestion(in);
        }

        @Override
        public QuizQuestion[] newArray(int size) {
            return new QuizQuestion[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getQuestion());
        dest.writeStringList(getAnswers());
        dest.writeInt(getRightAnswerIndex());
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public Integer getRightAnswerIndex() {
        return rightAnswerIndex;
    }

    public void setRightAnswerIndex(Integer rightAnswerIndex) {
        this.rightAnswerIndex = rightAnswerIndex;
    }

    public List<QuizQuestion> populateQuestionsList() {
        List<QuizQuestion> questionList = new ArrayList<>();

        questionList.add(new QuizQuestion("Co mierzy amperomierz?",
                List.of("Napięcie", "Natężenie prądu", "Opór elektryczny", "Moc"),
                1));

        questionList.add(new QuizQuestion("Jaka jest podstawowa jednostka ładunku elektrycznego?",
                List.of("Kolumb", "Joul", "Volt", "Wat"),
                0));

        questionList.add(new QuizQuestion("Co to jest energia potencjalna?",
                List.of("Energia związana z ruchem", "Energia związana z położeniem", "Energia wyzwalana w reakcjach jądrowych", "Energia przekazywana przez fale"),
                1));

        questionList.add(new QuizQuestion("Jakie jest prawo Ohma?",
                List.of("I = V/R", "V = I/R", "R = IV", "P = VI"),
                0));

        questionList.add(new QuizQuestion("Jak nazywa się zjawisko rozszczepienia światła w pryzmacie?",
                List.of("Dyfrakcja", "Rozszczepienie", "Interferencja", "Załamanie"),
                1));
        questionList.add(new QuizQuestion("Co opisuje druga zasada dynamiki Newtona?",
                List.of("Równowagę sił", "Przyspieszenie ciała", "Reakcję na działanie siły", "Oddziaływanie grawitacyjne"),
                1));

        questionList.add(new QuizQuestion("Jaka jest jednostka pracy w układzie SI?",
                List.of("Niuton", "Joul", "Wat", "Amper"),
                1));

        questionList.add(new QuizQuestion("Co to jest prędkość światła w próżni?",
                List.of("3 x 10^6 m/s", "3 x 10^7 m/s", "3 x 10^8 m/s", "3 x 10^9 m/s"),
                2));

        questionList.add(new QuizQuestion("Który rodzaj fali jest przykładem fali mechanicznej?",
                List.of("Fala dźwiękowa", "Fala elektromagnetyczna", "Fala świetlna", "Fala radiowa"),
                0));

        questionList.add(new QuizQuestion("Jakie jest prawo odbicia światła?",
                List.of("Kąt odbicia jest równy kątowi załamania", "Kąt odbicia jest równy kątowi padania", "Światło odbija się tylko pod kątem prostym", "Kąt padania jest większy od kąta odbicia"),
                1));


        return questionList;
    }
}

