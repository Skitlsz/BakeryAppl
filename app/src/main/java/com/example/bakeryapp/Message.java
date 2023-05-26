package com.example.bakeryapp;

public class Message {
    private String text;
    private boolean sentBySender;

    public Message(String text, boolean sentBySender) {
        this.text = text;
        this.sentBySender = sentBySender;
    }

    public String getText() {
        return text;
    }

    public boolean isSentBySender() {
        return sentBySender;
    }
}

