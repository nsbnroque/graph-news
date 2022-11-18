package com.gft.noticias.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class DuplicatedUniquePropertyException extends DataIntegrityViolationException {
    public DuplicatedUniquePropertyException(String s)
    {
        super(s);
    }
}
