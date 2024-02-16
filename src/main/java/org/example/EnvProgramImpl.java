package org.example;

import org.springframework.beans.factory.annotation.Value;

public class EnvProgramImpl implements EnvProgram {

    @Value("${app.path}")
    private String path;
    @Value("${app.initContacts}")
    private boolean initContacts;

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public boolean getInitContacts() {
        return initContacts;
    }
}
