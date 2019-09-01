package com.crud.communicator.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MessageDto {
    private String senderLogin;
    private String date;
    private String message;
    private String recipientLogin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageDto that = (MessageDto) o;
        return Objects.equals(senderLogin, that.senderLogin) &&
                Objects.equals(date, that.date) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(senderLogin, date, message);
    }
}
