package ollixd123.scander.man.renderer;

import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EndermanEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import ollixd123.scander.man.entity.ScandermanEntity;

public class ScandemanEntityRenderer extends BipedEntityRenderer<ScandermanEntity, EndermanEntityModel<ScandermanEntity>>{

    public ScandemanEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new EndermanEntityModel<ScandermanEntity>(context.getPart(EntityModelLayers.ENDERMAN)), 0.5f);
    }

    @Override
    public Identifier getTexture(ScandermanEntity entity){
        return new Identifier("scanderman","scanderman/textures/entity/scanderman/scanderman.png");
    }

}
