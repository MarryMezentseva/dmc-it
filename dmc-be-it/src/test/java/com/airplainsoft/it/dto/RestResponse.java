package com.airplainsoft.it.dto;

import java.util.List;

public class RestResponse<T> {
    private List<String> messages;
    private T payload;
    private boolean success;
    private String timestamp;

    public RestResponse(List<String> messages, T payload, boolean success, String timestamp) {
        this.messages = messages;
        this.payload = payload;
        this.success = success;
        this.timestamp = timestamp;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "RestResponse{" +
                "messages=" + messages +
                ", payload=" + payload +
                ", success=" + success +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}

