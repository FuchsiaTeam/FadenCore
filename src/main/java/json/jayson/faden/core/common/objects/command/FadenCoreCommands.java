package json.jayson.faden.core.common.objects.command;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class FadenCoreCommands implements CommandRegistrationCallback {
    @Override
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
        CurrencyCommand.register(dispatcher);
        RaceCommand.register(dispatcher);
        ReloadCommand.register(dispatcher);
        CapeCommand.register(dispatcher);
        ItemValueCommand.register(dispatcher, registryAccess);
        NPCCommand.register(dispatcher);
    }
}
