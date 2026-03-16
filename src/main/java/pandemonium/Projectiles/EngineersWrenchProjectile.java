package pandemonium.Projectiles;

import pandemonium.Main;
import necesse.engine.gameLoop.tickManager.TickManager;
import necesse.engine.sound.SoundEffect;
import necesse.engine.sound.SoundManager;
import necesse.engine.sound.SoundSettings;
import necesse.engine.util.GameRandom;
import necesse.entity.ParticleTypeSwitcher;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.particle.Particle;
import necesse.entity.projectile.boomerangProjectile.BoomerangProjectile;
import necesse.entity.trails.Trail;
import necesse.gfx.GameResources;
import necesse.gfx.ThemeColorRegistry;
import necesse.gfx.camera.GameCamera;
import necesse.gfx.drawOptions.texture.TextureDrawOptions;
import necesse.gfx.drawables.EntityDrawable;
import necesse.gfx.drawables.LevelSortedDrawable;
import necesse.gfx.drawables.OrderableDrawables;
import necesse.gfx.gameTexture.GameTextureSection;
import necesse.level.maps.Level;
import necesse.level.maps.LevelObjectHit;
import necesse.level.maps.light.GameLight;
import pandemonium.loaders.ModResources;

import java.awt.*;
import java.util.List;

public class EngineersWrenchProjectile extends BoomerangProjectile {
    protected ParticleTypeSwitcher typeSwitcher;
    public static GameTextureSection engineerswrenchparticletexture;

    public EngineersWrenchProjectile() {
        this.typeSwitcher = new ParticleTypeSwitcher(new Particle.GType[]{Particle.GType.IMPORTANT_COSMETIC, Particle.GType.COSMETIC, Particle.GType.CRITICAL});
    }
    public void init() {
        super.init();
        this.setWidth(20.0F, true);
        this.height = 18.0F;
        this.bouncing = 100;
    }

    public Color getParticleColor() {
        return null;
    }

    public Trail getTrail() {
        return null;
    }

    public void clientTick() {
        super.clientTick();
        float particleAngle = this.getAngle() - 45.0F - 90.0F;
        this.getLevel().entityManager.addParticle(this.x, this.y, Particle.GType.COSMETIC).sprite(engineerswrenchparticletexture.sprite(0, 0, 42)).color((options, lifeTime, timeAlive, lifePercent) -> options.alpha(0.5F - 0.5F * lifePercent)).height(this.height).size((options, lifeTime, timeAlive, lifePercent) -> options.size(42, 42)).rotation((lifeTime, timeAlive, lifePercent) -> particleAngle).lifeTime(200);
    }

    public void addDrawables(List<LevelSortedDrawable> list, OrderableDrawables tileList, OrderableDrawables topList, OrderableDrawables overlayList, Level level, TickManager tickManager, GameCamera camera, PlayerMob perspective) {
        if (!this.removed()) {
            GameLight light = level.getLightLevel(this);
            int drawX = camera.getDrawX(this.x) - this.texture.getWidth() / 2;
            int drawY = camera.getDrawY(this.y) - this.texture.getHeight() / 2;
            final TextureDrawOptions options = this.texture.initDraw().light(light).rotate(this.getAngle(), this.texture.getWidth() / 2, this.texture.getHeight() / 2).pos(drawX, drawY - (int)this.getHeight());
            list.add(new EntityDrawable(this) {
                public void draw(TickManager tickManager) {
                    options.draw();
                }
            });
            this.addShadowDrawables(tileList, drawX, drawY, light, this.getAngle(), this.texture.getHeight() / 2);
        }
    }

    public float getAngle() {
        return super.getAngle() * 1.5F;
    }

    public void doHitLogic(Mob mob, LevelObjectHit object, float x, float y) {
        super.doHitLogic(mob, object, x, y);
        if (this.isClient()) {
            GameRandom clientRandom = GameRandom.globalRandom;

            for(int i = 0; i < 6; ++i) {
                this.getLevel().entityManager.addParticle(x + (float)clientRandom.getIntBetween(-12, 12), y + (float)clientRandom.getIntBetween(-12, 12), this.typeSwitcher.next()).sprite(GameResources.puffParticles.sprite(clientRandom.nextInt(4), 0, 12)).sizeFades(30, 60).height(18.0F).color(this.getWallHitColor());
            }
        }

    }

    protected void playHitSound(float x, float y) {
        SoundManager.playSound(ModResources.WrenchHit, SoundEffect.effect(x, y).volume(0.5F).pitch(0.9F));
    }

    protected Color getWallHitColor() {
        return ThemeColorRegistry.SMOKE.getRandomColor();
    }

    protected SoundSettings getSpawnSound() {
        return (new SoundSettings(GameResources.tungstenBoomerang)).volume(0.75F);
    }
}
