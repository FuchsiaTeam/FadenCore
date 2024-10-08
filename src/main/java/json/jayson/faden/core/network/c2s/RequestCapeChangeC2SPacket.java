package json.jayson.faden.core.network.c2s;

import java.util.UUID;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import json.jayson.faden.core.server.PlayerData;
import json.jayson.faden.core.server.ServerPlayerDatas;
import json.jayson.faden.core.util.FadenCoreIdentifier;
import json.jayson.faden.core.util.NetworkUtils;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record RequestCapeChangeC2SPacket(UUID uuid, String capeId) implements CustomPayload {

    public static final CustomPayload.Id<RequestCapeChangeC2SPacket> ID = new CustomPayload.Id<>(FadenCoreIdentifier.create("request_cape"));
    public static final PacketCodec<RegistryByteBuf, RequestCapeChangeC2SPacket> CODEC = new PacketCodec<>() {
        @Override
        public RequestCapeChangeC2SPacket decode(RegistryByteBuf buf) {
            return new RequestCapeChangeC2SPacket(buf.readUuid(), buf.readString());
        }

        @Override
        public void encode(RegistryByteBuf buf, RequestCapeChangeC2SPacket value) {
            buf.writeUuid(value.uuid);
            buf.writeString(value.capeId);
        }
    };

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public void receive(ServerPlayNetworking.Context context) {
        PlayerData data = ServerPlayerDatas.getOrLoadPlayerData(uuid);
        if(capeId.isEmpty()) {
            data.setSelectedCape("");
        } else {
            data.getCapes().stream()
                    .filter(s -> s.equalsIgnoreCase(capeId))
                    .findFirst()
                    .ifPresent(s -> data.setSelectedCape(capeId));
        }
        ServerPlayerDatas.getPlayerDatas().put(uuid, data);
        NetworkUtils.syncPlayer(context.server(), context.player());
    }
}

