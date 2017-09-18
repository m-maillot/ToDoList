package io.mmaillot.todolistsample.model;

import android.support.annotation.NonNull;

import java.util.Date;

public class ToDo {

    @NonNull
    private final Date creation;
    @NonNull
    private final Date deadline;
    @NonNull
    private final String title;
    @NonNull
    private final String content;

    public ToDo(@NonNull Date pCreation, @NonNull Date pDeadline, @NonNull String pTitle,
                @NonNull String pContent) {
        creation = pCreation;
        deadline = pDeadline;
        title = pTitle;
        content = pContent;
    }

    @NonNull
    public Date getCreation() {
        return creation;
    }

    @NonNull
    public Date getDeadline() {
        return deadline;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getContent() {
        return content;
    }
}
