package me.iblitzkriegi.vixio.scopes;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.iblitzkriegi.vixio.Vixio;
import me.iblitzkriegi.vixio.util.scope.EffectSection;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.bukkit.event.Event;

public class ScopeMakeBot extends EffectSection {

    public static JDA bot;
    public static JDABuilder jdaBuilder;

    static {
        Vixio.getInstance().registerCondition(ScopeMakeBot.class, "(make|create) vixio bot")
                .setName("Create Bot Scope")
                .setDesc("This is for verified bots or bots that have enabled privileged intents and need to activate them in Vixio")
                .setExample(
                        "discord command neeko:",
                        "\ttrigger:",
                        "\t\tcreate vixio bot:",
                        "\t\t\tenable the guild members intent",
                        "\t\t\tlogin to \"YAHITAMUH\" with the name \"Neeko\""
                );
    }

    @Override
    protected void execute(Event e) {
        jdaBuilder = JDABuilder.createDefault("temporary token");
        runSection(e);
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "create bot";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if (checkIfCondition())
            return false;
        if (!hasSection()) {
            Vixio.getErrorHandler().warn("Vixio attempted to create a bot but no section was found.");
            return false;
        }
        loadSection(true);

        return true;
    }
}
