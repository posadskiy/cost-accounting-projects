package com.posadskiy.costaccounting.projects.core.exception;

public interface Exceptionable {
    String getDetailedMessage();

    int getCode();
}
