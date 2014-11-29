package mods.creepersfire.api.interfaces;

public interface ICreeper
{
    //use this as a fuse on your creeper compatible with creepersfire (must make explode preferably by doExplode)
    public void doFuse();

    //your creeper explosion
    public void doExplode();

    //return true if you use the Config for the fuse
    public boolean useConfigForFuse();

    //Method CreepersFire can use to set fuseTime. The fuseTime will be for a normal creeper so adgust yours accordingly
    public void setFuseTime(int fuseTime);
}
