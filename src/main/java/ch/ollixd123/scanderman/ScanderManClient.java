package ch.ollixd123.scanderman;

import ch.ollixd123.scanderman.entity.ModEntity;
import ch.ollixd123.scanderman.entity.client.ScanderManRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class ScanderManClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntity.SCANDERMAN, ScanderManRenderer::new);
    }
}
