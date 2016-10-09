package ru.spbau.sdcourse.Commands;

import ru.spbau.sdcourse.util.PathResolver;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * Change directory command
 * Created by andy on 10/9/16.
 */
@BuiltinCommand(name="cd")
public class Cd extends Command  {

    /**
     * @param prev other command which provides input.
     * @param args arguments for command.
     * @param env  environment variables.
     */
    public Cd(Command prev, List<String> args, Map<String, String> env) {
        super(prev, args, env);
    }

    @Override
    protected void start() throws Exception {
        if (arguments.isEmpty()) {
            System.setProperty("user.dir", System.getProperty("user.home"));
            return;
        }

        Path newDir = PathResolver.resolvePath(arguments.get(0));
        System.setProperty("user.dir", newDir.toString());
    }
}
