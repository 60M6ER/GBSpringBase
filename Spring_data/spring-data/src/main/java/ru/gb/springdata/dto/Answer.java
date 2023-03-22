package ru.gb.springdata.dto;

import lombok.Data;

@Data
public class Answer {
    private boolean result;

    public Answer() {
    }

    public Answer(boolean result) {
        this.result = result;
    }

}
