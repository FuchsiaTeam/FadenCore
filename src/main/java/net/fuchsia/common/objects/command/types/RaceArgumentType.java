package net.fuchsia.common.objects.command.types;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.context.StringRange;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestion;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.fuchsia.common.race.Race;
import net.minecraft.command.CommandSource;
import net.minecraft.util.StringIdentifiable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class RaceArgumentType implements ArgumentType<String> {

    public RaceArgumentType() {
    }

    public static RaceArgumentType empty() {
        return new RaceArgumentType();
    }

    @Override
    public String parse(final StringReader reader) throws CommandSyntaxException {
        return reader.readUnquotedString();
    }

    @Override
    public Collection<String> getExamples() {
        Collection<String> ex = new ArrayList<>();
        for (Race value : Race.values()) {
            ex.add(value.getId());
        }
        return ex;
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        for (Race value : Race.values()) {
            builder.suggest(value.getId());
        }
        return builder.buildFuture();
    }
}
