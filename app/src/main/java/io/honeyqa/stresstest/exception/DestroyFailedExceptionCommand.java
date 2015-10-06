package io.honeyqa.stresstest.exception;

import io.honeyqa.stresstest.common.Command;

import javax.security.auth.DestroyFailedException;

/**
 * @author seunoh on 2014. 05. 11..
 */
public class DestroyFailedExceptionCommand extends Command {

    public DestroyFailedExceptionCommand() {
        setName(DestroyFailedException.class.getSimpleName());
    }

    @Override
    public void execute() throws Exception {
        throw new DestroyFailedException(name());
    }
}
