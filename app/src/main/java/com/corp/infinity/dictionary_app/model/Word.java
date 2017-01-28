package com.corp.infinity.dictionary_app.model;

import io.realm.RealmObject;

public class Word extends RealmObject {
    String word;
    String synonym;
    String antonym;

    public String getAntonym() {
        return antonym;
    }

    public void setAntonym(String antonym) {
        this.antonym = antonym;
    }

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "Word{" +
                "antonym='" + antonym + '\'' +
                ", word='" + word + '\'' +
                ", synonym='" + synonym + '\'' +
                '}';
    }
}
