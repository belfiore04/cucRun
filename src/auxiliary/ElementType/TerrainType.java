package auxiliary.ElementType;

public enum TerrainType {
    BLANK,
    COIN,
    BLOCKER;
    public static TerrainType getType(int index){
        TerrainType[] terrainTypes = TerrainType.values();
        for(TerrainType terrainType : terrainTypes){
            if(terrainType.ordinal() == index){
                return terrainType;
            }
        }
        return null;
    }
}
