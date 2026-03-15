package pandemonium.Mobs;

import necesse.engine.gameLoop.tickManager.TickManager;
import necesse.engine.modifiers.ModifierValue;
import necesse.engine.registries.MobRegistry;
import necesse.entity.mobs.GameDamage;
import necesse.entity.mobs.MaskShaderOptions;
import necesse.entity.mobs.MobDrawable;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.ai.behaviourTree.BehaviourTreeAI;
import necesse.entity.mobs.ai.behaviourTree.trees.ConfusedCollisionPlayerChaserWandererAI;
import necesse.entity.mobs.buffs.BuffModifiers;
import necesse.entity.mobs.hostile.HostileMob;
import necesse.entity.particle.FleshParticle;
import necesse.entity.particle.Particle;
import necesse.gfx.camera.GameCamera;
import necesse.gfx.drawOptions.DrawOptions;
import necesse.gfx.drawOptions.texture.TextureDrawOptions;
import necesse.gfx.drawables.OrderableDrawables;
import necesse.gfx.gameTexture.GameTexture;
import necesse.inventory.lootTable.LootTable;
import necesse.inventory.lootTable.lootItem.ChanceLootItem;
import necesse.level.maps.Level;
import necesse.level.maps.light.GameLight;

import java.awt.*;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class FeralWolfMob extends HostileMob {
    public static GameTexture texture;
    public static LootTable lootTable = new LootTable(ChanceLootItem.between(0.4f, "feralwolffur", 1, 2));

    public FeralWolfMob() {
        super(80);
        this.setSpeed(40.0F);
        this.setFriction(4.0F);
        this.setKnockbackModifier(0.75F);
        this.setArmor(0);
        this.moveAccuracy = 8;
        this.collision = new Rectangle(-10, -7, 20, 14);
        this.hitBox = new Rectangle(-14, -12, 28, 24);
        this.selectBox = new Rectangle(-18, -24, 36, 36);
        this.swimMaskMove = 16;
        this.swimMaskOffset = 0;
        this.swimSinkOffset = -12;
    }

    public void init() {
        super.init();
        this.ai = new BehaviourTreeAI(this, new ConfusedCollisionPlayerChaserWandererAI((Supplier)null, 512, new GameDamage(15.0F), 100, 40000));
    }

    public LootTable getLootTable() {
        return lootTable;
    }

    public void spawnDeathParticles(float knockbackX, float knockbackY) {
        for(int i = 0; i < 4; ++i) {
            this.getLevel().entityManager.addParticle(new FleshParticle(this.getLevel(), texture, 12, i, 32, this.x, this.y, 20.0F, knockbackX, knockbackY), Particle.GType.IMPORTANT_COSMETIC);
        }

    }

    public void addDrawables(List<MobDrawable> list, OrderableDrawables tileList, OrderableDrawables topList, Level level, int x, int y, TickManager tickManager, GameCamera camera, PlayerMob perspective) {
        super.addDrawables(list, tileList, topList, level, x, y, tickManager, camera, perspective);
        GameLight light = level.getLightLevel(getTileCoordinate(x), getTileCoordinate(y));
        int drawX = camera.getDrawX(x) - 32;
        int drawY = camera.getDrawY(y) - 36;
        int dir = this.getDir();
        Point sprite = this.getAnimSprite(x, y, dir);
        drawY += this.getBobbing(x, y);
        drawY += level.getTile(getTileCoordinate(x), getTileCoordinate(y)).getMobSinkingAmount(this);
        final MaskShaderOptions swimMask = this.getSwimMaskShaderOptions(this.inLiquidFloat(x, y));
        final DrawOptions body = texture.initDraw().sprite(sprite.x, sprite.y, 64).addMaskShader(swimMask).light(light).pos(drawX, drawY);
        list.add(new MobDrawable() {
            public void draw(TickManager tickManager) {
                swimMask.use();
                body.draw();
                swimMask.stop();
            }
        });
        TextureDrawOptions shadow = MobRegistry.Textures.snowWolf.shadow.initDraw().sprite(0, sprite.y, 64).light(light).pos(drawX, drawY);
        tileList.add((tm) -> shadow.draw());
    }

    public int getSwimMaskMove() {
        return this.getDir() != 2 ? super.getSwimMaskMove() + 4 : super.getSwimMaskMove();
    }

    public int getRockSpeed() {
        return 13;
    }

    public Stream<ModifierValue<?>> getDefaultModifiers() {
        return Stream.of((new ModifierValue(BuffModifiers.FRICTION, 0.0F)).min(0.75F));
    }
}
