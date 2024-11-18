package com.example.rentandhirecars;

public class Message {
    private String sender; // "user" or "bot"
    private String content; // The content of the message

    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }
}