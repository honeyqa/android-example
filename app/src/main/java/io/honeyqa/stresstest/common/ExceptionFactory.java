package io.honeyqa.stresstest.common;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;

import io.honeyqa.stresstest.exception.AccountsExceptionCommand;
import io.honeyqa.stresstest.exception.AndroidExceptionCommand;
import io.honeyqa.stresstest.exception.ArrayIndexOutOfBoundsExceptionCommand;
import io.honeyqa.stresstest.exception.BrokenBarrierExceptionCommand;
import io.honeyqa.stresstest.exception.ClassCastExceptionCommand;
import io.honeyqa.stresstest.exception.DataFormatExceptionCommand;
import io.honeyqa.stresstest.exception.DestroyFailedExceptionCommand;
import io.honeyqa.stresstest.exception.FileNotFoundExceptionCommand;
import io.honeyqa.stresstest.exception.FormatExceptionCommand;
import io.honeyqa.stresstest.exception.NullPointerExceptionCommand;
import io.honeyqa.stresstest.exception.NumberFormatExceptionCommand;

/**
 * @author seunoh on 2014. 05. 07..
 */
public final class ExceptionFactory {

    private static volatile List<ExceptionCommand> sData = Lists.newArrayList();

    static {
        items().add(new AccountsExceptionCommand());
        items().add(new AndroidExceptionCommand());
        items().add(new ArrayIndexOutOfBoundsExceptionCommand());
        items().add(new BrokenBarrierExceptionCommand());
        items().add(new ClassCastExceptionCommand());
        items().add(new DataFormatExceptionCommand());
        items().add(new DestroyFailedExceptionCommand());
        items().add(new FileNotFoundExceptionCommand());
        items().add(new FormatExceptionCommand());
        items().add(new NullPointerExceptionCommand());
        items().add(new NumberFormatExceptionCommand());
    }


    public static ExceptionCommand getRandom() {
        Random random = new Random();
        return items().get(random.nextInt(items().size()));
    }

    public static List<ExceptionCommand> items() {
        return sData;
    }
}
