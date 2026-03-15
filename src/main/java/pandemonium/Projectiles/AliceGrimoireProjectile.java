package pandemonium.Projectiles;

import necesse.engine.gameLoop.tickManager.TickManager;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.projectile.Projectile;
import necesse.entity.trails.Trail;
import necesse.gfx.GameResources;
import necesse.gfx.camera.GameCamera;
import necesse.gfx.drawables.LevelSortedDrawable;
import necesse.gfx.drawables.OrderableDrawables;
import necesse.gfx.gameTexture.GameSprite;
import necesse.level.maps.Level;

import java.awt.*;
import java.util.List;

public class AliceGrimoireProjectile extends Projectile {
        public void init() {
            super.init();
            this.maxMovePerTick = 24;
            this.height = 18.0F;
            this.piercing = 0;
            this.bouncing = 3;
        }

    public Color TrailColor() {
        long time = System.currentTimeMillis() % 2500L;
        return new Color(Color.HSBtoRGB((float)time / 2500.0F, 1.0F, 1.0F));
    }

    public Trail getTrail() {
        Trail trail = new Trail(this, this.getLevel(), this.TrailColor(), 16.0F, 100, this.getHeight());
        trail.sprite = new GameSprite(GameResources.chains, 7, 0, 32);
        return trail;
    }

    public void clientTick() {
        super.clientTick();
        if (this.trail != null) {
            this.trail.setColor(this.TrailColor());
        }
    }

    public void addDrawables(List<LevelSortedDrawable> list, OrderableDrawables tileList, OrderableDrawables topList, OrderableDrawables overlayList, Level level, TickManager tickManager, GameCamera camera, PlayerMob perspective) {
    }
}
