package auxiliary.ElementType;

import auxiliary.CommonUtils;

public enum NPCType {
    GirlA,
    GirlB,
    Boy;
    public static NPCType getRandomNPC(){
        int i = CommonUtils.getRandomNumBetween(0,2);
        for(NPCType npcType : NPCType.values()){
            if (i == npcType.ordinal())
                return npcType;
        }
        return null;
    }
}
