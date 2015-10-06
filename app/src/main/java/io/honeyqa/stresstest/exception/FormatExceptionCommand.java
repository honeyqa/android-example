package io.honeyqa.stresstest.exception;

import android.nfc.FormatException;

import io.honeyqa.stresstest.common.Command;

/**
 * @author seunoh on 2014. 05. 11..
 */
public class FormatExceptionCommand extends Command {

    public FormatExceptionCommand() {
        setName(FormatException.class.getSimpleName());
    }


    @Override
    public void execute() throws Exception {
        throw new FormatException(name());
    }
}
