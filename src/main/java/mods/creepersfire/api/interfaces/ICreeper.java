package mods.creepersfire.api.interfaces;

public interface ICreeper
{
    //use this as a fuse on your creeper compatible with creepersfire (must make explode preferably by doExplode)
    public void doFuse();

    //your creeper explosion
    public void doExplode();
}
