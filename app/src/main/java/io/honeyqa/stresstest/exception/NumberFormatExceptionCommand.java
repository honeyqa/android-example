package io.honeyqa.stresstest.exception;

import io.honeyqa.stresstest.common.Command;

/**
 * @author seunoh on 2014. 05. 10..
 */
public class NumberFormatExceptionCommand extends Command {


    public NumberFormatExceptionCommand() {
        setName(NumberFormatException.class.getSimpleName());
    }


    @Override
    public void execute() throws Exception {
        throw new NumberFormatException(name());
    }
}
