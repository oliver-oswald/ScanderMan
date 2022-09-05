package ollixd123.scander.man;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import ollixd123.scander.man.model.CubeEntityModel;
import ollixd123.scander.man.renderer.CubeEntityRenderer;
import ollixd123.scander.man.renderer.ScandemanEntityRenderer;

@Environment(EnvType.CLIENT)
public class EntityTestingClient implements ClientModInitializer {
    public static final EntityModelLayer MODEL_CUBE_LAYER = new EntityModelLayer(new Identifier("scanderman", "cube"), "main");
    @Override
    public void onInitializeClient() {
        /*
         * Registers our Cube Entity's renderer, which provides a model and texture for the entity.
         *
         * Entity Renderers can also manipulate the model before it renders based on entity context (EndermanEntityRenderer#render).
         */
        
        EntityRendererRegistry.register(ScanderMan.CUBE, (context) -> {
            return new CubeEntityRenderer(context);
        });

        EntityRendererRegistry.register(ScanderMan.SCANDERMAN, (context) -> {
            return new ScandemanEntityRenderer(context);
        });
 
        EntityModelLayerRegistry.registerModelLayer(MODEL_CUBE_LAYER, CubeEntityModel::getTexturedModelData);
    }
}
