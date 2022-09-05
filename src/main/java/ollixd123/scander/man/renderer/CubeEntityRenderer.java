package ollixd123.scander.man.renderer;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import ollixd123.scander.man.EntityTestingClient;
import ollixd123.scander.man.entity.CubeEntity;
import ollixd123.scander.man.model.CubeEntityModel;

public class CubeEntityRenderer extends MobEntityRenderer<CubeEntity, CubeEntityModel> {
 
    public CubeEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new CubeEntityModel(context.getPart(EntityTestingClient.MODEL_CUBE_LAYER)), 0.5f);
    }
 
    @Override
    public Identifier getTexture(CubeEntity entity) {
        return new Identifier("scanderman", "scanderman/textures/entity/cube/cube.png");
    }
}
