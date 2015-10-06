package io.honeyqa.stresstest.exception;

import android.accounts.AccountsException;

import io.honeyqa.stresstest.common.Command;

/**
 * @author seunoh on 2014. 05. 11..
 */
public class AccountsExceptionCommand extends Command {


    public AccountsExceptionCommand() {
        setName(AccountsException.class.getSimpleName());
    }

    @Override
    public void execute() throws Exception {
        throw new AccountsException(name());
    }
}
