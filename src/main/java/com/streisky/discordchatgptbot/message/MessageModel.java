package com.streisky.discordchatgptbot.message;

import net.dv8tion.jda.api.utils.FileUpload;

public class MessageModel {

    private String message;
    private FileUpload file;

    public MessageModel(String message, FileUpload file) {
        this.message = message;
        this.file = file;
    }

    public MessageModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FileUpload getFile() {
        return file;
    }

    public void setFile(FileUpload file) {
        this.file = file;
    }
}
